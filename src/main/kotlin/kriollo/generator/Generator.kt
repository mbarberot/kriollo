package kriollo.generator

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.extensions.JavaDependenciesGeneratorExtensions
import kriollo.generator.utils.ServiceProvider

abstract class Generator {
    abstract fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider)

    open fun registerExtension(extension: JavaDependenciesGeneratorExtensions) {
        // Left empty for child classes to implement if needed
    }

}