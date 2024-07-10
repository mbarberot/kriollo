package kriollo.generator.cli

import kriollo.generator.cli.script.MainScriptConfiguration

data class CliConfiguration(
    val enabled: Boolean = false,
    val script: MainScriptConfiguration = MainScriptConfiguration(),
)