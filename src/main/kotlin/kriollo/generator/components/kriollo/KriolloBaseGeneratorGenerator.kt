package kriollo.generator.components.kriollo

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator

class KriolloBaseGeneratorGenerator(val configuration: CodegenConfiguration) : TemplatedFileGenerator() {
    override fun getTemplatePath(): String {
        return "generator/components/kriollo/KriolloBaseGenerator.kt.kte"
    }

    override fun getTemplateData(): Any {

        val extensionProviders = configuration
            .components
            .kriollo
            .filter { it.provideExtensions.isNotEmpty() }

        return KriolloBaseGeneratorModel(
            namespace = "kriollo.generator",
            name = "Generator",
            imports = buildList {
                add("kriollo.configuration.CodegenConfiguration")
                add("kriollo.generator.utils.ServiceProvider")

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
