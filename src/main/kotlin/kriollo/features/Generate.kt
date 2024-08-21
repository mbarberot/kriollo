package kriollo.features

import kriollo.generator.CodeGenerators
import kriollo.generator.CoreModules
import kriollo.generator.KriolloModules
import kriollo.services.provider.ServiceProvider

class Generate(
    private val serviceProvider: ServiceProvider,
) {

    private val generators: CodeGenerators = CodeGenerators(
        buildList {
            addAll(CoreModules().getModules(serviceProvider))
            addAll(KriolloModules().getModules(serviceProvider))
        },
    )

    fun generate() {
        generators.execute(serviceProvider);
        createIndexFile()
    }

    private fun createIndexFile() {
        val fileSystem = serviceProvider.fileSystem

        fileSystem.createFile(
            filename = "${serviceProvider.configuration.kriollo.dir}/generated-files.txt",
            content = fileSystem.generatedFiles().joinToString("\n") { it.filename }
        )
    }
}