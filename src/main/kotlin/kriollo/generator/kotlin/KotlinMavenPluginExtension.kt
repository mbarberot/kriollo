package kriollo.generator.kotlin

import kriollo.generator.base.extensions.TemplatedExtension
import kriollo.generator.maven.MavenPluginExtension
import kriollo.services.provider.ServiceProvider

class KotlinMavenPluginExtension(
    serviceProvider: ServiceProvider
): MavenPluginExtension, TemplatedExtension(serviceProvider) {

    override fun getTemplatePath(): String = "generator/kotlin/maven-plugin.kte"

    override fun getAllTemplateData(): List<Any> {
        return listOf(
            KotlinMavenPluginModel(
                serviceProvider.configuration.kotlin.version
            )
        )
    }
}
