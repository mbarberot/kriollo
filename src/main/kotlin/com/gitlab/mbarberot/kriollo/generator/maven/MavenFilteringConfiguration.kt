package com.gitlab.mbarberot.kriollo.generator.maven

fun setOfExtensions(vararg extensions: String): Set<String> =
    extensions.map { "**/*.${it}" }.toSet()

val fonts = setOfExtensions("ttf", "eot", "woff", "woff2")
val images = setOfExtensions("png", "jpg", "jpeg", "gif")

data class MavenFilteringConfiguration(
    val enabled: Boolean = true,
    private val exclusions: Set<String> = buildSet {
        addAll(fonts)
        addAll(images)
    },
    private val additionalExclusions: Set<String> = setOf()
) {
    fun exclusions(): List<String> = buildList {
        addAll(exclusions)
        addAll(additionalExclusions)
        sort()
    }
}