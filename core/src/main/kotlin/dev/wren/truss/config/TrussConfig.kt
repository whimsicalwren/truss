package dev.wren.truss.config

import dev.wren.truss.internal.config.ConfigEntry


object TrussConfig {

    @JvmField
    val CLIENT = Client()

    @JvmField
    val COMMON = Common()

    @JvmField
    val SERVER = Server()

    class Client {
        @ConfigEntry
        val showEmojis = true

        @ConfigEntry
        val showMarkdown = true

        @ConfigEntry
        val showMarkdownWhileTyping = true
    }

    class Common {

    }

    class Server {

    }
}