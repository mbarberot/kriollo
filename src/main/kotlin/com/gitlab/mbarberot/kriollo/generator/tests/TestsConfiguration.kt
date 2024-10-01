package com.gitlab.mbarberot.kriollo.generator.tests

import com.gitlab.mbarberot.kriollo.generator.tests.junit.JUnitConfiguration

data class TestsConfiguration(
    val enabled: Boolean = false,
    val junit: JUnitConfiguration = JUnitConfiguration()
)
