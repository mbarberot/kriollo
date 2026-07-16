package com.gitlab.mbarberot.kriollo.generator.maven

import com.gitlab.mbarberot.kriollo.generator.base.extensions.TemplatedExtension
import com.gitlab.mbarberot.kriollo.generator.project.ProjectConfiguration
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.utils.SourceUtils

class MavenAssemblyPluginExtension(
    serviceProvider: ServiceProvider,
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {

    override fun getTemplatePath(): String {
        return "generator/maven/maven-assembly-plugin.kte"
    }

    override fun getAllTemplateData(): List<Any> {
        val projectConfiguration = serviceProvider.configurationService.get(ProjectConfiguration::class)

        return listOf(
            MavenAssemblyPluginModel(
                mainClass = SourceUtils.getRootPackage(projectConfiguration) + projectConfiguration.mainClass
            )
        )
    }

}
