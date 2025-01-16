package com.gitlab.mbarberot.kriollo.generator.git

data class GitConfiguration(
    val enabled: Boolean = false,
    val ignore: List<String> = listOf(),
)