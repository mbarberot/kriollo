package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.utils.NamingUtils
import com.gitlab.mbarberot.kriollo.utils.SourceUtils

class ProjectVersionGenerator(
    val serviceProvider: ServiceProvider,
) : TemplatedFileGenerator() {

    override fun getTemplatePath(): String {
        return "generator/project/ProjectVersion.kte"
    }

    override fun getTemplateData(): Any {
        return ProjectVersionClassModel(
            version = serviceProvider.configuration.project.version,
            name = getClassName(),
            namespace = SourceUtils.getRootPackage(serviceProvider.configuration, withTrailingDot = false)
        )
    }

    override fun getFilePath(): String {
        return SourceUtils.getRootSourcePath(serviceProvider.configuration) + "${getClassName()}.kt"
    }

    private fun getClassName() = NamingUtils.toClassName(serviceProvider.configuration.project.name) + "Version"
}
