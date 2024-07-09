package kriollo.generator

import gg.jte.ContentType
import gg.jte.TemplateEngine
import kriollo.configuration.CodegenConfiguration
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
        modules.forEach { module ->
            addAll(module.provideGenerators())
        }
    }

    init {
        generators.forEach { generator ->
            modules.forEach { module ->
                module.provideExtensions(generator)
            }
        }

    }

    fun execute(configuration: CodegenConfiguration) {
        generators.forEach { it.execute(configuration, serviceProvider) }
    }
}