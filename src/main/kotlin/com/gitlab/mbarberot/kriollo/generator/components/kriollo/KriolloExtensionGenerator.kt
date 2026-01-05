package com.gitlab.mbarberot.kriollo.generator.components.kriollo

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.services.templating.JteTemplate
import com.gitlab.mbarberot.kriollo.services.templating.Template
import com.gitlab.mbarberot.kriollo.utils.SourceUtils

class KriolloExtensionGenerator(
    private val serviceProvider: ServiceProvider,
    private val moduleConfiguration: KriolloComponentConfiguration,
    private val extensionConfiguration: KriolloExtensionProviderConfiguration,
) : TemplatedFileGenerator() {

    override fun getTemplate(): Template {
        val rootPackage = SourceUtils.getRootPackage(serviceProvider.configuration, withTrailingDot = false)
        return JteTemplate(
            "generator/components/kriollo/KriolloExtension.kt.kte",
            KriolloExtensionModel(
                namespace = "$rootPackage.${moduleConfiguration.getNamespace()}",
                name = extensionConfiguration.name,
                rootPackage = rootPackage,
                provideType = extensionConfiguration.getProvidedTypeClassName(),
                imports = buildList {
                    if (extensionConfiguration.provideType.contains('.')) {
                        add(extensionConfiguration.provideType)
                    }
                }
            )
        )
    }

    override fun getFilePath(): String {
        val rootSourcePath = SourceUtils.getRootSourcePath(serviceProvider, withTrailingSlash = false)
        return "$rootSourcePath/${moduleConfiguration.path}/${extensionConfiguration.name}.kt"
    }
}
