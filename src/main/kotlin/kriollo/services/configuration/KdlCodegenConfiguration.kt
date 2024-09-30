package kriollo.services.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import kdl.objects.KDLDocument
import kdl.parse.KDLParser
import kriollo.configuration.CodegenConfiguration
import java.io.FileReader

fun <T> mapToClass(reader: FileReader, clazz: Class<T>): T {
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

fun <T> toPojo(data: Map<String, Any>, clazz: Class<T>): T {
    val objectMapper = ObjectMapper()
    objectMapper.registerModule(kotlinModule())
    return objectMapper.convertValue(data, clazz)
}

fun toMap(document: KDLDocument): Map<String, Any> {
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

fun toList(document: KDLDocument): List<Any> {
    return buildList {
        document.nodes.forEach { node ->
            when {
                node.child.isPresent -> add(toMap(node.child.get()))
                node.args.isNotEmpty() -> add(node.args.first().asString.value)
            }
        }
    }
}

fun isList(document: KDLDocument): Boolean {
    if(document.nodes.isEmpty()) {
        return false
    }

    return listOf("-", "*").any { it == document.nodes.first().identifier }
}

fun readKdlConfiguration(path: String): CodegenConfiguration {
    return mapToClass(FileReader(path), BasicCodegenConfiguration::class.java)
}

class KdlCodegenConfiguration(
    configurationFilePath: String,
) : CodegenConfiguration by readKdlConfiguration(configurationFilePath)