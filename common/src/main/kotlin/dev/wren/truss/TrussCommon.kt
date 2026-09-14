package dev.wren.truss

import com.mojang.brigadier.CommandDispatcher
import dev.wren.truss.commands.registerDebug
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands


object TrussCommon {

    fun init() {
        Truss.init()
        LOGGER.info("common init")
    }

    fun registerCommands(dispatcher: CommandDispatcher<CommandSourceStack>) {
        val root = Commands.literal("truss")
            .then(registerDebug())

        dispatcher.register(root)
    }

}