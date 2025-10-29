package com.gitlab.mbarberot.kriollo.utils

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.generator.kotlin.KotlinConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationService
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider


object SourceUtils {

    fun getSourceExtension(configurationService: ConfigurationService): String {
        return if(configurationService.get(KotlinConfiguration::class).enabled) {
            "kt"
        } else {
            "java"
        }
    }

    fun getSourceDirectory(configurationService: ConfigurationService, isTestSource: Boolean = false): String {
        val finalDirectory = if(configurationService.get(KotlinConfiguration::class).enabled) {
            "kotlin"
        } else {
            "java"
        }

        val directoryType = if(isTestSource) {
            "test"
        } else {
            "main"
        }

        return "src/${directoryType}/${finalDirectory}"
    }

    fun getRootSourcePath(serviceProvider: ServiceProvider, withTrailingSlash: Boolean = true): String {
        val sourcePath = StringBuilder()
            .append(getSourceDirectory(serviceProvider.configurationService))
            .append("/")
            .append(getRootPackage(serviceProvider.configuration, withTrailingDot = false).replace(".", "/"))

        if (withTrailingSlash) {
            sourcePath.append("/")
        }

        return sourcePath.toString()
    }

    fun getRootPackage(configuration: CodegenConfiguration, withTrailingDot: Boolean = true): String {
        val rootPackage = StringBuilder()
            .append(NamingUtils.toNamespace(configuration.project.groupId))
            .append(".")
            .append(NamingUtils.toNamespace(configuration.project.name))

        if (withTrailingDot) {
            rootPackage.append(".")
        }

        return rootPackage.toString()
    }
}