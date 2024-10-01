package com.gitlab.mbarberot.kriollo.cli

import com.gitlab.mbarberot.kriollo.KriolloVersion
import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.features.init.Init
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