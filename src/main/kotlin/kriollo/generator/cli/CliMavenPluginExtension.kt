package kriollo.generator.cli

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.extensions.MavenPluginExtension
import kriollo.generator.base.extensions.TemplatedExtension
import kriollo.generator.utils.ServiceProvider

class CliMavenPluginExtension(
    val configuration: CodegenConfiguration,
    serviceProvider: ServiceProvider,
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {

    override fun getTemplatePath(): String {
        return "generator/cli/maven-plugin.kte"
    }

    override fun getAllTemplateData(): List<Any> {
        return listOf(
            CliMavenPluginModel(mainClass = configuration.project.mainClass)
        )
    }

}
