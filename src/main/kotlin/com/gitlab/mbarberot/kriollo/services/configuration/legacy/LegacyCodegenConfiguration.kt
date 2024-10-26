package com.gitlab.mbarberot.kriollo.services.configuration.legacy

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.configuration.KriolloConfiguration
import com.gitlab.mbarberot.kriollo.generator.cli.CliConfiguration
import com.gitlab.mbarberot.kriollo.generator.components.ComponentsConfiguration
import com.gitlab.mbarberot.kriollo.generator.git.GitConfiguration
import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.java.JavaConfiguration
import com.gitlab.mbarberot.kriollo.generator.jetbrains.JetbrainsConfiguration
import com.gitlab.mbarberot.kriollo.generator.kotlin.KotlinConfiguration
import com.gitlab.mbarberot.kriollo.generator.libs.FeignConfiguration
import com.gitlab.mbarberot.kriollo.generator.libs.IJavaLibConfiguration
import com.gitlab.mbarberot.kriollo.generator.libs.JacksonConfiguration
import com.gitlab.mbarberot.kriollo.generator.libs.PicocliConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.nix.NixConfiguration
import com.gitlab.mbarberot.kriollo.generator.project.IProjectConfiguration
import com.gitlab.mbarberot.kriollo.generator.scripts.ScriptsConfiguration
import com.gitlab.mbarberot.kriollo.generator.templating.TemplatingConfiguration
import com.gitlab.mbarberot.kriollo.generator.templating.jte.JteConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.TestsConfiguration

data class LegacyCodegenConfiguration(
    override val kriollo: KriolloConfiguration = KriolloConfiguration(),
    override val project: MutableProjectConfiguration = MutableProjectConfiguration(),
    override val cli: CliConfiguration = CliConfiguration(),
    override val jetbrains: JetbrainsConfiguration = JetbrainsConfiguration(),
    override val java: JavaConfiguration = JavaConfiguration(),
    override val kotlin: KotlinConfiguration = KotlinConfiguration(),
    override val nix: NixConfiguration = NixConfiguration(),
    override val git: GitConfiguration = GitConfiguration(),
    override val scripts: ScriptsConfiguration = ScriptsConfiguration(),
    override val maven: MavenConfiguration = MavenConfiguration(),
    val templating: TemplatingConfiguration = TemplatingConfiguration(),
    val libs: MutableJavaLibConfiguration = MutableJavaLibConfiguration(),
    override val tests: TestsConfiguration = TestsConfiguration(),
    override val components: ComponentsConfiguration = ComponentsConfiguration(),
): CodegenConfiguration

data class MutableProjectConfiguration(
    override val groupId: String = "",
    override val name: String = "",
    override val version: String = "",
    override val mainClass: String = "",
    override val encoding: String = "UTF-8",
    override val dependencies: List<JavaArtifact> = listOf(),
    override var libs: MutableJavaLibConfiguration = MutableJavaLibConfiguration(),
) : IProjectConfiguration

data class MutableJavaLibConfiguration(
    override val jackson: JacksonConfiguration = JacksonConfiguration(),
    override val picocli: PicocliConfiguration = PicocliConfiguration(),
    override val feign: FeignConfiguration = FeignConfiguration(),
    override var jte: JteConfiguration = JteConfiguration(),
): IJavaLibConfiguration
