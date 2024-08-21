package kriollo.generator.java

import kriollo.generator.nix.DefaultNixFileExtension
import kriollo.generator.nix.DefaultNixPartial
import kriollo.services.provider.ServiceProvider

class JavaNixDefaultExtension(
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
