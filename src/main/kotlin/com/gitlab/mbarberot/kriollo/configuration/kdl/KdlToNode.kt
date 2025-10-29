package com.gitlab.mbarberot.kriollo.configuration.kdl

import com.gitlab.mbarberot.kriollo.spi.configuration.ArrayItem
import com.gitlab.mbarberot.kriollo.spi.configuration.ArrayNode
import com.gitlab.mbarberot.kriollo.spi.configuration.ArrayObject
import com.gitlab.mbarberot.kriollo.spi.configuration.ArrayValue
import com.gitlab.mbarberot.kriollo.spi.configuration.NamedNode
import com.gitlab.mbarberot.kriollo.spi.configuration.ObjectNode
import com.gitlab.mbarberot.kriollo.spi.configuration.RootNode
import com.gitlab.mbarberot.kriollo.spi.configuration.ValueNode
import dev.kdl.KdlNode
import dev.kdl.parse.Kdl1Parser

fun fromKdl(configAsText: String) = parseKdl(Kdl1Parser().parse(configAsText).nodes)
private fun parseKdl(kdlNode: List<KdlNode>): RootNode = RootNode(build(kdlNode))

private fun build(kdlNodes: List<KdlNode>): List<NamedNode> {
    return buildList {
        kdlNodes.forEach { kdlNode ->
            when {
                kdlNode.children().isNotEmpty() -> add(toComplexNode(kdlNode))
                kdlNode.arguments().isNotEmpty() -> add(toValueNode(kdlNode))
            }
        }
    }
}

private fun toValueNode(kdlNode: KdlNode): ValueNode =
    ValueNode(kdlNode.name, kdlNode.arguments.first().value().toString())

private fun toComplexNode(kdlNode: KdlNode): NamedNode =
    if (listOf("*", "-").contains(kdlNode.children.first().name)) toArrayNode(kdlNode) else toObjectNode(kdlNode)

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