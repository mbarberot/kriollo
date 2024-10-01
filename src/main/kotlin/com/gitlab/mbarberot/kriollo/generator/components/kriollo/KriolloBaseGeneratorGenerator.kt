package com.gitlab.mbarberot.kriollo.generator.components.kriollo

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.utils.SourceUtils

class KriolloBaseGeneratorGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {
    override fun getTemplatePath(): String {
        return "generator/components/kriollo/KriolloBaseGenerator.kt.kte"
    }

    override fun getTemplateData(): Any {

        val rootPackage = SourceUtils.getRootPackage(serviceProvider.configuration, withTrailingDot = false)
        val extensionProviders = serviceProvider
            .configuration
            .components
            .kriollo
            .filter { it.provideExtensions.isNotEmpty() }

        return KriolloBaseGeneratorModel(
            namespace = "$rootPackage.generator",
            name = "Generator",
            imports = buildList {
                add("$rootPackage.services.provider.ServiceProvider")

                extensionProviders.forEach { config ->
                    config.provideExtensions.forEach { extensionConfig ->
                        add("$rootPackage.${config.getNamespace()}.${extensionConfig.name}")
                    }
                }
            },
            extensions = buildList {
                extensionProviders
                    .flatMap { it.provideExtensions }
                    .forEach { add(it.name) }
            }
        )
    }

    override fun getFilePath(): String {
        return SourceUtils.getRootSourcePath(serviceProvider.configuration) + "generator/Generator.kt"
    }

}
