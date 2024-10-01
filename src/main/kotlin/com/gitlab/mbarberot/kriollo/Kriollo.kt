package com.gitlab.mbarberot.kriollo

import com.gitlab.mbarberot.kriollo.cli.KriolloCli
import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.features.init.Init
import com.gitlab.mbarberot.kriollo.services.provider.DefaultServiceProvider
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import picocli.CommandLine
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val serviceProvider: ServiceProvider = DefaultServiceProvider()

    val kriolloCli = KriolloCli(
        Init(serviceProvider),
        Generate(serviceProvider),
    )

    exitProcess(CommandLine(kriolloCli).execute(*args))
}
