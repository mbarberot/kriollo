package kriollo.generator

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.cli.script.MainScriptModule
import kriollo.generator.git.GitModule
import kriollo.generator.kotlin.KotlinModule
import kriollo.generator.libs.LibrariesModule
import kriollo.generator.maven.MavenModule
import kriollo.generator.nix.NixModule
import kriollo.generator.project.ProjectModule
import kriollo.generator.script.build.BuildScriptModule
import kriollo.generator.templating.jte.JteModule
import kriollo.generator.utils.ServiceProvider

class CoreModules {
    fun getModules(configuration: CodegenConfiguration, serviceProvider: ServiceProvider): List<CodegenModule> {
        return listOf(
            NixModule(configuration),
            MainScriptModule(configuration),
            BuildScriptModule(configuration),
            GitModule(configuration),
            MavenModule(configuration),
            ProjectModule(configuration),
            KotlinModule(configuration),
            JteModule(configuration, serviceProvider),
            LibrariesModule(configuration),
        )
    }
}