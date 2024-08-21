package kriollo.generator.components.kriollo

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.services.provider.ServiceProvider

class KriolloBaseGeneratorGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {
    override fun getTemplatePath(): String {
        return "generator/components/kriollo/KriolloBaseGenerator.kt.kte"
    }

    override fun getTemplateData(): Any {

        val extensionProviders = serviceProvider
            .configuration
            .components
            .kriollo
            .filter { it.provideExtensions.isNotEmpty() }

        return KriolloBaseGeneratorModel(
            namespace = "kriollo.generator",
            name = "Generator",
            imports = buildList {
                add("kriollo.services.provider.ServiceProvider")

                extensionProviders.forEach { config ->
                    config.provideExtensions.forEach { extensionConfig ->
                        add("${config.getNamespace()}.${extensionConfig.name}")
                    }
                }
            },
            extensions = buildList {
                extensionProviders
                    .flatMap { it.provideExtensions }
                    .forEach { add(it.name) }
            }
        )
    }

    override fun getFilePath(configuration: CodegenConfiguration): String {
        return "src/main/kotlin/kriollo/generator/Generator.kt"
    }

}
