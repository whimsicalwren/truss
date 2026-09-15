package dev.wren.truss.bot

import dev.minn.jda.ktx.coroutines.await
import dev.wren.truss.LOGGER
import dev.wren.truss.config.TrussConfig
import dev.wren.truss.platform.LANG
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.MemberCachePolicy
import java.util.concurrent.Executors
import dev.wren.truss.util.Utils.newThreadFactory
import dev.wren.truss.util.errorKey
import dev.wren.truss.util.infoKey
import dev.wren.truss.util.warnKey
import kotlinx.coroutines.*
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import kotlin.time.Duration.Companion.milliseconds

object BotManager {

    lateinit var bot: JDA
        private set

    suspend fun init(): Boolean {
        val token = TrussConfig.server.token
        if (token.isBlank()) {
            LOGGER.errorKey("discord.no_token")
            return false
        }

        return withContext(Dispatchers.IO) {
            val eventExecutor = Executors.newSingleThreadExecutor(
                newThreadFactory("truss-event")
            )
            val callbackExecutor = Executors.newCachedThreadPool(
                newThreadFactory("truss-callback")
            )

            val readyWarningJob = launch {
                delay(5000.milliseconds)
                LOGGER.warnKey("discord.waiting_ready")
            }

            try {
                bot = JDABuilder.createDefault(token)
                    .enableIntents(
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS
                    )
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .setEventPool(eventExecutor, true)
                    .setCallbackPool(callbackExecutor, true)
                    .addEventListeners(DiscordEventListener)
                    .build()

                bot.awaitReady()
                readyWarningJob.cancel()
                LOGGER.infoKey("discord.ready")
            } catch (e: CancellationException) {
                readyWarningJob.cancel()
                LOGGER.errorKey("discord.init_interrupted", e)
                throw e
            } catch (e: Exception) {
                readyWarningJob.cancel()
                eventExecutor.shutdownNow()
                callbackExecutor.shutdownNow()
                LOGGER.errorKey("discord.init_interrupted", e)
            }

            if (bot.status != JDA.Status.CONNECTED) {
                return@withContext false
            }

            try {
                val commands = mutableListOf<CommandData>().apply {
                    val lang = LANG.createPrefix("command")

                    add(Commands.slash("help", lang.translate("help.description")))
                    add(Commands.slash("info", lang.translate("info.description")))
                    add(Commands.slash("whitelist", lang.translate("whitelist.description")))
                }

                val commandWarningJob = launch {
                    delay(5000.milliseconds)
                    LOGGER.warnKey("discord.registering_commands")
                }

                bot.updateCommands().addCommands(commands).await()
                commandWarningJob.cancel()

                LOGGER.infoKey("discord.commands_success")
                true
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                LOGGER.errorKey("discord.commands_failed", e)
                false
            }
        }
    }
}