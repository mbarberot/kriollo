package kriollo.generator.maven

import gg.jte.TemplateEngine
import gg.jte.output.StringOutput
import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.Generator
import kriollo.generator.utils.ServiceProvider

class MavenGenerator : Generator {

    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.maven.enabled
    }

    override fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) {
        serviceProvider.fileSystem.createFile("pom.xml", generateContent(configuration, serviceProvider.templateEngine))
        serviceProvider.fileSystem.createDirectory("./src/main/kotlin")
        serviceProvider.fileSystem.createDirectory("./src/test/kotlin")
    }

    private fun generateContent(configuration: CodegenConfiguration, templateEngine: TemplateEngine): String {
        val output = StringOutput()
        templateEngine.render(
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
