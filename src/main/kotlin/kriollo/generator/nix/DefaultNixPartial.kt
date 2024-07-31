package kriollo.generator.nix

data class DefaultNixPartial(
    val packages: List<String> = listOf(),
    val shellHook: String = "",
)
