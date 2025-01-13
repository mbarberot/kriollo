package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.utils.NamingUtils
import com.gitlab.mbarberot.kriollo.utils.SourceUtils

class ProjectVersionGenerator(
    val serviceProvider: ServiceProvider,
    val configuration: CodegenConfiguration = serviceProvider.configuration,
) : TemplatedFileGenerator() {

    override fun getTemplatePath(): String {
        return "generator/project/ProjectVersion.${SourceUtils.getSourceExtension(configuration)}.kte"
    }

    override fun getTemplateData(): Any {
        return ProjectVersionClassModel(
            version = configuration.project.version,
            name = getClassName(),
            namespace = SourceUtils.getRootPackage(configuration, withTrailingDot = false)
        )
    }

    override fun getFilePath(): String {
        return SourceUtils.getRootSourcePath(configuration) + "${getClassName()}.${SourceUtils.getSourceExtension(configuration)}"
    }

    private fun getClassName() = NamingUtils.toClassName(configuration.project.name) + "Version"
}
