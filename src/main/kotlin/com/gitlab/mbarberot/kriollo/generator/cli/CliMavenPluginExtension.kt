package com.gitlab.mbarberot.kriollo.generator.cli

import com.gitlab.mbarberot.kriollo.generator.base.extensions.TemplatedExtension
import com.gitlab.mbarberot.kriollo.generator.maven.MavenPluginExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.utils.SourceUtils

class CliMavenPluginExtension(
    serviceProvider: ServiceProvider,
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {

    override fun getTemplatePath(): String {
        return "generator/cli/maven-plugin.kte"
    }

    override fun getAllTemplateData(): List<Any> {
        return listOf(
            CliMavenPluginModel(
                mainClass = SourceUtils.getRootPackage(serviceProvider.configuration) + serviceProvider.configuration.project.mainClass
            )
        )
    }

}
