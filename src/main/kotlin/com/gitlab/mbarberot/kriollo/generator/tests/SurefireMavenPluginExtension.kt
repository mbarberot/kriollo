package com.gitlab.mbarberot.kriollo.generator.tests

import com.gitlab.mbarberot.kriollo.generator.base.extensions.TemplatedExtension
import com.gitlab.mbarberot.kriollo.generator.maven.MavenPluginExtension
import com.gitlab.mbarberot.kriollo.generator.maven.SimpleMavenPluginModel
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class SurefireMavenPluginExtension(
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