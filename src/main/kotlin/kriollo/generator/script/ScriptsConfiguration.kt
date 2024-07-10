package kriollo.generator.script

import kriollo.generator.script.build.BuildScriptConfiguration

data class ScriptsConfiguration(
    val build: BuildScriptConfiguration = BuildScriptConfiguration()
)