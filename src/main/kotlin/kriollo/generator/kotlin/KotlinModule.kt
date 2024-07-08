package kriollo.generator.kotlin

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.generator.CodegenModule


class KotlinModule(configuration: CodegenConfiguration) : CodegenModule(configuration) {
    override fun isActivated(): Boolean = configuration.kotlin.enabled

    override fun provideGenerators(): List<Generator> = listOf(
        KotlinSourceDirectoryGenerator(),
        KotlinTestDirectoryGenerator(),
    )

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(KotlinJavaDependenciesGeneratorExtension(configuration))
    }
}