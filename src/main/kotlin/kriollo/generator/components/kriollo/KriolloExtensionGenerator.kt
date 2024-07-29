package kriollo.generator.components.kriollo

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator

class KriolloExtensionGenerator(
    private val moduleConfiguration: KriolloComponentConfiguration,
    private val extensionConfiguration: KriolloExtensionProviderConfiguration,
) : TemplatedFileGenerator() {

    override fun getTemplatePath(): String {
        return "generator/components/kriollo/KriolloExtension.kt.kte"
    }

    override fun getTemplateData(): Any {
        return KriolloExtensionModel(
            namespace = moduleConfiguration.getNamespace(),
            name = extensionConfiguration.name,
            provideType = extensionConfiguration.getProvidedTypeClassName(),
            imports = buildList {
                if (extensionConfiguration.provideType.contains('.')) {
                    add(extensionConfiguration.provideType)
                }
            }
        )
    }

    override fun getFilePath(configuration: CodegenConfiguration): String {
        return "src/main/kotlin/${moduleConfiguration.path}/${extensionConfiguration.name}.kt"
    }
}
