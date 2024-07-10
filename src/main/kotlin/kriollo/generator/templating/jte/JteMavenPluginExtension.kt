package kriollo.generator.templating.jte

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.extensions.MavenPluginExtension
import kriollo.generator.base.extensions.TemplatedExtension
import kriollo.generator.utils.ServiceProvider

class JteMavenPluginExtension(
    val configuration: CodegenConfiguration,
    serviceProvider: ServiceProvider,
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {

    override fun getTemplatePath(): String = "generator/templating/jte/maven-plugin.kte"

    override fun getAllTemplateData(): List<Any> {
        val (_, _, _, version, sourceDirectory, contentType) = configuration.templating.jte
        return listOf(
            JteMavenPluginModel(version, sourceDirectory, contentType)
        )
    }
}