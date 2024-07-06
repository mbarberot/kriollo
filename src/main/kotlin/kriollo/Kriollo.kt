package kriollo

import kriollo.configuration.*
import kriollo.generator.CodeGenerators

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
        cli = CliConfiguration(
            enabled = true,
            script = MainScriptConfiguration(
                enabled = true,
                fileName = "kriollo",
                targetDirectory = "codegen",
                jarLocation = "codegen/kriollo.jar" // TODO : get jar name from project equivalent of maven's build.finalName
            ),
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

    val generators = CodeGenerators(codegenConfiguration)

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

//TODO read configuration from a file
//TODO setup tests to start TDD loops => require Maven (JUnit)
//TODO setup logging instead of println
//TODO use picocli
//TODO handle file deletion => probably need to track generated files
//TODO -lock file to track generated files
//TODO move kotlin elements from the pom into the pom generation