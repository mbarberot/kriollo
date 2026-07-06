package com.gitlab.mbarberot.kriollo.generator.maven

data class MavenConfiguration(
    val enabled: Boolean = false,
    val repositories: MavenRepositoriesConfiguration = MavenRepositoriesConfiguration(),
    val filtering: MavenFilteringConfiguration = MavenFilteringConfiguration()
)

data class MavenRepositoriesConfiguration(
    val jitpack: Boolean = false
)
