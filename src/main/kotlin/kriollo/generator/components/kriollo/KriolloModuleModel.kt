package kriollo.generator.components.kriollo

import kriollo.generator.utils.BaseModel

data class KriolloModuleModel(
    val namespace: String,
    val name: String,
    val activation: String,
    val generators: List<String>,
    val extensions: List<String>,
): BaseModel()