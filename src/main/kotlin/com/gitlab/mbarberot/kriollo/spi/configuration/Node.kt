package com.gitlab.mbarberot.kriollo.spi.configuration

interface Node {
    fun get(key: String): Node? {
        throw UnsupportedOperationException()
    }

    fun setValue(value: String) {
        throw UnsupportedOperationException()
    }

    fun setValue(name: String, value: String) {
        throw UnsupportedOperationException()
    }

    fun value(): String? {
        throw UnsupportedOperationException()
    }

    fun remove(key: String) {
        throw UnsupportedOperationException()
    }

    fun toMap(): Map<String, Any> {
        throw UnsupportedOperationException()
    }

    fun isObject(): Boolean {
        throw UnsupportedOperationException()
    }

}

interface NamedNode : Node {
    val name: String
}

interface ArrayItem : Node

fun extractPath(key: String): List<String> = key.split(".")
fun rebuildPath(elements: List<String>): String = elements.filterIndexed { index, _ -> index > 0 }.joinToString(".")

open class ObjectNode(override val name: String, nodes: List<NamedNode>) : NamedNode {
    val nodes: MutableList<NamedNode> = nodes.toMutableList()

    override fun get(key: String): Node? {
        val path = extractPath(key)
        val node = nodes.find { node -> node.name == path[0] }
        return if (path.size == 1) {
            node
        } else {
            node?.get(rebuildPath(path))
        }
    }

    override fun remove(key: String) {
        nodes.removeIf { node -> node.name == key }
    }

    override fun setValue(name: String, value: String) {
        nodes.add(ValueNode(name, value))
    }

    override fun toMap(): Map<String, Any> {
        return mapOf(Pair(name, buildMap {
            nodes.forEach { node ->
                putAll(node.toMap())
            }
        }
        ))
    }

    override fun isObject(): Boolean = true
}

class RootNode(nodes: List<NamedNode>) : ObjectNode("$", nodes) {
    override fun get(key: String): Node? {
        return super.get(key.removePrefix("$."))
    }
}

class ValueNode(override val name: String, private var value: String) : NamedNode {
    override fun value() = value
    override fun setValue(value: String) {
        this.value = value
    }

    override fun toMap(): Map<String, Any> {
        return mapOf(Pair(name, value))
    }
}

class ArrayNode(override val name: String, val items: List<ArrayItem>) : NamedNode {
    override fun get(key: String): Node? {
        val path = extractPath(key)
        val index = path[0].removePrefix("[").removeSuffix("]").toInt()
        val node = if (items.size <= index) null else items[index]
        return if (path.size == 1) {
            node
        } else {
            node?.get(rebuildPath(path))
        }
    }

    override fun toMap(): Map<String, Any> {
        return mapOf(Pair(name, items.map {
            when {
                it.isObject() -> it.toMap()
                else -> it.value()
            }
        }))
    }

    override fun isObject(): Boolean = false
}

class ArrayValue(private var value: String) : ArrayItem {
    override fun value() = value
    override fun setValue(value: String) {
        this.value = value
    }

    override fun isObject(): Boolean = false
}

class ArrayObject(nodes: List<NamedNode>) : ArrayItem {
    val nodes = nodes.toMutableList()

    override fun get(key: String): Node? {
        val path = extractPath(key)
        val node = nodes.find { node -> node.name == path[0] }
        return if (path.size == 1) {
            node
        } else {
            node?.get(rebuildPath(path))
        }
    }

    override fun setValue(name: String, value: String) {
        nodes.add(ValueNode(name, value))
    }

    override fun remove(key: String) {
        nodes.removeIf { node -> node.name == key }
    }

    override fun toMap(): Map<String, Any> {
        return buildMap {
            nodes.forEach { node ->
                node.value()?.let { value ->
                    put(node.name, value)
                }
            }
        }
    }

    override fun isObject(): Boolean = true
}