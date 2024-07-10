package kriollo.generator.components.kriollo

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.generator.components.kriollo.configuration.KriolloComponentConfiguration

class KriolloModuleGenerator(
    private val moduleConfiguration: KriolloComponentConfiguration,
) : TemplatedFileGenerator() {

    override fun getTemplatePath(): String {
        return "generator/components/kriollo/KriolloModule.kt.jte"
    }

    override fun getTemplateData(): Any {
        val (path, _, activationKey, generators, extensions) = moduleConfiguration

        return KriolloModuleModel(
            namespace = path.replace("/", "."),
            name = getClassName(),
            activation = if (activationKey == "-") "true" else "configuration.$activationKey",
            generators = generators.map { generator -> generator.name },
            extensions = extensions.map { extension -> extension.name },
        )
    }

    override fun getFilePath(configuration: CodegenConfiguration): String {
        return "src/main/kotlin/${moduleConfiguration.path}/${getClassName()}.kt"
    }

    private fun getClassName() = moduleConfiguration.name.replaceFirstChar(Char::titlecaseChar) + "Module"
}
