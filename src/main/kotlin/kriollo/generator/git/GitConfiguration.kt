package kriollo.generator.git

data class GitConfiguration(
    val enabled: Boolean = false // TODO inject conventionnal commit in pre-commit hook
)