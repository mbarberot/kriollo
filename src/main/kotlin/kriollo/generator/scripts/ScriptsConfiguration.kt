package kriollo.generator.scripts

import kriollo.generator.scripts.build.BuildScriptConfiguration
import kriollo.generator.scripts.test.TestScriptConfiguration

data class ScriptsConfiguration(
    val enabled: Boolean = false,
    val dir: String = "scripts",
    val build: BuildScriptConfiguration = BuildScriptConfiguration(),
    val tests: TestScriptConfiguration = TestScriptConfiguration(),
)