package com.gitlab.mbarberot.kriollo.configuration

import com.gitlab.mbarberot.kriollo.KriolloVersion

data class KriolloConfiguration(
    val dir: String = "codegen",
    val version: String = KriolloVersion.VERSION
)
