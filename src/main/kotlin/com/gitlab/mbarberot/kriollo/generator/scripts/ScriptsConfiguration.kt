package com.gitlab.mbarberot.kriollo.generator.scripts

import com.gitlab.mbarberot.kriollo.generator.scripts.build.BuildScriptConfiguration
import com.gitlab.mbarberot.kriollo.generator.scripts.test.TestScriptConfiguration

data class ScriptsConfiguration(
    val enabled: Boolean = false,
    val dir: String = "scripts",
    val build: BuildScriptConfiguration = BuildScriptConfiguration(),
    val tests: TestScriptConfiguration = TestScriptConfiguration(),
)