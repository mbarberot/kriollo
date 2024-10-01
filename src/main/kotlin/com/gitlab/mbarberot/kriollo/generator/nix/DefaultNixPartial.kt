package com.gitlab.mbarberot.kriollo.generator.nix

data class DefaultNixPartial(
    val packages: List<String> = listOf(),
    val shellHook: String = "",
)
