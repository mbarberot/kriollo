package kriollo

import com.fasterxml.jackson.dataformat.toml.TomlMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import gg.jte.ContentType
import gg.jte.TemplateEngine
import kriollo.configuration.CodegenConfiguration
import kriollo.services.filesystem.DefaultFileSystemService
import kriollo.services.provider.DefaultServiceProvider
import kriollo.services.templating.DefaultTemplatingService
import kriollo.services.provider.ServiceProvider
import kriollo.features.Generate
import java.io.File

private fun readConfiguration(configurationFilePath: String): CodegenConfiguration {
    val mapper = TomlMapper()
    mapper.registerModule(kotlinModule())
    return mapper.readValue(File(configurationFilePath), CodegenConfiguration::class.java)
}

object Kriollo {
    val version = "0.1.0"
}

fun main(args: Array<String>) {

    println(
        """
            
        Welcome to Kriollo
        
    """.trimIndent()
    )

    val configuration = readConfiguration("./codegen/codegen.toml")

    val buildCommand = "build"

    if (args.isEmpty()) {
        showHelp(buildCommand)
        return
    }

    val serviceProvider: ServiceProvider = DefaultServiceProvider(
        DefaultFileSystemService(),
        DefaultTemplatingService(
            TemplateEngine.createPrecompiled(ContentType.valueOf(configuration.templating.jte.contentType)),
        )
    )

    if (buildCommand == args[0]) {
        Generate(configuration, serviceProvider).generate()
        return
    }

    showHelp(buildCommand)
}

fun showHelp(buildCommand: String) {
    println(
        """
        
            Usage : 
                $buildCommand : generate or regenerate the project
            
        """.trimIndent()
    )
}

//TODO: feat(generator): handle old obsolete files using the -lock file

//TODO: feat(cli): use picocli
//TODO: refactor(di): add configuration service to allow a global access to the configuration and unify signatures

//TODO: feat(plugin): use a ServiceProvider (from JDK) to build dynamic module registration
//TODO: feat(logs): add Logging framework

//TODO: feat(maven): set source and test directories using an extension (only one || error)
//TODO: feat(maven): artifact coordinates
//TODO: feat(maven): use ${config.project.name} for the final name
//TODO: refactor(jte): use a base template for java classes
//TODO: feat(config): check configuration (ie: JUnit requires Maven or Gradle, ...)
//TODO: feat(config): split configuration file
//TODO: feat(justfile): add justfile support
//TODO: feat(config): handle version (maven filtering ? how to handle module/plugins ?)
//TODO: feat(readme): add readme support with custom title/description/sections
//TODO: feat(dockerfile): generate dockerfile
