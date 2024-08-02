package kriollo.features

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.CodeGenerators
import kriollo.generator.CoreModules
import kriollo.generator.KriolloModules
import kriollo.services.provider.ServiceProvider

class Generate(
    private val configuration: CodegenConfiguration,
    private val serviceProvider: ServiceProvider,
) {

    private val generators: CodeGenerators = CodeGenerators(
        buildList {
            addAll(CoreModules().getModules(configuration, serviceProvider))
            addAll(KriolloModules().getModules(configuration, serviceProvider))
        },
    )

    fun generate() {
        generators.execute(configuration, serviceProvider);
        createIndexFile()
    }

    private fun createIndexFile() {
        val fileSystem = serviceProvider.fileSystem

        fileSystem.createFile(
            filename = "${configuration.kriollo.dir}/generated-files.txt",
            content = fileSystem.generatedFiles().joinToString("\n") { it.filename }
        )
    }
}