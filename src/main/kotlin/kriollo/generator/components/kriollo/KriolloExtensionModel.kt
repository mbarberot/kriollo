package kriollo.generator.components.kriollo

class KriolloExtensionModel(
    namespace: String,
    name: String,
    imports: List<String>,
    val provideType: String,
) : JavaModel(namespace, name, imports)