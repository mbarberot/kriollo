package kriollo

import gg.jte.ContentType
import gg.jte.TemplateEngine
import kriollo.cli.KriolloCli
import kriollo.features.Generate
import kriollo.features.init.Init
import kriollo.services.configuration.TomlCodegenConfiguration
import kriollo.services.filesystem.DefaultFileSystemService
import kriollo.services.provider.DefaultServiceProvider
import kriollo.services.provider.ServiceProvider
import kriollo.services.templating.DefaultTemplatingService
import picocli.CommandLine
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val configuration = TomlCodegenConfiguration("./codegen/codegen.toml")
    val serviceProvider: ServiceProvider = DefaultServiceProvider(
        configuration,
        DefaultFileSystemService(),
        DefaultTemplatingService(
            TemplateEngine.createPrecompiled(ContentType.valueOf(configuration.templating.jte.contentType)),
        )
    )

    val kriolloCli = KriolloCli(
        Init(serviceProvider),
        Generate(serviceProvider),
    )

    exitProcess(CommandLine(kriolloCli).execute(*args))
}
