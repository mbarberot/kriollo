package kriollo

import gg.jte.ContentType
import gg.jte.TemplateEngine
import kriollo.cli.KriolloCli
import kriollo.features.Generate
import kriollo.services.configuration.TomlCodegenConfiguration
import kriollo.services.filesystem.DefaultFileSystemService
import kriollo.services.provider.DefaultServiceProvider
import kriollo.services.provider.ServiceProvider
import kriollo.services.templating.DefaultTemplatingService
import picocli.CommandLine
import kotlin.system.exitProcess


object Kriollo {
    val version = "0.1.0"
}

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
        Generate(serviceProvider),
    )

    exitProcess(CommandLine(kriolloCli).execute(*args))
}


// ** v2014.1 **
//TODO: feat(logs): add Logging framework
//TODO: refactor(di): remove last "configuration: CodegenConfiguration" occurences
//TODO: refactor(di): check serviceProvider parameters (should be injected through constructor everywhere)

//TODO: feat(maven): set source and test directories using an extension (only one || error)
//TODO: feat(maven): artifact coordinates
//TODO: feat(maven): use ${config.project.name} for the final name
//TODO: refactor(jte): use a base template for java classes
//TODO: refactor(jte): use StaticTemplates
//TODO: feat(config): handle version (maven filtering ? how to handle module/plugins ?)

// ** v2014.2 **
//TODO: feat(justfile): add justfile support
//TODO: feat(dockerfile): generate dockerfile
//TODO: feat(readme): add readme support with custom title/description/sections
//TODO: feat(config): check configuration (ie: JUnit requires Maven or Gradle, ...)
//TODO: feat(config): split configuration file
//TODO: feat(generator): handle old obsolete files using the -lock file
//TODO: feat(plugin): use a ServiceProvider (from JDK) to build dynamic module registration
