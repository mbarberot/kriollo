package kriollo.generator.script.build

import kriollo.configuration.BuildScriptCustomStepConfiguration

data class BuildScriptModel(
    val customSteps: List<BuildScriptCustomStepConfiguration> = listOf()
)