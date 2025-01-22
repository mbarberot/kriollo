package com.gitlab.mbarberot.kriollo.generator.components.kriollo

class KriolloModuleModel(
    namespace: String,
    name: String,
    rootPackage: String,
    val activation: String,
    val generators: List<String>,
    val extensions: List<String>,
) : ClassModel(
    namespace,
    name,
    rootPackage,
    listOf()
)