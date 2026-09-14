package dev.wren.truss.commands

import com.mojang.brigadier.builder.ArgumentBuilder
import dev.wren.truss.platform.CommonLangHelper
import dev.wren.truss.platform.LANG
import dev.wren.truss.platform.PLATFORM
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component

fun registerDebug(): ArgumentBuilder<CommandSourceStack, *> {
    return Commands.literal("debug")
        .then(registerLangDebug())
        .then(registerPlatformDebug())
}

private fun registerLangDebug(): ArgumentBuilder<CommandSourceStack, *> {
    return Commands.literal("langservice")
        .executes { ctx ->
            ctx.source.sendSuccess({
                Component.literal(LANG.translate("truss.test_lang"))
            }, false)
            1
        }
}

private fun registerPlatformDebug(): ArgumentBuilder<CommandSourceStack, *> {
    return Commands.literal("platformservice")
        .executes { ctx ->
            ctx.source.sendSuccess({
                Component.literal("${PLATFORM.platform} ${PLATFORM.dist}")
            }, false)
            1
        }
}