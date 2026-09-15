package dev.wren.truss.util

import dev.wren.truss.platform.LANG
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.HoverEvent
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.Style
import net.minecraft.network.chat.TextColor

class ComponentUtil {
    val blurple: TextColor = TextColor.fromRgb(0x5865F2)

    fun makeDiscordStyle(messageId: String? = null): Style =
        Style.EMPTY
            .withColor(blurple)
            .withBold(true)
            .withHoverEvent(
                HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                LANG.translate("discord.message.from_discord").component().gray()
                    .applyIf(messageId != null) {
                        it.newLine().append(LANG.translate("discord.message.id", messageId!!).component().darkGray())
                    }
            ))
}

fun MutableComponent.newLine(): MutableComponent = this.append("\n")
fun MutableComponent.indent(): MutableComponent = this.append("\t")

// yes i'm lazy how can you tell
fun MutableComponent.gray(): MutableComponent = this.withStyle(ChatFormatting.GRAY)
fun MutableComponent.darkGray(): MutableComponent = this.withStyle(ChatFormatting.DARK_GRAY)


