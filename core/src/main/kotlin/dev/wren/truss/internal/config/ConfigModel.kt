package dev.wren.truss.internal.config

import com.electronwill.nightconfig.core.CommentedConfig

import java.lang.reflect.Field
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField

data class ConfigModel(val root: ConfigModelCategory) {


    fun update(config: CommentedConfig) {
        root.forEach { category, node ->
            val forgeKey = (category + node.name).joinToString(".")
            config.get<Any>(forgeKey)?.let { newValue ->
                val defaultValue = node.default
                if (defaultValue != null) {
                    val convertedValue = when {
                        defaultValue is Float && newValue is Double -> newValue.toFloat()
                        defaultValue is Enum<*> -> {
                            when (newValue) {
                                is String -> {
                                    // Convert string name to enum instance
                                    @Suppress("UNCHECKED_CAST")
                                    val enumConstants = defaultValue.declaringJavaClass.enumConstants as Array<Enum<*>>
                                    enumConstants.find { it.name == newValue }
                                }

                                is Enum<*> -> newValue
                                else -> null
                            }
                        }

                        defaultValue::class.isInstance(newValue) -> newValue
                        else -> null
                    }

                    if (convertedValue != null) {
                        @Suppress("UNCHECKED_CAST")
                        (node as ConfigModelEntry<Any>).setValue(convertedValue)
                    }
                }
            }
        }
    }

    companion object {
        fun build(root: Any) =
            ConfigModel(buildConfigTree(root, "root"))

        private fun buildConfigTree(obj: Any, title: String): ConfigModelCategory {
            val root = ConfigModelCategory(title)

            for (member in obj::class.memberProperties) {
                val field = member.javaField ?: continue
                field.isAccessible = true


                val category = getCategory(field, obj)
                if (category != null) {
                    root.addCategory(category)
                    continue
                }

                val name = member.name
                val entry = getEntry(field, name, obj)
                if (entry != null) {
                    root.addEntry(name, entry)
                }
            }

            return root
        }

        private fun getEntry(
            field: Field,
            name: String,
            obj: Any,
        ) = field.getAnnotation(ConfigEntry::class.java)?.let { annotation ->
            ConfigModelEntry.build(
                getValue = { field.get(obj) as Any },
                setValue = { v: Any -> field.set(obj, v) },
                min = annotation.min,
                max = annotation.max,
                name = name,
                description = annotation.description
            )
        }

        private fun getCategory(
            field: Field,
            obj: Any,
        ) = field.getAnnotation(ConfigCategory::class.java)?.let { annotation ->
            buildConfigTree(field.get(obj), annotation.title)
        }
    }
}
