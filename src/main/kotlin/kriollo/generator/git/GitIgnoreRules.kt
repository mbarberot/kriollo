package kriollo.generator.git

data class GitIgnoreRules(
    val rules: List<String>,
    val description: String = ""
)
