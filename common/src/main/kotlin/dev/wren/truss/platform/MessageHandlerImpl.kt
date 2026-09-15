package dev.wren.truss.platform

import dev.wren.truss.platform.services.Core2CommonCommunication
import net.dv8tion.jda.api.entities.Message

class MessageHandlerImpl : Core2CommonCommunication {
    override fun sendMessage(message: Message, direction: Core2CommonCommunication.Direction) {
        val author = message.author
    }

    override fun updateMessage(message: Message, direction: Core2CommonCommunication.Direction) {

    }

    override fun deleteMessage(messageId: String, direction: Core2CommonCommunication.Direction) {

    }
}