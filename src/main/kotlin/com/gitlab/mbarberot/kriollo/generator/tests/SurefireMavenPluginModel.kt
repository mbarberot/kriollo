package com.gitlab.mbarberot.kriollo.generator.tests

data class SurefireMavenPluginModel(
    val artifactId: String = "maven-surefire-plugin",
    val version: String,
    val systemPropertyVariables: Map<String,String> = mapOf(),
    val includes: List<String> = listOf(),
) {
    fun hasConfiguration(): Boolean =
        systemPropertyVariables.isNotEmpty()
                || includes.isNotEmpty()
}