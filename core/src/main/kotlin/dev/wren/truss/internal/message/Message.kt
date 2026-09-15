package dev.wren.truss.internal.message

data class Message(val segments: List<MessageSegment>)

data class MessageSegment(
    val text: String,
    val color: String,
)
