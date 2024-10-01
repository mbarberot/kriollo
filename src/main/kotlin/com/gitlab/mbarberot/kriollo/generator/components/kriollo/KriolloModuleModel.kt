package com.gitlab.mbarberot.kriollo.generator.components.kriollo

import com.gitlab.mbarberot.kriollo.generator.utils.BaseModel

data class KriolloModuleModel(
    val namespace: String,
    val name: String,
    val activation: String,
    val generators: List<String>,
    val extensions: List<String>,
): BaseModel()