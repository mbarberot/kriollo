package kriollo.generator.project

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.services.provider.ServiceProvider

class ProjectVersionGenerator(
    val serviceProvider: ServiceProvider,
) : TemplatedFileGenerator() {

    override fun getTemplatePath(): String {
        return "generator/project/ProjectVersion.kte"
    }

    override fun getTemplateData(): Any {
        return ProjectVersionClassModel(
            version = serviceProvider.configuration.project.version,
            name = getClassName(),
            namespace = getNamespace()
        )
    }

    override fun getFilePath(configuration: CodegenConfiguration): String {
        return "src/main/kotlin/${getNamespace()}/${getClassName()}.kt"
    }

    private fun getNamespace() = serviceProvider.configuration.project.name.replaceFirstChar(Char::lowercase)

    private fun getClassName() = serviceProvider.configuration.project.name.replaceFirstChar(Char::titlecaseChar) + "Version"
}
