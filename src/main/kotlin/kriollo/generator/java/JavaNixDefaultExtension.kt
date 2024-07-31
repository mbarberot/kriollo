package kriollo.generator.java

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.nix.DefaultNixFileExtension
import kriollo.generator.nix.DefaultNixPartial
import kriollo.generator.utils.ServiceProvider

class JavaNixDefaultExtension(
    val configuration: CodegenConfiguration,
    val serviceProvider: ServiceProvider
): DefaultNixFileExtension {
    override fun provide(): List<DefaultNixPartial> {
        return listOf(
            DefaultNixPartial(
                packages = listOf("jdk21"),
                shellHook = "export JAVA_HOME=${'$'}{jdk21.home}"
            )
        )
    }
}
