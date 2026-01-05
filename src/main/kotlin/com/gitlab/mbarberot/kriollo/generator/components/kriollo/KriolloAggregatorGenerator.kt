package com.gitlab.mbarberot.kriollo.generator.components.kriollo

import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.services.templating.JteTemplate
import com.gitlab.mbarberot.kriollo.services.templating.Template
import com.gitlab.mbarberot.kriollo.utils.NamingUtils
import com.gitlab.mbarberot.kriollo.utils.SourceUtils

class KriolloAggregatorGenerator(
    private val serviceProvider: ServiceProvider,
    private val tag: String,
    private val componentsConfigurations: List<KriolloComponentConfiguration>,
) : TemplatedFileGenerator() {

    override fun getTemplate(): Template {
        val rootPackage = SourceUtils.getRootPackage(serviceProvider.configuration, withTrailingDot = false)
        return JteTemplate(
            "generator/components/kriollo/TagModules.kt.kte",
            KriolloTagModulesModel(
                namespace = "$rootPackage.generator",
                name = getClassName(),
                rootPackage = rootPackage,
                imports = buildList {
                    add("$rootPackage.services.provider.ServiceProvider")
                    componentsConfigurations
                        .map { config -> "$rootPackage.${config.getNamespace()}.${config.getClassName()}" }
                        .forEach { add(it) }
                },
                components = componentsConfigurations
                    .map { config -> config.getClassName() },
            )
        )
    }

    override fun getFilePath(): String {
        return SourceUtils.getRootSourcePath(serviceProvider) + "generator/${getClassName()}.kt"
    }

    private fun getClassName(): String {
        return NamingUtils.toClassName(tag) + "Modules"
    }
}

