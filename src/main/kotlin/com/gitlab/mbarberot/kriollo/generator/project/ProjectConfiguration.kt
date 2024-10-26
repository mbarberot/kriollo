package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.libs.IJavaLibConfiguration
import com.gitlab.mbarberot.kriollo.generator.libs.JavaLibConfiguration

interface IProjectConfiguration {
    val groupId: String
    val name: String
    val version: String
    val mainClass: String
    val encoding: String
    val dependencies: List<JavaArtifact>
    val libs: IJavaLibConfiguration
}

data class ProjectConfiguration(
    override val groupId: String = "",
    override val name: String = "",
    override val version: String = "",
    override val mainClass: String = "",
    override val encoding: String = "UTF-8",
    override val dependencies: List<JavaArtifact> = listOf(),
    override val libs: IJavaLibConfiguration = JavaLibConfiguration(),
) : IProjectConfiguration