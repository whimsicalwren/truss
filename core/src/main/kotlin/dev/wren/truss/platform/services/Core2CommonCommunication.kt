package dev.wren.truss.platform.services

import net.dv8tion.jda.api.entities.Message

interface Core2CommonCommunication {
    fun sendMessage(message: Message, direction: Direction)
    fun updateMessage(message: Message, direction: Direction)
    fun deleteMessage(messageId: String, direction: Direction)

    enum class Direction {
        DISCORD,
        MINECRAFT
    }
    /*
    general system design:
    sending to minecraft -
        we send Message object to common, then parse it there and then use server instance to send packet to client
    sending to discord -
        we have Component on common, get string then send to core along with mc player username and player head for webhook
     */
}