package com.gitlab.mbarberot.kriollo.generator.components.kriollo

class KriolloExtensionModel(
    namespace: String,
    name: String,
    rootPackage: String,
    imports: List<String>,
    val provideType: String,
) : ClassModel(namespace, name, rootPackage, imports)
