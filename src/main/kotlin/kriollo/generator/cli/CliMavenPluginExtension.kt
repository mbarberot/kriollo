package kriollo.generator.cli

import kriollo.generator.base.extensions.TemplatedExtension
import kriollo.generator.maven.MavenPluginExtension
import kriollo.services.provider.ServiceProvider

class CliMavenPluginExtension(
    serviceProvider: ServiceProvider,
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {

    override fun getTemplatePath(): String {
        return "generator/cli/maven-plugin.kte"
    }

    override fun getAllTemplateData(): List<Any> {
        return listOf(
            CliMavenPluginModel(mainClass = serviceProvider.configuration.project.mainClass)
        )
    }

}
