package com.gitlab.mbarberot.kriollo.generator.templating.jte

import com.gitlab.mbarberot.kriollo.generator.base.extensions.TemplatedExtension
import com.gitlab.mbarberot.kriollo.generator.maven.MavenPluginExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class JteMavenPluginExtension(
    serviceProvider: ServiceProvider,
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {

    override fun getTemplatePath(): String = "generator/templating/jte/maven-plugin.kte"

    override fun getAllTemplateData(): List<Any> {
        val (_, _, _, version, sourceDirectory, contentType) = serviceProvider.configuration.project.libs.jte
        return listOf(
            JteMavenPluginModel(version, sourceDirectory, contentType)
        )
    }
}