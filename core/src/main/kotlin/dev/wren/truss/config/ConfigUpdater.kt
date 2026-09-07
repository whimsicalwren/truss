package dev.wren.truss.config

import com.electronwill.nightconfig.core.CommentedConfig
import dev.wren.truss.internal.config.ConfigModel
import dev.wren.truss.internal.config.ConfigModelCategory
import dev.wren.truss.internal.config.ConfigModelEntry
import dev.wren.truss.internal.config.ConfigType
import net.neoforged.neoforge.common.ModConfigSpec
import java.util.EnumMap

object ConfigUpdater {

    @JvmStatic
    val forgeConfigValuesMap: HashMap<String, ModConfigSpec.ConfigValue<*>> = HashMap()

    private val configValueConsumer = { name: String, value: ModConfigSpec.ConfigValue<*> ->
        forgeConfigValuesMap[name] = value
    }

    @JvmStatic
    val pathAwareConfigValuesMap: EnumMap<ConfigType, HashMap<String, Pair<ConfigModelEntry<*>, ModConfigSpec.ConfigValue<*>>>> =
        EnumMap(ConfigType::class.java)

    private fun pathAwareConsumerFor(configType: ConfigType) =
        { path: List<String>, entry: ConfigModelEntry<*>, forgeValue: ModConfigSpec.ConfigValue<*> ->
            val path = if (path.size == 1) {
                listOf("General") + path
            } else {
                path
            }
            pathAwareConfigValuesMap.getOrPut(configType) { HashMap() }[sanitizeCategoryName(path.joinToString("."))] =
                Pair(entry, forgeValue)
        }

    private fun sanitizeCategoryName(category: String): String {
        return category.replace(" ", "").filter { it.isLetterOrDigit() || it == '.' }
    }

    private val serverConfig = buildConfigModel(TrussConfig.SERVER)
    private val commonConfig = buildConfigModel(TrussConfig.COMMON)
    private val clientConfig = buildConfigModel(TrussConfig.CLIENT)


    val SERVER_SPEC: ModConfigSpec = buildForgeConfigSpec(
        configCategory = serverConfig.root,
        builder = ModConfigSpec.Builder(),
        forgeConfigValueConsumer = configValueConsumer,
        pathAwareConsumer = pathAwareConsumerFor(ConfigType.SERVER),
    ).build()

    val COMMON_SPEC: ModConfigSpec = buildForgeConfigSpec(
        configCategory = commonConfig.root,
        builder = ModConfigSpec.Builder(),
        forgeConfigValueConsumer = configValueConsumer,
        pathAwareConsumer = pathAwareConsumerFor(ConfigType.COMMON),
    ).build()

    val CLIENT_SPEC: ModConfigSpec = buildForgeConfigSpec(
        configCategory = clientConfig.root,
        builder = ModConfigSpec.Builder(),
        forgeConfigValueConsumer = configValueConsumer,
        pathAwareConsumer = pathAwareConsumerFor(ConfigType.CLIENT),
    ).build()

    fun update(config: CommentedConfig) {
        serverConfig.update(config)
        commonConfig.update(config)
        clientConfig.update(config)
    }

    // region building stuff
    @JvmStatic
    fun buildConfigModel(annotatedConfigObject: Any) =
        ConfigModel.build(annotatedConfigObject)

    @JvmStatic
    fun buildForgeConfigSpec(
        configCategory: ConfigModelCategory,
        builder: ModConfigSpec.Builder,
        forgeConfigValueConsumer: (String, ModConfigSpec.ConfigValue<*>) -> Unit = { a, b -> },
        path: List<String> = emptyList(),
        pathAwareConsumer: (List<String>, ConfigModelEntry<*>, ModConfigSpec.ConfigValue<*>) -> Unit? = { a, b, c -> }
    ): ModConfigSpec.Builder {
        for ((_, node) in configCategory.children) {
            if (node is ConfigModelCategory) {
                builder.push(node.title)
                buildForgeConfigSpec(node, builder, forgeConfigValueConsumer, path + node.title, pathAwareConsumer)
                builder.pop()
            } else if (node is ConfigModelEntry<*>) {
                val value = defineNode(builder, node)
                forgeConfigValueConsumer.invoke(node.name, value)
                pathAwareConsumer.invoke(path + node.name, node, value)
            }
        }
        return builder
    }

    private fun defineNode(
        builder: ModConfigSpec.Builder,
        entry: ConfigModelEntry<*>
    ): ModConfigSpec.ConfigValue<*> {
        fun <T> define(v: T) = builder.define<T>(entry.name, v)

        @Suppress("UNCHECKED_CAST")
        fun <T : Enum<T>> defineEnum(builder: ModConfigSpec.Builder, value: Enum<*>): ModConfigSpec.EnumValue<*> {
            return builder.defineEnum(entry.name, value as T)
        }

        fun <T : Comparable<T>> defineNumeric(v: T) =
            if (entry.min == null || entry.max == null) {
                define(if (v is Float) v.toDouble() else v)
            } else {
                when (v) {
                    is Int -> builder.defineInRange(entry.name, v, entry.min as Int, entry.max as Int)
                    is Long -> builder.defineInRange(entry.name, v, entry.min as Long, entry.max as Long)
                    is Float -> builder.defineInRange(
                        entry.name,
                        v.toDouble(),
                        (entry.min as Float).toDouble(),
                        (entry.max as Float).toDouble()
                    )

                    is Double -> builder.defineInRange(entry.name, v, entry.min as Double, entry.max as Double)
                    else -> throw IllegalArgumentException("Non numeric type $v not accepted")
                }
            }


        entry.description?.let(builder::comment)

        return when (val v = entry.getValue()) {
            is Int -> defineNumeric(v)
            is Long -> defineNumeric(v)
            is Float -> defineNumeric(v)
            is Double -> defineNumeric(v)
            is Boolean -> define(v)
            is String -> define(v)
            is Enum<*> -> defineEnum(builder, v)
            else -> {
                throw IllegalArgumentException("invalid config type $v of class ${v?.javaClass}")
            }
        }
    }

    // endregion
}