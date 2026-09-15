package dev.wren.truss.util

import dev.wren.truss.platform.LANG
import org.apache.logging.log4j.Logger

// region logger
// info
fun Logger.infoKey(key: String) {
    info(LANG.translate(key))
}

fun Logger.infoKey(key: String, vararg args: Any) {
    info(LANG.translate(key, args))
}

// warn
fun Logger.warnKey(key: String) {
    warn(LANG.translate(key))
}

fun Logger.warnKey(key: String, vararg args: Any) {
    warn(LANG.translate(key, args))
}

// error
fun Logger.errorKey(key: String, e: Throwable) {
    error(LANG.translate(key), e)
}

fun Logger.errorKey(key: String) {
    error(LANG.translate(key))
}

fun Logger.errorKey(key: String, vararg args: Any, e: Throwable) {
    error(LANG.translate(key, args), e)
}

fun Logger.errorKey(key: String, vararg args: Any) {
    error(LANG.translate(key, args))
}
// endregion

inline fun <T> T.applyIf(condition: Boolean, applyFunc: (T) -> T): T {
    if (condition) {
        applyFunc.invoke(this)
    }
    return this
}