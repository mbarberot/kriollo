package com.gitlab.mbarberot.kriollo.generator.cli

import com.gitlab.mbarberot.kriollo.generator.cli.script.MainScriptConfiguration

data class CliConfiguration(
    val enabled: Boolean = false,
    val script: MainScriptConfiguration = MainScriptConfiguration(),
)