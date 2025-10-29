package com.gitlab.mbarberot.kriollo.services.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.gitlab.mbarberot.kriollo.KriolloException
import com.gitlab.mbarberot.kriollo.configuration.kdl.fromKdl
import com.gitlab.mbarberot.kriollo.spi.configuration.ConfigurationCustomizer
import kotlin.reflect.KClass

class DefaultConfigurationService(
    configurationString: String,
    val customizers: List<ConfigurationCustomizer> = listOf(),
) : ConfigurationService {

    val rootNode = fromKdl(configurationString) // TODO : inject ?
    val objectMapper: ObjectMapper = ObjectMapper()

    init {
        objectMapper.registerModule(kotlinModule())
    }

    override fun <T : Any> get(klass: KClass<T>): T {
        val key = getConfigKey(klass.simpleName ?: throw KriolloException("Cannot get configuration key for $klass"))
        val config = rootNode.get(key) ?: throw KriolloException("Cannot get configuration for key $key")

        customizers
            .filter { it.accepts(klass) }
            .sortedBy { it.priority }
            .forEach { it.customize(rootNode) }


        return toPojo(config.toMap().getValue(key), klass.java)
    }

    private fun getConfigKey(klassName: String): String {
        return klassName
            .removeSuffix("Config")
            .replaceFirstChar { it.lowercaseChar() }
    }

    private fun <T> toPojo(data: Any, clazz: Class<T>): T {
        return objectMapper.convertValue(data, clazz)
    }

}