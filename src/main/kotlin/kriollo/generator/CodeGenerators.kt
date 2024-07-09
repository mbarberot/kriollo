package kriollo.generator

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.utils.ServiceProvider

class CodeGenerators(
    modules: List<CodegenModule>,
) {


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

    fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider) {
        generators.forEach { it.execute(configuration, serviceProvider) }
    }
}