package kriollo.generator.components.kriollo.configuration

data class KriolloExtensionProviderConfiguration(
    val name: String,
    val provideType: String
) {
    fun getProvidedTypeClassName() = provideType.substringAfterLast(".").replaceFirstChar(Char::titlecaseChar)
}
