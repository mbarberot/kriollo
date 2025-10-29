package com.gitlab.mbarberot.kriollo.services.configuration.kdl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationReader
import dev.kdl.KdlNode
import dev.kdl.parse.Kdl1Parser
import java.nio.file.Path
import kotlin.io.path.Path

private fun <T> kdlFileToClass(path: Path, clazz: Class<T>): T {
    val parser = Kdl1Parser()
    val document = parser.parse(path)
    val data = toMap(document.nodes)
    return toPojo(data, clazz)
}

fun <T> kdlStringToClass(kdl: String, clazz: Class<T>): T {
    val data = kdlStringToMap(kdl)
    return toPojo(data, clazz)
}

fun kdlStringToMap(kdl: String): Map<String, Any> {
    val parser = Kdl1Parser()
    val document = parser.parse(kdl)
    val data = toMap(document.nodes)
    return data
}

private fun <T> toPojo(data: Map<String, Any>, clazz: Class<T>): T {
    val objectMapper = ObjectMapper()
    objectMapper.registerModule(kotlinModule())
    return objectMapper.convertValue(data, clazz)
}

fun toMap(nodes: List<KdlNode>): Map<String, Any> {
    return buildMap {
        nodes
            .forEach { node ->
                when {
                    node.children.isNotEmpty() -> {
                        when {
                            isList(node.children) -> put(node.name, toList(node.children()))
                            else -> put(node.name, toMap(node.children()))
                        }
                    }

                    node.arguments.isNotEmpty() -> put(node.name, node.arguments.first().value())
                }
            }
    }
}

private fun toList(nodes: List<KdlNode>): List<Any> {
    return buildList {
        nodes.forEach { node ->
            when {
                node.children.isNotEmpty() -> add(toMap(node.children))
                node.arguments.isNotEmpty() -> add(node.arguments.first().value())
            }
        }
    }
}

private fun isList(nodes: List<KdlNode>): Boolean {
    if (nodes.isEmpty()) {
        return false
    }

    return listOf("-", "*").any { it == nodes.first().name }
}

private fun <T> readKdlConfiguration(path: String, clazz: Class<T>): T {
    return kdlFileToClass(Path(path), clazz)
}

class KdlConfigurationReader<T>(val path: String, val clazz: Class<T>) : ConfigurationReader<T> {
    override fun read(): T = readKdlConfiguration(path, clazz)
}
