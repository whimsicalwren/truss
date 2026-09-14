package dev.wren.truss.platform

import dev.wren.truss.platform.services.LangHelper
import net.minecraft.network.chat.Component

class CommonLangHelper : LangHelper {
    override fun translate(key: String, vararg args: Any): String {
        return Component.translatable(key, args).string
    }

}