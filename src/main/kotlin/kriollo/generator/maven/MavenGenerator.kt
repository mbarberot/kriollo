package kriollo.generator.maven

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.Generator
import kriollo.generator.utils.ServiceProvider
import kriollo.generator.utils.TemplatingService

class MavenGenerator : Generator {

    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.maven.enabled
    }

    override fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) {
        serviceProvider.fileSystem.createFile("pom.xml", generateContent(configuration, serviceProvider.templatingService))
        serviceProvider.fileSystem.createDirectory("./src/main/kotlin")
        serviceProvider.fileSystem.createDirectory("./src/test/kotlin")
    }

    private fun generateContent(configuration: CodegenConfiguration, templatingService: TemplatingService): String {
        val templatePath = "generator/maven/pom.kte"
        val templateData = PomModel(
            configuration.project.mainClass,
            findDependencies(configuration),
            kotlin = KotlinModel(
                configuration.kotlin.version
            ),
            jte = JteModel(
                // TODO : do not hardcode plugins
                configuration.templating.jte.version,
                configuration.templating.jte.sourceDirectory,
                configuration.templating.jte.contentType,
            )
        )

        return templatingService.renderToString(templatePath, templateData)
    }

    private fun findDependencies(configuration: CodegenConfiguration): List<JavaArtifact> {
        return buildList {
            val kotlin = configuration.kotlin
            if(kotlin.enabled) {
            addAll(kotlin.getArtifacts(configuration))
            }

            val jte = configuration.templating.jte
            if (jte.enabled) {
                addAll(jte.getArtifacts(configuration))
            }

            addAll(configuration.project.dependencies)
        }
    }
}
