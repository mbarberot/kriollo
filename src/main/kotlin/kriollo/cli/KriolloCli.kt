package kriollo.cli

import kriollo.KriolloVersion
import kriollo.features.Generate
import kriollo.features.init.Init
import picocli.CommandLine.Command

@Command(
    name = "Kriollo",
    mixinStandardHelpOptions = true,
    version = [KriolloVersion.VERSION]
)
class KriolloCli(
    private val initAction: Init,
    private val generateAction: Generate,
) {

    @Command(name = "init")
    fun init() {
        initAction.init()
    }

    @Command(name = "build")
    fun build() {
        generateAction.generate()
    }

}