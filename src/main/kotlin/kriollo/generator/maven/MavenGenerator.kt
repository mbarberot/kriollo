package kriollo.generator.maven

import gg.jte.output.StringOutput
import kriollo.Kriollo
import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.Generator
import kriollo.generator.utils.createDirectories
import kriollo.generator.utils.initFile

class MavenGenerator : Generator {

    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.maven.enabled
    }

    override fun execute(configuration: CodegenConfiguration) {
        createPomFile(configuration)
        createDirectories(
            "./src/main/kotlin",
            "./src/test/kotlin",
        )
    }

    private fun createPomFile(configuration: CodegenConfiguration) {
        val pomTemplate = generateWithJte(configuration)

        initFile("pom.xml", pomTemplate)
    }

    private fun generateWithJte(configuration: CodegenConfiguration): String {
        val output = StringOutput()
        Kriollo.templateEngine.render( // TODO Inject the template engine
            "generator/maven/pom.kte",
            PomModel(
                configuration.project.mainClass,
                findDependencies(configuration),
                JteModel(
                    // TODO : do not hardcode plugins
                    configuration.templating.jte.version,
                    configuration.templating.jte.sourceDirectory,
                    configuration.templating.jte.contentType,
                )
            ),
            output
        )
        return output.toString()
    }

    private fun findDependencies(configuration: CodegenConfiguration): List<JavaArtifact> {
        return buildList {
            val jte = configuration.templating.jte
            if (jte.enabled) {
                addAll(jte.getArtifacts(configuration))
            }
        }
    }
}
