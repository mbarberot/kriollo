package com.gitlab.mbarberot.kriollo.utils

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration

object SourceUtils {

    fun getRootSourcePath(configuration: CodegenConfiguration, withTrailingSlash: Boolean = true): String {
        val sourcePath = StringBuilder()
            .append("src/main/kotlin")
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