package kriollo.services.configuration

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.KriolloConfiguration
import kriollo.generator.cli.CliConfiguration
import kriollo.generator.components.ComponentsConfiguration
import kriollo.generator.git.GitConfiguration
import kriollo.generator.java.JavaConfiguration
import kriollo.generator.jetbrains.JetbrainsConfiguration
import kriollo.generator.kotlin.KotlinConfiguration
import kriollo.generator.libs.JavaLibConfiguration
import kriollo.generator.maven.MavenConfiguration
import kriollo.generator.nix.NixConfiguration
import kriollo.generator.project.ProjectConfiguration
import kriollo.generator.scripts.ScriptsConfiguration
import kriollo.generator.templating.TemplatingConfiguration
import kriollo.generator.tests.TestsConfiguration

data class BasicCodegenConfiguration(
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
    override val templating: TemplatingConfiguration = TemplatingConfiguration(),
    override val libs: JavaLibConfiguration = JavaLibConfiguration(),
    override val tests: TestsConfiguration = TestsConfiguration(),
    override val components: ComponentsConfiguration = ComponentsConfiguration(),
): CodegenConfiguration