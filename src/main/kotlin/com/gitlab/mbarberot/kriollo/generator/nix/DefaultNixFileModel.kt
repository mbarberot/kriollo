package com.gitlab.mbarberot.kriollo.generator.nix

import com.gitlab.mbarberot.kriollo.generator.utils.BaseModel

data class DefaultNixFileModel(
    val packages: List<String> = listOf(),
    val shellHooks: List<String> = listOf(),
) : BaseModel()
