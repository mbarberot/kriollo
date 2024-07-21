package kriollo.generator.components.kriollo

class KriolloBaseGeneratorModel(
    namespace: String,
    name: String,
    imports: List<String>,
    val extensions: List<String>,
): JavaModel(namespace, name, imports ) {

}
