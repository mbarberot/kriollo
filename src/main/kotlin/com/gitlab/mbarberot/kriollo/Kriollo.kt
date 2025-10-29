package com.gitlab.mbarberot.kriollo

import com.gitlab.mbarberot.kriollo.cli.KriolloCli
import com.gitlab.mbarberot.kriollo.features.Clean
import com.gitlab.mbarberot.kriollo.features.Config
import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.features.Init
import com.gitlab.mbarberot.kriollo.services.configuration.DefaultConfigurationService
import com.gitlab.mbarberot.kriollo.services.provider.DefaultServiceProvider
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.spi.ModuleProvider
import picocli.CommandLine
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val moduleProviders: List<ModuleProvider> =
        ServiceLoader
            .load(ModuleProvider::class.java)
            .filterNotNull()

    val serviceProvider: ServiceProvider = DefaultServiceProvider(
        configurationService = DefaultConfigurationService(
            Path("./codegen/codegen.kdl").readText(),
            moduleProviders.flatMap { it.getConfigurationCustomizers() },
        ),
        moduleProviders = moduleProviders,
    )

    val kriolloCli = KriolloCli(
        Init(serviceProvider),
        Clean(serviceProvider),
        Config.default(),
        Generate(serviceProvider),
    )

    exitProcess(CommandLine(kriolloCli).execute(*args))
}
