package kriollo.generator.tests

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.extensions.TemplatedExtension
import kriollo.generator.maven.MavenPluginExtension
import kriollo.generator.maven.SimpleMavenPluginModel
import kriollo.services.provider.ServiceProvider

class SurefireMavenPluginExtension(
    val configuration: CodegenConfiguration,
    serviceProvider: ServiceProvider,
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {
    override fun getTemplatePath(): String {
        return "generator/maven/simple-maven-plugin.kte"
    }

    override fun getAllTemplateData(): List<Any> {
        return listOf(
            SimpleMavenPluginModel(
                artifactId = "maven-surefire-plugin",
                version = "2.22.2",
            )
        )
    }

}