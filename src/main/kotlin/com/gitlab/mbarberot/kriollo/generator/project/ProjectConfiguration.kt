package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact

data class ProjectConfiguration(
    val groupId: String = "",
    val name: String = "",
    val version: String = "",
    val mainClass: String = "",
    val encoding: String = "UTF-8",
    val dependencies: List<JavaArtifact> = listOf()
)