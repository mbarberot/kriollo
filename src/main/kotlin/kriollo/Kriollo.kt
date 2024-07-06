package kriollo

import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.resolve.DirectoryCodeResolver
import kriollo.configuration.*
import kriollo.generator.CodeGenerators
import kriollo.generator.git.initGit
import kriollo.generator.maven.initMaven
import kriollo.generator.script.initBuild
import kriollo.generator.script.initMainScript
import java.nio.file.Path

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
        mainScript = MainScriptConfiguration(),
        buildScript = BuildScriptConfiguration(
            customSteps = listOf(
                BuildScriptCustomStepConfiguration("Copy jar into codegen dir", "cp target/kriollo.jar codegen/")
            )
        ),
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

        initBuild(codegenConfiguration)
        initMainScript(codegenConfiguration)


        if (codegenConfiguration.git.enabled) {
            initGit()
        }

        if (codegenConfiguration.maven.enabled) {
            initMaven(codegenConfiguration)
        }

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

//TODO configure jte-classes to go in target/ or add it to the gitignoreGenerator
//TODO inject templating engine and create it from the jte generator instead of global access

//TODO read configuration from a file
//TODO setup tests to start TDD loops => require Maven (JUnit)
//TODO setup logging instead of println
//TODO use picocli
//TODO handle file deletion => probably need to track generated files
//TODO -lock file to track generated files

//TODO template engine must be abstracted
//TODO move kotlin elements from the pom into the pom generation
//TODO remove old pom generation

object Kriollo {
    val templateEngine: TemplateEngine = TemplateEngine.create(
        DirectoryCodeResolver(Path.of("src/main/jte")),
        ContentType.Plain
    )
}

