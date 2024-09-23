package kriollo

import kriollo.cli.KriolloCli
import kriollo.features.Generate
import kriollo.features.init.Init
import kriollo.services.provider.DefaultServiceProvider
import kriollo.services.provider.ServiceProvider
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
