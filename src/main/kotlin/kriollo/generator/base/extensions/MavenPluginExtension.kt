package kriollo.generator.base.extensions

fun interface MavenPluginExtension {
    fun providePlugins(): List<String>
}
