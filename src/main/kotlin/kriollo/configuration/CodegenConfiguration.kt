package kriollo.configuration

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
import kriollo.generator.script.ScriptsConfiguration
import kriollo.generator.templating.TemplatingConfiguration

data class CodegenConfiguration(
    val project: ProjectConfiguration,
    val cli: CliConfiguration = CliConfiguration(),
    val jetbrains: JetbrainsConfiguration = JetbrainsConfiguration(),
    val java: JavaConfiguration = JavaConfiguration(),
    val kotlin: KotlinConfiguration = KotlinConfiguration(),
    val nix: NixConfiguration = NixConfiguration(),
    val git: GitConfiguration = GitConfiguration(),
    val scripts: ScriptsConfiguration = ScriptsConfiguration(),
    val maven: MavenConfiguration = MavenConfiguration(),
    val templating: TemplatingConfiguration = TemplatingConfiguration(),
    val libs: JavaLibConfiguration = JavaLibConfiguration(),
    val components: ComponentsConfiguration = ComponentsConfiguration(),
)

