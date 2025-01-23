package com.gitlab.mbarberot.kriollo.generator.tests

import com.gitlab.mbarberot.kriollo.generator.tests.coverage.CoverageConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.junit.IJUnitConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.junit.JUnitConfiguration

interface ITestsConfiguration{
    val enabled: Boolean
    val junit: IJUnitConfiguration
    val coverage: CoverageConfiguration
 }

data class TestsConfiguration(
    override val enabled: Boolean = false,
    override val junit: IJUnitConfiguration = JUnitConfiguration(),
    override val coverage: CoverageConfiguration = CoverageConfiguration(),
): ITestsConfiguration
