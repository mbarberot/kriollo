package com.gitlab.mbarberot.kriollo.generator.components.kriollo

class KriolloBaseGeneratorModel(
    namespace: String,
    name: String,
    rootPackage: String,
    imports: List<String>,
    val extensions: List<String>,
): ClassModel(namespace, name, rootPackage, imports ) {

}
