package dev.wren.truss.config

import dev.wren.truss.internal.config.ConfigCategory
import dev.wren.truss.internal.config.ConfigEntry


object TrussConfig {

    @JvmField
    val client = ClientConfig()

    @JvmField
    val common = CommonConfig()

    @JvmField
    val server = ServerConfig()

    class ClientConfig {
        @ConfigEntry
        var showEmojis = true

        @ConfigEntry
        var showMarkdown = true

        @ConfigEntry
        var showMarkdownWhileTyping = true
    }

    class CommonConfig {

    }

    class ServerConfig {
        @ConfigCategory(title = "Core Config")
        val core = CoreServerConfig()

        class CoreServerConfig {
            @ConfigEntry(
                description = "The token of the bot. See https://jda.wiki/using-jda/getting-started/#creating-a-discord-bot for how to create a bot and get the token."
            )
            var token = ""
        }
    }
}