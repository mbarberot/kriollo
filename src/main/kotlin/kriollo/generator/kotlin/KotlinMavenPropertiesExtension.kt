package kriollo.generator.kotlin

import kriollo.generator.maven.MavenPropertiesExtension
import kriollo.services.provider.ServiceProvider

class KotlinMavenPropertiesExtension(val serviceProvider: ServiceProvider) :
    MavenPropertiesExtension {
    override fun provide(): List<Pair<String, String>> {
        return listOf(
                Pair("kotlin.code.style", "official"),
                Pair("kotlin.compiler.jvmTarget", "21"),
        )
    }

}
