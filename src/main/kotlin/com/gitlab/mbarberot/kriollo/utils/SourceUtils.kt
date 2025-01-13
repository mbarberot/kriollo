package com.gitlab.mbarberot.kriollo.utils

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration

object SourceUtils {

    fun getSourceExtension(configuration: CodegenConfiguration): String {
        return if(configuration.kotlin.enabled) {
            "kt"
        } else {
            "java"
        }
    }

    fun getSourceDirectory(configuration: CodegenConfiguration, isTestSource: Boolean = false): String {
        val finalDirectory = if(configuration.kotlin.enabled) {
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

    fun getRootSourcePath(configuration: CodegenConfiguration, withTrailingSlash: Boolean = true): String {
        val sourcePath = StringBuilder()
            .append(getSourceDirectory(configuration))
            .append("/")
            .append(getRootPackage(configuration, withTrailingDot = false).replace(".", "/"))

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