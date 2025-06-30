package com.gitlab.mbarberot.kriollo.generator.nix

data class NixConfiguration(
    val enabled: Boolean = false,
    val customPackages: List<String> = listOf()
)