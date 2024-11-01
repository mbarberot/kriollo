package com.gitlab.mbarberot.kriollo.cli

import com.gitlab.mbarberot.kriollo.KriolloVersion
import com.gitlab.mbarberot.kriollo.features.Clean
import com.gitlab.mbarberot.kriollo.features.Config
import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.features.Init
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyCodegenConfiguration
import picocli.CommandLine.Command

@Command(
    name = "Kriollo",
    mixinStandardHelpOptions = true,
    version = [KriolloVersion.VERSION]
)
class KriolloCli(
    private val initAction: Init,
    private val cleanAction: Clean,
    private val configAction: Config<LegacyCodegenConfiguration>,
    private val generateAction: Generate,
) {

    @Command(name = "init")
    fun init() {
        initAction.init()
    }

    @Command(name = "config")
    fun config() {
        configAction.check()
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