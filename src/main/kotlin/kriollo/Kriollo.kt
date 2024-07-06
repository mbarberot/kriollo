package kriollo

import gg.jte.ContentType
import gg.jte.TemplateEngine
import kriollo.configuration.*
import kriollo.generator.CodeGenerators
import kriollo.generator.script.initMainScript

fun main(args: Array<String>) {

    println(
        """
            
        Welcome to Kriollo
        
    """.trimIndent()
    )

    val buildCommand = "build"

    if (args.isEmpty()) {
        showHelp(buildCommand)
        return
    }

    val codegenConfiguration = CodegenConfiguration(
        project = ProjectConfiguration(
            mainClass = "kriollo.KriolloKt"
        ),
        kotlin = KotlinConfiguration(
            enabled = true
        ),
        nix = NixConfiguration(
            enabled = true
        ),
        git = GitConfiguration(
            enabled = true
        ),
        scripts = ScriptsConfiguration(
            build = BuildScriptConfiguration(
                customSteps = listOf(
                    BuildScriptCustomStepConfiguration("Copy jar into codegen dir", "cp target/kriollo.jar codegen/")
                )
            ),
        ),
        mainScript = MainScriptConfiguration(),
        maven = MavenConfiguration(
            enabled = true
        ),
        templating = TemplatingConfiguration(
            enabled = true,
            jte = JteConfiguration(
                enabled = true,
                version = "3.1.12",
                contentType = "Plain"
            )
        )
    )

    val generators = CodeGenerators()

    if (buildCommand == args[0]) {
        generators.execute(codegenConfiguration)
        initMainScript(codegenConfiguration)
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

//TODO inject templating engine and create it from the jte generator instead of global access

//TODO read configuration from a file
//TODO setup tests to start TDD loops => require Maven (JUnit)
//TODO setup logging instead of println
//TODO use picocli
//TODO handle file deletion => probably need to track generated files
//TODO -lock file to track generated files

//TODO template engine must be abstracted
//TODO move kotlin elements from the pom into the pom generation

object Kriollo {
    val templateEngine: TemplateEngine = TemplateEngine.createPrecompiled(ContentType.Plain)
}

