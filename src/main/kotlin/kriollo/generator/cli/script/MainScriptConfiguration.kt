package kriollo.generator.cli.script

data class MainScriptConfiguration(
    val enabled: Boolean = false,
    val fileName: String = "run.sh",
    val targetDirectory: String = "./build",
)