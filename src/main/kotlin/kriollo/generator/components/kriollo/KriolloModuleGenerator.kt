package kriollo.generator.components.kriollo

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator

class KriolloModuleGenerator(
    private val moduleConfiguration: KriolloComponentConfiguration,
) : TemplatedFileGenerator() {

    override fun getTemplatePath(): String {
        return "generator/components/kriollo/KriolloModule.kt.jte"
    }

    override fun getTemplateData(): Any {
        val (_, _, _, activationKey, generators, extensions) = moduleConfiguration

        return KriolloModuleModel(
            namespace = moduleConfiguration.getNamespace(),
            name = moduleConfiguration.getClassName(),
            activation = if (activationKey == "-") "true" else "configuration.$activationKey",
            generators = generators.map { generator -> generator.name },
            extensions = extensions.map { extension -> extension.name },
        )
    }

    override fun getFilePath(configuration: CodegenConfiguration): String {
        return "src/main/kotlin/${moduleConfiguration.path}/${moduleConfiguration.getClassName()}.kt"
    }
}
