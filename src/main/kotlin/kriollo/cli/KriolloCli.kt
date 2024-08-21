package kriollo.cli

import kriollo.features.Generate
import picocli.CommandLine

@CommandLine.Command(name = "Kriollo", version = ["2024.0.0"], mixinStandardHelpOptions = true)
class KriolloCli(
    private val generateAction: Generate,
) {

    @CommandLine.Command(name = "build")
    fun build() {
        generateAction.generate()
    }
}