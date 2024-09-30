package kriollo.generator.maven

data class MavenConfiguration(
    val enabled: Boolean = false,
    val repositories: MavenRepositoriesConfiguration = MavenRepositoriesConfiguration()
)

data class MavenRepositoriesConfiguration(
    val jitpack: Boolean = false
)
