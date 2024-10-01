package com.gitlab.mbarberot.kriollo.generator.scripts.build

data class BuildScriptModel(
    val customSteps: List<BuildScriptCustomStepConfiguration> = listOf()
)