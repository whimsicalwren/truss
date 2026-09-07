package dev.wren.truss.internal.config


data class ConfigModelEntry<T>(
    val getValue: () -> T,
    val setValue: (T) -> Unit,
    val min: T?,
    val max: T?,
    val name: String,
    val description: String?
) : ConfigNode {

    val default = getValue()

    companion object {
        fun <T> build(
            getValue: () -> T,
            setValue: (T) -> Unit,
            min: Double,
            max: Double,
            name: String,
            description: String
        ) = ConfigModelEntry(
            getValue = getValue,
            setValue = setValue,
            min = processBoundingValue(getValue(), min),
            max = processBoundingValue(getValue(), max),
            name = name,
            description = description.takeIf { it.isNotEmpty() }
        )

        @Suppress("UNCHECKED_CAST")
        private fun <T> processBoundingValue(value: T, bound: Double): T? {
            return if (bound.isNaN()) {
                null
            } else {
                when (value) {
                    is Int -> bound.toInt() as T
                    is Double -> bound as T
                    is Float -> bound.toFloat() as T
                    is Long -> bound.toLong() as T
                    else -> null
                }
            }
        }
    }
}

@Target(AnnotationTarget.FIELD)
annotation class ConfigEntry(
    val description: String = "",
    val min: Double = Double.NaN,
    val max: Double = Double.NaN
)