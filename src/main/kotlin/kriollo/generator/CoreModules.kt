package kriollo.generator

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.cli.script.MainScriptModule
import kriollo.generator.git.GitModule
import kriollo.generator.kotlin.KotlinModule
import kriollo.generator.maven.MavenModule
import kriollo.generator.nix.NixModule

class CoreModules {
    fun getModules(configuration: CodegenConfiguration): List<CodegenModule> {
        return listOf(
            NixModule(configuration),
            MainScriptModule(configuration),
            GitModule(configuration),
            MavenModule(configuration),
            KotlinModule(configuration),
        )
    }
}