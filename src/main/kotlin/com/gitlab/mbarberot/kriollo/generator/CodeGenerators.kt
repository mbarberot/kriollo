package com.gitlab.mbarberot.kriollo.generator

import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class CodeGenerators(
    modules: List<com.gitlab.mbarberot.kriollo.generator.CodegenModule>,
) {

    private val generators: List<com.gitlab.mbarberot.kriollo.generator.Generator> = buildList {
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