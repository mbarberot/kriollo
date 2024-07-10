package kriollo.generator.components.kriollo.configuration

data class KriolloComponentConfiguration(
    val path: String,
    val name: String,
    val activationKey: String,
    val generators: List<KriolloGeneratorConfiguration> = listOf(),
    val extensions: List<KriolloExtensionConfiguration> = listOf(),
)