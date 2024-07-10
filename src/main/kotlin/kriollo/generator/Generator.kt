package kriollo.generator

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.extensions.JavaDependencyExtension
import kriollo.generator.base.extensions.MavenPluginExtension
import kriollo.generator.base.extensions.GitIgnoreExtension
import kriollo.generator.utils.ServiceProvider

/*
 * TODO : generate this and the extension
 *
 * [[components.kriollo-extensions]]
 * path: "kriollo/generator/base/extensions
 * availableExtensions: [
 *  { name = "GitIgnoreExtension", provides = "GitIgnoreRule", for = "kriollo.generator.git" },
 *  # ... and so on
 * ]
 */
abstract class Generator {
    abstract fun execute(configuration: CodegenConfiguration, serviceProvider: ServiceProvider)

    open fun registerExtension(extension: JavaDependencyExtension) {
        // Left empty for child classes to implement if needed
    }

    open fun registerExtension(extension: MavenPluginExtension) {
        // Left empty for child classes to implement if needed
    }

    open fun registerExtension(extension: GitIgnoreExtension) {
        // Left empty for child classes to implement if needed
    }
}