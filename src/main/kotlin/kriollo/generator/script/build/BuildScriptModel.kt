package kriollo.generator.script.build

data class BuildScriptModel(
    val customSteps: List<BuildScriptCustomStepConfiguration> = listOf()
)