package kriollo.generator

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import kriollo.generator.cli.CliModule
import kriollo.generator.cli.script.MainScriptModule
import kriollo.generator.git.GitModule
import kriollo.generator.java.JavaModule
import kriollo.generator.jetbrains.JetbrainsModule
import kriollo.generator.kotlin.KotlinModule
import kriollo.generator.libs.LibrariesModule
import kriollo.generator.maven.MavenModule
import kriollo.generator.nix.NixModule
import kriollo.generator.project.ProjectModule
import kriollo.generator.scripts.ScriptsModule
import kriollo.generator.scripts.build.BuildScriptModule
import kriollo.generator.scripts.test.TestScriptModule
import kriollo.generator.templating.jte.JteModule
import kriollo.generator.tests.TestsModule
import kriollo.generator.tests.junit.JunitModule
import kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v0.1.0")
class CoreModules {
    fun getModules(serviceProvider: ServiceProvider): List<CodegenModule> {
        return listOf(
            NixModule(serviceProvider),
            BuildScriptModule(serviceProvider),
            ScriptsModule(serviceProvider),
            TestScriptModule(serviceProvider),
            MavenModule(serviceProvider),
            GitModule(serviceProvider),
            JetbrainsModule(serviceProvider),
            CliModule(serviceProvider),
            MainScriptModule(serviceProvider),
            JavaModule(serviceProvider),
            KotlinModule(serviceProvider),
            JteModule(serviceProvider),
            ProjectModule(serviceProvider),
            TestsModule(serviceProvider),
            JunitModule(serviceProvider),
            LibrariesModule(serviceProvider),
        )
    }
}