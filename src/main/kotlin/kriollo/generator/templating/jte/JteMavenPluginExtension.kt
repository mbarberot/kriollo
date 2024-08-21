package kriollo.generator.templating.jte

import kriollo.generator.base.extensions.TemplatedExtension
import kriollo.generator.maven.MavenPluginExtension
import kriollo.services.provider.ServiceProvider

class JteMavenPluginExtension(
    serviceProvider: ServiceProvider,
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {

    override fun getTemplatePath(): String = "generator/templating/jte/maven-plugin.kte"

    override fun getAllTemplateData(): List<Any> {
        val (_, _, _, version, sourceDirectory, contentType) = serviceProvider.configuration.templating.jte
        return listOf(
            JteMavenPluginModel(version, sourceDirectory, contentType)
        )
    }
}