package com.gitlab.mbarberot.kriollo.services.configuration.kdl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationReader
import kdl.objects.KDLDocument
import kdl.parse.KDLParser
import java.io.FileReader

private fun <T> mapToClass(reader: FileReader, clazz: Class<T>): T {
    val parser = KDLParser();
    val document = parser.parse(reader)
    val data = toMap(document)
    return toPojo(data, clazz)
}

fun <T> mapToClass(kdl: String, clazz: Class<T>): T {
    val parser = KDLParser();
    val document = parser.parse(kdl)
    val data = toMap(document)
    return toPojo(data, clazz)
}

private fun <T> toPojo(data: Map<String, Any>, clazz: Class<T>): T {
    val objectMapper = ObjectMapper()
    objectMapper.registerModule(kotlinModule())
    return objectMapper.convertValue(data, clazz)
}

private fun toMap(document: KDLDocument): Map<String, Any> {
    return buildMap {
        document
            .nodes
            .forEach { node ->
                when {
                    node.child.isPresent -> {
                        when {
                            isList(node.child.get()) -> put(node.identifier, toList(node.child.get()))
                            else -> put(node.identifier, toMap(node.child.get()))
                        }
                    }
                    node.args.isNotEmpty() -> put(node.identifier, node.args.first().asString.value)
                }
            }
    }
}

private fun toList(document: KDLDocument): List<Any> {
    return buildList {
        document.nodes.forEach { node ->
            when {
                node.child.isPresent -> add(toMap(node.child.get()))
                node.args.isNotEmpty() -> add(node.args.first().asString.value)
            }
        }
    }
}

private fun isList(document: KDLDocument): Boolean {
    if(document.nodes.isEmpty()) {
        return false
    }

    return listOf("-", "*").any { it == document.nodes.first().identifier }
}

private fun <T>readKdlConfiguration(path: String, clazz: Class<T>): T {
    return mapToClass(FileReader(path), clazz)
}

class KdlConfigurationReader<T>(val path: String, val clazz: Class<T>): ConfigurationReader<T> {
    override fun read(): T = readKdlConfiguration(path, clazz)
}
