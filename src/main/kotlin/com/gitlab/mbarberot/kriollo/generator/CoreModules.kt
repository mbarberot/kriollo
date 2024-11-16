package com.gitlab.mbarberot.kriollo.generator

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import com.gitlab.mbarberot.kriollo.generator.cli.CliModule
import com.gitlab.mbarberot.kriollo.generator.cli.script.MainScriptModule
import com.gitlab.mbarberot.kriollo.generator.git.GitModule
import com.gitlab.mbarberot.kriollo.generator.java.JavaModule
import com.gitlab.mbarberot.kriollo.generator.jetbrains.JetbrainsModule
import com.gitlab.mbarberot.kriollo.generator.kotlin.KotlinModule
import com.gitlab.mbarberot.kriollo.generator.libs.LibrariesModule
import com.gitlab.mbarberot.kriollo.generator.maven.MavenModule
import com.gitlab.mbarberot.kriollo.generator.nix.NixModule
import com.gitlab.mbarberot.kriollo.generator.project.ProjectModule
import com.gitlab.mbarberot.kriollo.generator.scripts.ScriptsModule
import com.gitlab.mbarberot.kriollo.generator.scripts.build.BuildScriptModule
import com.gitlab.mbarberot.kriollo.generator.scripts.test.TestScriptModule
import com.gitlab.mbarberot.kriollo.generator.templating.jte.JteModule
import com.gitlab.mbarberot.kriollo.generator.tests.TestsModule
import com.gitlab.mbarberot.kriollo.generator.tests.junit.JunitModule
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo")
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