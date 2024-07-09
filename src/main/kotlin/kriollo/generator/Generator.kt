package kriollo.generator

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.extensions.JavaDependencyExtension
import kriollo.generator.base.extensions.MavenPluginExtension
import kriollo.generator.utils.ServiceProvider

abstract class Generator {
    abstract fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider)

    open fun registerExtension(extension: JavaDependencyExtension) {
        // Left empty for child classes to implement if needed
    }

    open fun registerExtension(extension: MavenPluginExtension) {
        // Left empty for child classes to implement if needed
    }
}