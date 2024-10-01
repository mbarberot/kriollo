package com.gitlab.mbarberot.kriollo.generator.components.kriollo

data class KriolloComponentConfiguration(
    val path: String,
    val name: String,
    val tag: String,
    val activationKey: String,
    val generators: List<KriolloGeneratorConfiguration> = listOf(),
    val extensions: List<KriolloExtensionConfiguration> = listOf(),
    val provideExtensions: List<KriolloExtensionProviderConfiguration> = listOf()
) {
    fun getClassName() = name.replaceFirstChar(Char::titlecaseChar) + "Module"
    fun getNamespace() = path.replace("/", ".")
}