package kriollo.generator.cli

import kriollo.generator.cli.script.MainScriptConfiguration

data class CliConfiguration(
    val enabled: Boolean = false, // TODO use this to generate the assemble plugin configuration in pom generation
    val script: MainScriptConfiguration = MainScriptConfiguration(),
)