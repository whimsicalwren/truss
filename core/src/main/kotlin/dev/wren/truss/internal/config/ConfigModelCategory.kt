package dev.wren.truss.internal.config


data class ConfigModelCategory(
    val title: String,
    val children: MutableMap<String, ConfigNode> = linkedMapOf()
) : ConfigNode {

    fun addEntry(name: String, entry: ConfigModelEntry<*>) {
        children[name] = entry
    }

    fun addCategory(cat: ConfigModelCategory) {
        children[cat.title] = cat
    }

    fun forEach(category: List<String> = emptyList(), callback: (List<String>, ConfigModelEntry<*>) -> Unit) {
        for ((_, node) in children) {
            if (node is ConfigModelEntry<*>) {
                callback(category, node)
            } else if (node is ConfigModelCategory) {
                node.forEach(category + node.title, callback)
            }
        }
    }
}

sealed interface ConfigNode

@Target(AnnotationTarget.FIELD)
annotation class ConfigCategory(
    val title: String
)