package kriollo

import kriollo.configuration.*
import kriollo.generator.script.initBuild
import kriollo.generator.git.initGit
import kriollo.generator.maven.initMaven
import kriollo.generator.nix.initNixFile
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
                version = "3.1.12"
            )
        )
    )

    if (buildCommand == args[0]) {

        initBuild(codegenConfiguration)
        initMainScript(codegenConfiguration)

        if(codegenConfiguration.nix.enabled) {
            initNixFile()
        }

        if(codegenConfiguration.git.enabled) {
            initGit()
        }

        if(codegenConfiguration.maven.enabled) {
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

//TODO setup first codegen
//TODO read configuration from a file
//TODO setup tests to start TDD loops => require Maven (JUnit)
//TODO setup logging instead of println
//TODO use picocli
//TODO handle file deletion => probably need to track generated files
//TODO -lock file to track generated files


