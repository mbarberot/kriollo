package com.gitlab.mbarberot.kriollo.generator.maven

data class MavenExecPlugin(
    val id: String,
    val phase: String,
    val command: String
)
