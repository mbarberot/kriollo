package com.gitlab.mbarberot.kriollo.generator.kotlin

import com.gitlab.mbarberot.kriollo.generator.base.extensions.TemplatedExtension
import com.gitlab.mbarberot.kriollo.generator.maven.MavenPluginExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

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
