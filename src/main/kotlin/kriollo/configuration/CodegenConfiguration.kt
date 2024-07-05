package kriollo.configuration

data class CodegenConfiguration(
    val project: ProjectConfiguration,
    val nix: NixConfiguration,
    val git: GitConfiguration,
    val mainScript: MainScriptConfiguration,
    val buildScript: BuildScriptConfiguration,
    val maven: MavenConfiguration
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

class MavenConfiguration(
    val enabled: Boolean = false
)
