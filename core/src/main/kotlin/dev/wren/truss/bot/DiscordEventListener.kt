package dev.wren.truss.bot

import dev.wren.truss.config.TrussConfig
import dev.wren.truss.platform.MESSAGE
import dev.wren.truss.platform.services.Core2CommonCommunication
import net.dv8tion.jda.api.events.message.MessageDeleteEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.MessageUpdateEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

object DiscordEventListener : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.author == event.jda.selfUser || // check that we did not send message
            !TrussConfig.server.sendDiscordToMinecraft || // check to make sure discord -> minecraft bridge is enabled
            !(event.channel.name == TrussConfig.server.bridgeChannel || event.channel.id == TrussConfig.server.bridgeChannel) || // check to make sure message is in bridge channel
            event.isWebhookMessage // check that the message isn't from a webhook
        ) return // i hate this

        MESSAGE.sendMessage(event.message, Core2CommonCommunication.Direction.MINECRAFT)
    }

    override fun onMessageUpdate(event: MessageUpdateEvent) {
        if (event.author == event.jda.selfUser ||
            !TrussConfig.server.sendDiscordToMinecraft ||
            !(event.channel.name == TrussConfig.server.bridgeChannel || event.channel.id == TrussConfig.server.bridgeChannel)
        ) return

        MESSAGE.updateMessage(event.message, Core2CommonCommunication.Direction.MINECRAFT)
    }

    override fun onMessageDelete(event: MessageDeleteEvent) {
        if (!TrussConfig.server.sendDiscordToMinecraft ||
            !(event.channel.name == TrussConfig.server.bridgeChannel || event.channel.id == TrussConfig.server.bridgeChannel)
        ) return

        MESSAGE.deleteMessage(event.messageId, Core2CommonCommunication.Direction.MINECRAFT)
    }

}