package com.gitlab.mbarberot.kriollo.cli

import com.gitlab.mbarberot.kriollo.KriolloVersion
import com.gitlab.mbarberot.kriollo.features.Clean
import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.features.Init
import picocli.CommandLine.Command

@Command(
    name = "Kriollo",
    mixinStandardHelpOptions = true,
    version = [KriolloVersion.VERSION]
)
class KriolloCli(
    private val initAction: Init,
    private val cleanAction: Clean,
    private val generateAction: Generate,
) {

    @Command(name = "init")
    fun init() {
        initAction.init()
    }

    @Command(name = "clean")
    fun clean() {
        cleanAction.clean()
    }

    @Command(name = "build")
    fun build() {
        cleanAction.clean()
        generateAction.generate()
    }

}