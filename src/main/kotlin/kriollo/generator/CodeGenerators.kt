package kriollo.generator

import gg.jte.ContentType
import gg.jte.TemplateEngine
import kriollo.configuration.CodegenConfiguration
import kriollo.generator.script.build.BuildScriptGenerator
import kriollo.generator.templating.JteGenerator
import kriollo.generator.utils.FilesytemService
import kriollo.generator.utils.ServiceProvider
import kriollo.generator.utils.TemplatingService

class CodeGenerators(configuration: CodegenConfiguration, modules: List<CodegenModule>) {

    private val serviceProvider: ServiceProvider = ServiceProvider(
        FilesytemService(),
        TemplatingService(
            TemplateEngine.createPrecompiled(ContentType.valueOf(configuration.templating.jte.contentType)),
        )
    )

    private val generators: List<Generator> = buildList {
        addAll(
            listOf(
                JteGenerator(), // TODO => module
                BuildScriptGenerator(), // TODO => module
            )
        )

        modules.forEach { module ->
            addAll(module.provideGenerators())
        }
    }

    init {
        // Step two : register extensions (aka: a special kind of generator generating partials for the main generator)
        generators.forEach { generator ->
            modules.forEach { module ->
                module.provideExtensions(generator)
            }
        }

    }

    fun execute(configuration: CodegenConfiguration) {
        generators
            .filter { it.isActivated(configuration) }
            .forEach { it.execute(configuration, serviceProvider) }
    }

}