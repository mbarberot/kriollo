package com.gitlab.mbarberot.kriollo.generator.maven

data class SimpleMavenPluginModel(
    val groupId: String? = null,
    val artifactId: String,
    val version: String? = null,
    val configuration: Map<String,String> = mapOf(),
    val executions: List<String> = listOf(),
)