package com.gitlab.mbarberot.kriollo.generator.tests.junit

import com.gitlab.mbarberot.kriollo.HardcodedValues

interface IJUnitConfiguration {
    val junitVersion: String
    val assertjVersion: String
}

data class JUnitConfiguration(
    override val junitVersion: String = HardcodedValues.DEFAULT_JUNIT_VERSION,
    override val assertjVersion: String = HardcodedValues.DEFAULT_ASSERTJ_VERSION,
) : IJUnitConfiguration
