package com.gitlab.mbarberot.kriollo.configuration

import com.gitlab.mbarberot.kriollo.generator.cli.CliConfiguration
import com.gitlab.mbarberot.kriollo.generator.components.ComponentsConfiguration
import com.gitlab.mbarberot.kriollo.generator.git.GitConfiguration
import com.gitlab.mbarberot.kriollo.generator.java.JavaConfiguration
import com.gitlab.mbarberot.kriollo.generator.jetbrains.JetbrainsConfiguration
import com.gitlab.mbarberot.kriollo.generator.kotlin.KotlinConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.nix.NixConfiguration
import com.gitlab.mbarberot.kriollo.generator.project.IProjectConfiguration
import com.gitlab.mbarberot.kriollo.generator.scripts.ScriptsConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.TestsConfiguration

interface CodegenConfiguration {
    val kriollo: KriolloConfiguration
    val project: IProjectConfiguration
    val cli: CliConfiguration
    val jetbrains: JetbrainsConfiguration
    val java: JavaConfiguration
    val kotlin: KotlinConfiguration
    val nix: NixConfiguration
    val git: GitConfiguration
    val scripts: ScriptsConfiguration
    val maven: MavenConfiguration
    val tests: TestsConfiguration
    val components: ComponentsConfiguration
} 
