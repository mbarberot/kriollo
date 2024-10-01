package com.gitlab.mbarberot.kriollo.generator.components.kriollo

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.utils.SourceUtils

class KriolloModuleGenerator(
    private val serviceProvider: ServiceProvider,
    private val moduleConfiguration: KriolloComponentConfiguration,
) : TemplatedFileGenerator() {

    override fun getTemplatePath(): String {
        return "generator/components/kriollo/KriolloModule.kt.jte"
    }

    override fun getTemplateData(): Any {
        val (_, _, _, activationKey, generators, extensions) = moduleConfiguration

        return KriolloModuleModel(
            namespace = SourceUtils.getRootPackage(serviceProvider.configuration) + moduleConfiguration.getNamespace(),
            name = moduleConfiguration.getClassName(),
            activation = if (activationKey == "-") "true" else "serviceProvider.configuration.$activationKey",
            generators = generators.map { generator -> generator.name },
            extensions = extensions.map { extension -> extension.name },
        )
    }

    override fun getFilePath(): String {
        return SourceUtils.getRootSourcePath(serviceProvider.configuration) + "${moduleConfiguration.path}/${moduleConfiguration.getClassName()}.kt"
    }
}
