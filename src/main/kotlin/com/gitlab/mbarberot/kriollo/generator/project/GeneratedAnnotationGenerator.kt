package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.services.templating.JteTemplate
import com.gitlab.mbarberot.kriollo.services.templating.Template
import com.gitlab.mbarberot.kriollo.utils.SourceUtils.getRootPackage
import com.gitlab.mbarberot.kriollo.utils.SourceUtils.getRootSourcePath
import com.gitlab.mbarberot.kriollo.utils.SourceUtils.getSourceExtension

class GeneratedAnnotationGenerator(
    val serviceProvider: ServiceProvider,
    val configuration: CodegenConfiguration = serviceProvider.configuration,
) : TemplatedFileGenerator() {

    override fun getTemplate(): Template {
        val rootPackage = getRootPackage(configuration, withTrailingDot = false)
        return JteTemplate(
            "generator/project/GeneratedAnnotation.${getSourceExtension(serviceProvider.configurationService)}.kte",
            GeneratedAnnotationModel(
                name = getClassName(),
                namespace = "$rootPackage.kriollo",
                rootPackage = rootPackage
            )
        )
    }

    override fun getFilePath(): String {
        return buildString {
            append(getRootSourcePath(serviceProvider))
            append("kriollo/")
            append("${getClassName()}.${getSourceExtension(serviceProvider.configurationService)}")
        }
    }

    private fun getClassName() = "Generated"
}
