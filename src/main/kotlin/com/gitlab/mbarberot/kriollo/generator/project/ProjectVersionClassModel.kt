package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.generator.components.kriollo.ClassModel

class ProjectVersionClassModel(
    val version: String,
    namespace: String,
    name: String,
    rootPackage: String,
) : ClassModel(
    namespace,
    name,
    rootPackage,
    listOf()
)
