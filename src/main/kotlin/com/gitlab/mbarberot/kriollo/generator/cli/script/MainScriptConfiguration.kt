package com.gitlab.mbarberot.kriollo.generator.cli.script

data class MainScriptConfiguration(
    val enabled: Boolean = false,
    val fileName: String = "run.sh",
    val targetDirectory: String = "./build",
    val beforeRun: String = "",
    val includeDebug: Boolean = false,
)