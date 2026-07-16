package com.gitlab.mbarberot.kriollo.services.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.gitlab.mbarberot.kriollo.KriolloException
import com.gitlab.mbarberot.kriollo.configuration.kdl.fromKdl
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.DeprecationHandler
import com.gitlab.mbarberot.kriollo.spi.configuration.ConfigurationCustomizer
import kotlin.reflect.KClass

class DefaultConfigurationService(
    configurationString: String,
    val customizers: List<ConfigurationCustomizer> = listOf(),
    val deprecationHandler: DeprecationHandler = DeprecationHandler { message -> println("[!] $message")}
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

        val data = if(config.isObject()) { config.toMap().getValue(key) } else { config.toMap() }

        return toPojo(data, klass.java)
    }

    override fun check() {
        customizers.forEach { it.check(rootNode, deprecationHandler) }
    }

    private fun getConfigKey(klassName: String): String {
        return klassName
            .removeSuffix("Config")
            .removeSuffix("Configuration")
            .replaceFirstChar { it.lowercaseChar() }
    }

    private fun <T> toPojo(data:Any, clazz: Class<T>): T {
        return objectMapper.convertValue(data, clazz)
    }

}