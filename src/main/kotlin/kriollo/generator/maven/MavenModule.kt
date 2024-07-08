package kriollo.generator.maven

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.CodegenModule

class MavenModule(configuration: CodegenConfiguration) : CodegenModule(configuration) {

    override fun isActivated() = configuration.maven.enabled

    override fun provideGenerators() = listOf(
        MavenPomGenerator(configuration),
    )
}