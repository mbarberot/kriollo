package kriollo.generator.kotlin

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.maven.MavenPluginExtension
import kriollo.generator.base.extensions.TemplatedExtension
import kriollo.services.provider.ServiceProvider

class KotlinMavenPluginExtension(
    val configuration: CodegenConfiguration,
    serviceProvider: ServiceProvider
): MavenPluginExtension, TemplatedExtension(serviceProvider) {

    override fun getTemplatePath(): String = "generator/kotlin/maven-plugin.kte"

    override fun getAllTemplateData(): List<Any> {
        return listOf(
            KotlinMavenPluginModel(
                configuration.kotlin.version
            )
        )
    }
}
