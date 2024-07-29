package kriollo.generator.test

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.extensions.TemplatedExtension
import kriollo.generator.maven.MavenPluginExtension
import kriollo.generator.maven.SimpleMavenPluginModel
import kriollo.generator.utils.ServiceProvider

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
