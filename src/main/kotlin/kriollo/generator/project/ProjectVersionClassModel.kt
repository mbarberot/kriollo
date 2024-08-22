package kriollo.generator.project

import kriollo.generator.components.kriollo.JavaModel

class ProjectVersionClassModel(
    val version: String,
    namespace: String,
    name: String,
) : JavaModel(namespace, name, listOf())
