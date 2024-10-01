package com.gitlab.mbarberot.kriollo.generator.kotlin

import com.gitlab.mbarberot.kriollo.generator.maven.MavenPropertiesExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class KotlinMavenPropertiesExtension(
    val serviceProvider: ServiceProvider,
) : MavenPropertiesExtension {

    override fun provide(): List<Pair<String, String>> {
        return listOf(
            Pair("kotlin.code.style", "official"),
            Pair("kotlin.compiler.jvmTarget", serviceProvider.configuration.java.version),
        )
    }

}
