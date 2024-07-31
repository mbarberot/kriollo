package kriollo.generator.nix

import kriollo.generator.utils.BaseModel

data class DefaultNixFileModel(
    val packages: List<String> = listOf(),
    val shellHooks: List<String> = listOf(),
) : BaseModel()
