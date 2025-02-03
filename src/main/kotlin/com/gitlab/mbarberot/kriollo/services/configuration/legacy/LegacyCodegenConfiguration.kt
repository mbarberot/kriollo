package com.gitlab.mbarberot.kriollo.services.configuration.legacy

import com.gitlab.mbarberot.kriollo.HardcodedValues
import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.configuration.KriolloConfiguration
import com.gitlab.mbarberot.kriollo.generator.cli.CliConfiguration
import com.gitlab.mbarberot.kriollo.generator.components.ComponentsConfiguration
import com.gitlab.mbarberot.kriollo.generator.git.GitConfiguration
import com.gitlab.mbarberot.kriollo.generator.java.JavaConfiguration
import com.gitlab.mbarberot.kriollo.generator.jetbrains.JetbrainsConfiguration
import com.gitlab.mbarberot.kriollo.generator.kotlin.KotlinConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.nix.NixConfiguration
import com.gitlab.mbarberot.kriollo.generator.project.ProjectConfiguration
import com.gitlab.mbarberot.kriollo.generator.scripts.ScriptsConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.ITestsConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.coverage.CoverageConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.junit.IJUnitConfiguration

data class LegacyCodegenConfiguration(
    override val kriollo: KriolloConfiguration = KriolloConfiguration(),
    override val project: ProjectConfiguration = ProjectConfiguration(),
    override val cli: CliConfiguration = CliConfiguration(),
    override val jetbrains: JetbrainsConfiguration = JetbrainsConfiguration(),
    override val java: JavaConfiguration = JavaConfiguration(),
    override val kotlin: KotlinConfiguration = KotlinConfiguration(),
    override val nix: NixConfiguration = NixConfiguration(),
    override val git: GitConfiguration = GitConfiguration(),
    override val scripts: ScriptsConfiguration = ScriptsConfiguration(),
    override val maven: MavenConfiguration = MavenConfiguration(),
    override val tests: LegacyTestConfiguration = LegacyTestConfiguration(),
    override val components: ComponentsConfiguration = ComponentsConfiguration(),
): CodegenConfiguration

data class LegacyTestConfiguration(
    override val enabled: Boolean = false,
    override val junit: LegacyJUnitConfiguration = LegacyJUnitConfiguration(),
    override val coverage: CoverageConfiguration = CoverageConfiguration(),
): ITestsConfiguration

data class LegacyJUnitConfiguration(
    val enabled: Boolean = false,
    override val junitVersion: String = HardcodedValues.DEFAULT_JUNIT_VERSION,
    override val assertjVersion: String = HardcodedValues.DEFAULT_ASSERTJ_VERSION,
) : IJUnitConfiguration