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

        @ConfigEntry(
            description = "If true, then"
        )
        var doNotDisturb = false
    }

    class CommonConfig {

    }

    class ServerConfig {
        @ConfigEntry(
            description = "The token of the bot. See https://jda.wiki/using-jda/getting-started/#creating-a-discord-bot for how to create a bot and get the token."
        )
        var token = ""

        @ConfigEntry(
            description = "if a discord user has a role with this id, they will be able to use operator commands. If this is unset, only users with the administrator permission can use operator commands."
        )
        var operatorRoleId = ""

        @ConfigEntry(
            description = "The channel id or name of the bridge channel."
        )
        var bridgeChannel = ""

        var sendDiscordToMinecraft = true

        var sendMinecraftToDiscord = true

    }
}