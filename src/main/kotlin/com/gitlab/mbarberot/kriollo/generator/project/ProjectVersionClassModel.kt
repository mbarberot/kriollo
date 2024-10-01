package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.generator.components.kriollo.JavaModel

class ProjectVersionClassModel(
    val version: String,
    namespace: String,
    name: String,
) : JavaModel(namespace, name, listOf())
