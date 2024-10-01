package com.gitlab.mbarberot.kriollo.utils

object NamingUtils {

    private val splitSeparators = Regex("[-_]")

    fun toClassName(value: String): String {
        if(value.contains(splitSeparators)) {
            return value.split(splitSeparators).joinToString(separator = "", transform = NamingUtils::toClassName)
        }

        return value
            .lowercase()
            .replaceFirstChar { it.titlecaseChar() }
    }

    fun toNamespace(value: String): String {
        if(value.contains(splitSeparators)) {
            return value.split(splitSeparators).joinToString(separator = ".", transform = NamingUtils::toNamespace)
        }

        return value
            .lowercase()
    }
}