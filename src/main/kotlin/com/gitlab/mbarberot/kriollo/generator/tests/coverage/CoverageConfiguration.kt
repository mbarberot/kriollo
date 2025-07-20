package com.gitlab.mbarberot.kriollo.generator.tests.coverage

import com.gitlab.mbarberot.kriollo.HardcodedValues

data class CoverageConfiguration(
    val jacocoVersion: String = HardcodedValues.DEFAULT_JACOCO_VERSION,
    val exclusions: List<String> = listOf(),
    val minimumBranchCoverage: String = HardcodedValues.DEFAULT_JACOCO_BRANCH_COVERAGE,
    val minimumInstructionCoverage: String = HardcodedValues.DEFAULT_JACOCO_INSTRUCTION_COVERAGE,
    val maximumMissedClasses: String? = null,
    val reportPhase: String = "prepare-package"
)
