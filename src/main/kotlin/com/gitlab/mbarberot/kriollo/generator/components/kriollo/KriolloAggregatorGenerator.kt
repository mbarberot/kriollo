package com.gitlab.mbarberot.kriollo.generator.components.kriollo

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.utils.NamingUtils
import com.gitlab.mbarberot.kriollo.utils.SourceUtils

class KriolloAggregatorGenerator(
    private val serviceProvider: ServiceProvider,
    private val tag: String,
    private val componentsConfigurations: List<KriolloComponentConfiguration>,
) : TemplatedFileGenerator() {

    override fun getTemplatePath(): String {
        return "generator/components/kriollo/TagModules.kt.kte"
    }

    override fun getTemplateData(): Any {
        val rootPackage = SourceUtils.getRootPackage(serviceProvider.configuration, withTrailingDot = false)
        return KriolloTagModulesModel(
            namespace = "$rootPackage.generator",
            name = getClassName(),
            imports = buildList {
                add("$rootPackage.services.provider.ServiceProvider")
                componentsConfigurations
                    .map { config -> "$rootPackage.${config.getNamespace()}.${config.getClassName()}" }
                    .forEach { add(it) }
            },
            components = componentsConfigurations
                .map { config -> config.getClassName() },
        )
    }

    override fun getFilePath(): String {
        return SourceUtils.getRootSourcePath(serviceProvider.configuration) + "generator/${getClassName()}.kt"
    }

    private fun getClassName(): String {
        return NamingUtils.toClassName(tag) + "Modules"
    }
}

