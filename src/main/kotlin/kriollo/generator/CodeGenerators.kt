package kriollo.generator

import gg.jte.ContentType
import gg.jte.TemplateEngine
import kriollo.configuration.CodegenConfiguration
import kriollo.generator.git.GitGenerator
import kriollo.generator.maven.MavenGenerator
import kriollo.generator.nix.NixGenerator
import kriollo.generator.script.build.BuildScriptGenerator
import kriollo.generator.cli.script.MainScriptGenerator
import kriollo.generator.templating.JteGenerator
import kriollo.generator.utils.FilesytemService
import kriollo.generator.utils.ServiceProvider
import kriollo.generator.utils.TemplatingService

class CodeGenerators(configuration: CodegenConfiguration) {

    private val serviceProvider: ServiceProvider = ServiceProvider(
        FilesytemService(),
        TemplatingService(
            TemplateEngine.createPrecompiled(ContentType.valueOf(configuration.templating.jte.contentType)),
        )
    )

    private val generators: List<Generator> = listOf(
        NixGenerator(),
        JteGenerator(),
        GitGenerator(),
        BuildScriptGenerator(),
        MainScriptGenerator(),
        MavenGenerator(),
    )

    fun execute(configuration: CodegenConfiguration) {
        generators
            .filter { it.isActivated(configuration)}
            .forEach { it.execute(configuration, serviceProvider) }

    }
}