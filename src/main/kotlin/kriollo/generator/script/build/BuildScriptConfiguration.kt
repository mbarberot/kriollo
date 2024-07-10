package kriollo.generator.script.build

data class BuildScriptConfiguration(
    val enabled: Boolean = false,
    val customSteps: List<BuildScriptCustomStepConfiguration> = listOf()
)