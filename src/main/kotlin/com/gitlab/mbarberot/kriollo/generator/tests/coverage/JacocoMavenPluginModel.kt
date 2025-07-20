package com.gitlab.mbarberot.kriollo.generator.tests.coverage

import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact

data class JacocoMavenPluginModel(
    val artifact: JavaArtifact,
    val exclusions: List<String>,
    val minimumBranchCoverage: String,
    val minimumInstructionCoverage: String,
    val maximumMissedClasses: String?,
    val reportPhase: String,
)
