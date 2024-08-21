package kriollo.generator

import kriollo.services.provider.ServiceProvider

class CodeGenerators(
    modules: List<CodegenModule>,
) {

    private val generators: List<Generator> = buildList {
        modules
            .filter { it.isActivated() }
            .forEach { module ->
                addAll(module.provideGenerators())
            }
    }

    init {
        generators.forEach { generator ->
            modules
                .filter { it.isActivated() }
                .forEach { module ->
                module.provideExtensions(generator)
            }
        }

    }

    fun execute(serviceProvider: ServiceProvider) {
        generators.forEach { it.execute(serviceProvider) }
    }
}