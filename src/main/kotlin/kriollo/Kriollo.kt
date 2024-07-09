package kriollo

import com.fasterxml.jackson.dataformat.toml.TomlMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import kriollo.configuration.CodegenConfiguration
import kriollo.generator.CodeGenerators
import kriollo.generator.CoreModules
import java.io.File

private fun readConfiguration(configurationFilePath: String): CodegenConfiguration {
    val mapper = TomlMapper()
    mapper.registerModule(kotlinModule())
    return mapper.readValue(File(configurationFilePath), CodegenConfiguration::class.java)
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

    val generators = CodeGenerators(
        codegenConfiguration,
        buildList {
            addAll(CoreModules().getModules(codegenConfiguration))
        }
    )

    if (buildCommand == args[0]) {
        generators.execute(codegenConfiguration)
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
//TODO move kotlin elements from the pom into the pom generation