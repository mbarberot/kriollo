package kriollo.cli

import kriollo.KriolloVersion
import kriollo.features.Generate
import picocli.CommandLine.Command

@Command(
    name = "Kriollo",
    mixinStandardHelpOptions = true,
    version = [KriolloVersion.VERSION]
)
class KriolloCli(
    private val generateAction: Generate,
) {

    @Command(name = "build")
    fun build() {
        generateAction.generate()
    }

}