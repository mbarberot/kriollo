package kriollo

import com.fasterxml.jackson.dataformat.toml.TomlMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import gg.jte.ContentType
import gg.jte.TemplateEngine
import kriollo.configuration.CodegenConfiguration
import kriollo.generator.CodeGenerators
import kriollo.generator.CoreModules
import kriollo.generator.KriolloModules
import kriollo.generator.utils.FilesytemService
import kriollo.generator.utils.ServiceProvider
import kriollo.generator.utils.TemplatingService
import java.io.File

private fun readConfiguration(configurationFilePath: String): CodegenConfiguration {
    val mapper = TomlMapper()
    mapper.registerModule(kotlinModule())
    return mapper.readValue(File(configurationFilePath), CodegenConfiguration::class.java)
}

object Kriollo {
    val version = "0.1.0" // TODO inject this at build time
}

fun main(args: Array<String>) {

    println(
        """
            
        Welcome to Kriollo
        
    """.trimIndent()
    )

    val codegenConfiguration = readConfiguration("./codegen/codegen.toml")

    val buildCommand = "build"

    if (args.isEmpty()) {
        showHelp(buildCommand)
        return
    }

    val serviceProvider = ServiceProvider(
        // TODO : add configuration service to allow a global access to the configuration and unify signatures
        FilesytemService(),
        TemplatingService(
            TemplateEngine.createPrecompiled(ContentType.valueOf(codegenConfiguration.templating.jte.contentType)),
        )
    )

    val generators = CodeGenerators(
        buildList {
            addAll(CoreModules().getModules(codegenConfiguration, serviceProvider))
            addAll(KriolloModules().getModules(codegenConfiguration, serviceProvider))
        },
    )

    if (buildCommand == args[0]) {
        generators.execute(codegenConfiguration, serviceProvider);
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
    return
}

//TODO setup tests to start TDD loops => require Maven (JUnit)
//TODO setup logging instead of println
//TODO use picocli
//TODO handle file deletion => probably need to track generated files
//TODO -lock file to track generated files
//TODO pom : source and test directories => how ?
//TODO pom : final name => config.project
//TODO pom : properties => extension => create the new extension code generator to make it easier to add an extension from toml file
//TODO config : the configuration file is getting pretty long, see if we can split it ? like a kind of inclusion, either built-in or home-made
/*
 * TODO config : generate the main configuration class => may require a lot of work to gather different classes all around
 * Maybe : move *Configuration into the same package to avoid the kinda hard import block generation
 * though it still requires to gather the configuration files
 */

/*
 * TODO : generate Generator base class and the extension
 *
 * [[components.kriollo-extensions]]
 * path: "kriollo/generator/base/extensions
 * availableExtensions: [
 *  { name = "GitIgnoreExtension", provides = "GitIgnoreRule", for = "kriollo.generator.git" },
 *  # ... and so on
 * ]
 */