package com.gitlab.mbarberot.kriollo.generator.tests

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact

data class JacocoMavenPluginModel(
    val artifact: JavaArtifact,
    val branchMinimumCoverage: String?,
    val classMaximumMissed: String?,
)
