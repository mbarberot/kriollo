package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.utils.SourceUtils.getRootPackage
import com.gitlab.mbarberot.kriollo.utils.SourceUtils.getRootSourcePath
import com.gitlab.mbarberot.kriollo.utils.SourceUtils.getSourceExtension

class GeneratedAnnotationGenerator(
    val serviceProvider: ServiceProvider,
    val configuration: CodegenConfiguration = serviceProvider.configuration,
) : TemplatedFileGenerator() {

    override fun getTemplatePath(): String {
        return "generator/project/GeneratedAnnotation.${getSourceExtension(configuration)}.kte"
    }

    override fun getTemplateData(): Any {
        return GeneratedAnnotationModel(
            name = getClassName(),
            namespace = "${getRootPackage(configuration, withTrailingDot = false)}.kriollo"
        )
    }

    override fun getFilePath(): String {
        return buildString {
            append(getRootSourcePath(configuration))
            append("kriollo/")
            append("${getClassName()}.${getSourceExtension(configuration)}")
        }
    }

    private fun getClassName() = "Generated"
}
