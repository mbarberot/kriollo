package kriollo.services.configuration

import com.fasterxml.jackson.dataformat.toml.TomlMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import kriollo.configuration.CodegenConfiguration
import java.io.File

private fun readConfiguration(configurationFilePath: String): CodegenConfiguration {
    val mapper = TomlMapper()
    mapper.registerModule(kotlinModule())
    return mapper.readValue(File(configurationFilePath), BasicCodegenConfiguration::class.java)
}

class TomlCodegenConfiguration(
    configurationFilePath: String,
) : CodegenConfiguration by readConfiguration(configurationFilePath)
