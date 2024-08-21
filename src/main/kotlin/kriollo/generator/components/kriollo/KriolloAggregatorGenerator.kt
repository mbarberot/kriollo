package kriollo.generator.components.kriollo

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator

class KriolloAggregatorGenerator(
    private val tag: String,
    private val componentsConfigurations: List<KriolloComponentConfiguration>,
) : TemplatedFileGenerator() {

    override fun getTemplatePath(): String {
        return "generator/components/kriollo/TagModules.kt.kte"
    }

    override fun getTemplateData(): Any {
        return KriolloTagModulesModel(
            namespace = "kriollo.generator",
            name = getClassName(),
            imports = buildList {
                add("kriollo.services.provider.ServiceProvider")
                componentsConfigurations
                    .map { config -> "${config.getNamespace()}.${config.getClassName()}" }
                    .forEach { add(it) }
            },
            components = componentsConfigurations
                .map { config -> config.getClassName() },
        )
    }

    override fun getFilePath(configuration: CodegenConfiguration): String {
        return "src/main/kotlin/kriollo/generator/${getClassName()}.kt"
    }

    private fun getClassName(): String {
        return tag.replaceFirstChar(Char::titlecaseChar) + "Modules"
    }
}

