package dev.wren.truss.platform.services

/**
 * Helper to translate keys in core, since we don't have minecraft's translation system.
 */
interface LangHelper {
    fun translate(key: String, vararg args: Any): String

    fun createPrefix(prefix: String): PrefixedLangHelper = PrefixedLangHelper(prefix, this)

    class PrefixedLangHelper(val prefix: String, val parent: LangHelper) : LangHelper {
        override fun translate(key: String, vararg args: Any): String =
            parent.translate("$prefix.$key", args)
    }
}