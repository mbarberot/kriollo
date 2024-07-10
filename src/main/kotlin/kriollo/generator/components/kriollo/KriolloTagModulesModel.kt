package kriollo.generator.components.kriollo

import kriollo.generator.utils.BaseModel

data class KriolloTagModulesModel(
    val namespace: String,
    val className: String,
    val components: List<KriolloTagModulesComponentModel>,
) : BaseModel()