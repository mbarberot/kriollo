package kriollo.generator.kotlin

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.maven.MavenPropertiesExtension
import kriollo.generator.utils.ServiceProvider

class KotlinMavenPropertiesExtension(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) :
    MavenPropertiesExtension {
    override fun provide(): List<Pair<String, String>> {
        return listOf(
                Pair("kotlin.code.style", "official"),
                Pair("kotlin.compiler.jvmTarget", "21"),
        )
    }

}
