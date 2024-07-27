package kriollo.test

data class TestConfiguration(
    val enabled: Boolean = false,
    val junitVersion: String = "5.10.0",
    val junitPlatformCommonsVersion: String = "1.10.3",
    val assertjVersion: String = "3.25.1",
)
