package kriollo.generator.components.kriollo

class KriolloTagModulesModel(
    namespace: String,
    name: String,
    imports: List<String>,
    val components: List<String>,
) : JavaModel(
    namespace,
    name,
    imports,
)