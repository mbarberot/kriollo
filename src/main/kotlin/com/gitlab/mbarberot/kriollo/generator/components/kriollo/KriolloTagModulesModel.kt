package com.gitlab.mbarberot.kriollo.generator.components.kriollo

class KriolloTagModulesModel(
    namespace: String,
    name: String,
    rootPackage: String,
    imports: List<String>,
    val components: List<String>,
) : ClassModel(
    namespace,
    name,
    rootPackage,
    imports,
)