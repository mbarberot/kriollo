package kriollo.generator.components.kriollo

import kriollo.generator.utils.BaseModel

class KriolloExtensionModel(
    namespace: String,
    name: String,
    imports: List<String>,
    val provideType: String,
) : JavaModel(namespace, name, imports)

open class JavaModel(
    val namespace: String,
    val name: String,
    val imports: List<String>,
) : BaseModel()