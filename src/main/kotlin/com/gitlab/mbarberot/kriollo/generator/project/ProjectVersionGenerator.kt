package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationService
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.utils.NamingUtils
import com.gitlab.mbarberot.kriollo.utils.SourceUtils

class ProjectVersionGenerator(
    val serviceProvider: ServiceProvider,
    val configurationService: ConfigurationService = serviceProvider.configurationService,
) : TemplatedFileGenerator() {

    override fun getTemplatePath(): String {
        return "generator/project/ProjectVersion.${SourceUtils.getSourceExtension(configurationService)}.kte"
    }

    override fun getTemplateData(): Any {
        val rootPackage = SourceUtils.getRootPackage(serviceProvider.configuration, withTrailingDot = false)
        return ProjectVersionClassModel(
            version = serviceProvider.configuration.project.version,
            name = getClassName(),
            namespace = rootPackage,
            rootPackage = rootPackage,
        )
    }

    override fun getFilePath(): String {
        return SourceUtils.getRootSourcePath(serviceProvider) + "${getClassName()}.${SourceUtils.getSourceExtension(configurationService)}"
    }

    private fun getClassName() = NamingUtils.toClassName(serviceProvider.configuration.project.name) + "Version"
}
