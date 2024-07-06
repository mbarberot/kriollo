package kriollo.generator.script

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.generator.utils.createDirectories
import kriollo.generator.utils.initFile

class BuildScriptGenerator: Generator {

    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.scripts.build.enabled
    }

    override fun execute(configuration: CodegenConfiguration) {
        val customScript = configuration.scripts.build.customSteps.joinToString("\n") { (step, command) ->
            """
            if [[ ${'$'}? > 0 ]]
            then
                echo 
                echo "> ! Build failed"
                exit 1
            else 
                echo 
                echo "> $step"
                $command
            fi
        """
        }
        val buildScriptTemplate = """
            # /!\ warning
            # this file is generated by kriollo, do not edit it manually
            
            echo "> Building jar"
            mvn clean package -DskipTests
            
            $customScript
            
            if [[ ${'$'}? > 0 ]]
            then
                echo
                echo "! Build failed"
                exit 1
            else
                echo 
                echo "> Build successful"
                exit 0
            fi
        """.trimIndent()

        createDirectories("./build")
        initFile("./build/build.sh", buildScriptTemplate, isScript = true)
    }

}