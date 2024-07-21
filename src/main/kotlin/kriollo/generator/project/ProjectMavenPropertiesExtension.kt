package kriollo.generator.project

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.maven.MavenPropertiesExtension
import kriollo.generator.utils.ServiceProvider

class ProjectMavenPropertiesExtension(
    configuration: CodegenConfiguration,
    serviceProvider: ServiceProvider
) : MavenPropertiesExtension {
    override fun provide(): List<Pair<String, String>> {
        return listOf(
            Pair("project.build.sourceEncoding", "UTF-8")
        )
    }
}
