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
import kriollo.generator.scripts.ScriptsConfiguration
import kriollo.generator.templating.TemplatingConfiguration
import kriollo.generator.tests.TestsConfiguration

interface CodegenConfiguration {
    val kriollo: KriolloConfiguration
    val project: ProjectConfiguration
    val cli: CliConfiguration
    val jetbrains: JetbrainsConfiguration
    val java: JavaConfiguration
    val kotlin: KotlinConfiguration
    val nix: NixConfiguration
    val git: GitConfiguration
    val scripts: ScriptsConfiguration
    val maven: MavenConfiguration
    val templating: TemplatingConfiguration
    val libs: JavaLibConfiguration
    val tests: TestsConfiguration
    val components: ComponentsConfiguration
} 
