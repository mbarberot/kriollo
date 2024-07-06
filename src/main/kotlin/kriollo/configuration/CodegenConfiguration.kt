package kriollo.configuration


data class CodegenConfiguration(
    val project: ProjectConfiguration,
    val cli: CliConfiguration = CliConfiguration(),
    val kotlin: KotlinConfiguration = KotlinConfiguration(),
    val nix: NixConfiguration = NixConfiguration(),
    val git: GitConfiguration = GitConfiguration(),
    val scripts: ScriptsConfiguration = ScriptsConfiguration(),
    val maven: MavenConfiguration = MavenConfiguration(),
    val templating: TemplatingConfiguration = TemplatingConfiguration(),
)

data class ProjectConfiguration(
    val mainClass: String // TODO add artifact informations and finalName
)

data class CliConfiguration(
    val enabled: Boolean = false, // TODO use this to generate the assemble plugin configuration in pom generation
    val script: MainScriptConfiguration = MainScriptConfiguration(),
)

data class MainScriptConfiguration(
    val enabled: Boolean = false,
    val fileName: String = "run.sh",
    val targetDirectory: String = "./build",
    val jarLocation: String = "",
)

data class KotlinConfiguration(
    val enabled: Boolean = false // TODO use this to generate kotlin configuration in the pom
)

data class NixConfiguration(
    val enabled: Boolean = false
)

data class GitConfiguration(
    val enabled: Boolean = false // TODO inject conventionnal commit in pre-commit hook
)

data class ScriptsConfiguration(
    val build: BuildScriptConfiguration = BuildScriptConfiguration()
)

data class BuildScriptConfiguration(
    val enabled: Boolean = false,
    val customSteps: List<BuildScriptCustomStepConfiguration> = listOf()
)

data class BuildScriptCustomStepConfiguration(
    val name: String,
    val command: String
)

data class MavenConfiguration(
    val enabled: Boolean = false
)

data class TemplatingConfiguration(
    val enabled: Boolean = false,
    val jte: JteConfiguration = JteConfiguration()
)

data class JteConfiguration(
    val enabled: Boolean = false,
    val groupId: String = "gg.jte",
    val artifactId: String = "jte",
    val version: String = "",
    val sourceDirectory: String = "src/main/jte",
    val contentType: String = "Html"
) {
    fun getArtifacts(configuration: CodegenConfiguration): List<JavaArtifact> {
        return buildList {
            add(JavaArtifact(groupId, artifactId, version))

            if(configuration.kotlin.enabled) {
                add(JavaArtifact(groupId, "jte-kotlin", version, "compile"))
            }
        }
    }
}