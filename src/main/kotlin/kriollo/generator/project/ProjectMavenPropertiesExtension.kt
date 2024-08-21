package kriollo.generator.project

import kriollo.generator.maven.MavenPropertiesExtension
import kriollo.services.provider.ServiceProvider

class ProjectMavenPropertiesExtension(
    val serviceProvider: ServiceProvider
) : MavenPropertiesExtension {
    override fun provide(): List<Pair<String, String>> {
        return listOf(
            Pair("project.build.sourceEncoding", "UTF-8")
        )
    }
}
