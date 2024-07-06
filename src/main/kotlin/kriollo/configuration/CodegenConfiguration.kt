package kriollo.configuration

data class CodegenConfiguration(
    val project: ProjectConfiguration,
    val nix: NixConfiguration = NixConfiguration(),
    val git: GitConfiguration = GitConfiguration(),
    val mainScript: MainScriptConfiguration = MainScriptConfiguration(),
    val buildScript: BuildScriptConfiguration = BuildScriptConfiguration(),
    val maven: MavenConfiguration = MavenConfiguration(),
    val templating: TemplatingConfiguration = TemplatingConfiguration(),
)

data class ProjectConfiguration(
    val mainClass: String
)

data class NixConfiguration(
    val enabled: Boolean = false
)

data class GitConfiguration(
    val enabled: Boolean = false
)

class MainScriptConfiguration

data class BuildScriptConfiguration(
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
    val version: String = ""
) {
    fun getArtifact(): JavaArtifact {
        return JavaArtifact(groupId, artifactId, version)
    }
}