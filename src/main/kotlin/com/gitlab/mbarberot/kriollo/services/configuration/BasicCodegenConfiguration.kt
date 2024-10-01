package com.gitlab.mbarberot.kriollo.services.configuration

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.configuration.KriolloConfiguration
import com.gitlab.mbarberot.kriollo.generator.cli.CliConfiguration
import com.gitlab.mbarberot.kriollo.generator.components.ComponentsConfiguration
import com.gitlab.mbarberot.kriollo.generator.git.GitConfiguration
import com.gitlab.mbarberot.kriollo.generator.java.JavaConfiguration
import com.gitlab.mbarberot.kriollo.generator.jetbrains.JetbrainsConfiguration
import com.gitlab.mbarberot.kriollo.generator.kotlin.KotlinConfiguration
import com.gitlab.mbarberot.kriollo.generator.libs.JavaLibConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.nix.NixConfiguration
import com.gitlab.mbarberot.kriollo.generator.project.ProjectConfiguration
import com.gitlab.mbarberot.kriollo.generator.scripts.ScriptsConfiguration
import com.gitlab.mbarberot.kriollo.generator.templating.TemplatingConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.TestsConfiguration

data class BasicCodegenConfiguration(
    override val kriollo: KriolloConfiguration = KriolloConfiguration(),
    override val project: ProjectConfiguration = ProjectConfiguration(),
    override val cli: CliConfiguration = CliConfiguration(),
    override val jetbrains: com.gitlab.mbarberot.kriollo.generator.jetbrains.JetbrainsConfiguration = com.gitlab.mbarberot.kriollo.generator.jetbrains.JetbrainsConfiguration(),
    override val java: JavaConfiguration = JavaConfiguration(),
    override val kotlin: KotlinConfiguration = KotlinConfiguration(),
    override val nix: NixConfiguration = NixConfiguration(),
    override val git: GitConfiguration = GitConfiguration(),
    override val scripts: ScriptsConfiguration = ScriptsConfiguration(),
    override val maven: MavenConfiguration = MavenConfiguration(),
    override val templating: TemplatingConfiguration = TemplatingConfiguration(),
    override val libs: JavaLibConfiguration = JavaLibConfiguration(),
    override val tests: TestsConfiguration = TestsConfiguration(),
    override val components: ComponentsConfiguration = ComponentsConfiguration(),
): CodegenConfiguration