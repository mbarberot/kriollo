package com.gitlab.mbarberot.kriollo.generator.tests

import com.gitlab.mbarberot.kriollo.generator.tests.coverage.CoverageConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.junit.IJUnitConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.junit.JUnitConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.livingspecifications.LivingSpecificationsConfiguration

interface ITestsConfiguration{
    val enabled: Boolean
    val junit: IJUnitConfiguration
    val coverage: CoverageConfiguration
    val livingSpecifications: LivingSpecificationsConfiguration
 }

data class TestsConfiguration(
    override val enabled: Boolean = false,
    override val junit: IJUnitConfiguration = JUnitConfiguration(),
    override val coverage: CoverageConfiguration = CoverageConfiguration(),
    override val livingSpecifications: LivingSpecificationsConfiguration = LivingSpecificationsConfiguration()
): ITestsConfiguration
