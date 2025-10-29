package com.gitlab.mbarberot.kriollo.services.configuration

import dev.kdl.KdlNode

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
        val childMap = buildMap {
            nodes.forEach { node -> putAll(node.toMap()) }
        }
        return mapOf(Pair(name, childMap))
    }
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
}

class ArrayValue(private var value: String) : ArrayItem {
    override fun value() = value
    override fun setValue(value: String) {
        this.value = value
    }
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
}


fun build(kdlNodes: List<KdlNode>): List<NamedNode> {
    return buildList {
        kdlNodes.forEach { kdlNode ->
            when {
                kdlNode.children().isNotEmpty() -> add(toComplexNode(kdlNode))
                kdlNode.arguments().isNotEmpty() -> add(toValueNode(kdlNode))
            }
        }
    }
}

val LIST_ITEM = listOf("*", "-")

private fun toValueNode(kdlNode: KdlNode): ValueNode =
    ValueNode(kdlNode.name, kdlNode.arguments.first().value().toString())

private fun toComplexNode(kdlNode: KdlNode): NamedNode =
    if (LIST_ITEM.contains(kdlNode.children.first().name)) toArrayNode(kdlNode) else toObjectNode(kdlNode)

private fun toObjectNode(kdlNode: KdlNode): ObjectNode = ObjectNode(kdlNode.name, build(kdlNode.children))

private fun toArrayNode(kdlNode: KdlNode): ArrayNode = ArrayNode(kdlNode.name, buildItem(kdlNode.children))

private fun buildItem(kdlNodes: List<KdlNode>): List<ArrayItem> {
    return buildList {
        kdlNodes.forEach { kdlNode ->
            when {
                kdlNode.children().isNotEmpty() -> add(toArrayObject(kdlNode))
                kdlNode.arguments.isNotEmpty() -> add(toArrayValue(kdlNode))
            }
        }
    }
}

private fun toArrayValue(kdlNode: KdlNode): ArrayValue = ArrayValue(kdlNode.arguments.first().value().toString())
private fun toArrayObject(kdlNode: KdlNode): ArrayObject = ArrayObject(build(kdlNode.children))
