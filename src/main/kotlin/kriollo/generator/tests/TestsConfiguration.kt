package kriollo.generator.tests

import kriollo.generator.tests.junit.JUnitConfiguration

data class TestsConfiguration(
    val enabled: Boolean = false,
    val junit: JUnitConfiguration = JUnitConfiguration()
)
