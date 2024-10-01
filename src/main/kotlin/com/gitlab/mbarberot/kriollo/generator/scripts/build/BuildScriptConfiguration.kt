package com.gitlab.mbarberot.kriollo.generator.scripts.build

data class BuildScriptConfiguration(
    val enabled: Boolean = false,
    val customSteps: List<BuildScriptCustomStepConfiguration> = listOf()
)