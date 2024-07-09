package kriollo.generator.templating.jte

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.extensions.MavenPluginExtension
import kriollo.generator.utils.ServiceProvider

class JteMavenPluginExtension(
    val configuration: CodegenConfiguration,
    val serviceProvider: ServiceProvider,
) : MavenPluginExtension {

    override fun providePlugins(): List<String> {
        val (_, _, _, version, sourceDirectory, contentType) = configuration.templating.jte
        return listOf(
            serviceProvider.templatingService.renderToString(
                "generator/templating/jte/maven-plugin.kte",
                JteMavenPluginModel(version, sourceDirectory, contentType)
            )
        )
    }
}