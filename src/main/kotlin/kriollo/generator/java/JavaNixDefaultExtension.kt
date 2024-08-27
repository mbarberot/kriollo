package kriollo.generator.java

import kriollo.generator.nix.DefaultNixFileExtension
import kriollo.generator.nix.DefaultNixPartial
import kriollo.services.provider.ServiceProvider

class JavaNixDefaultExtension(
    val serviceProvider: ServiceProvider
): DefaultNixFileExtension {
    override fun provide(): List<DefaultNixPartial> {
        val javaNixPackage = "jdk${serviceProvider.configuration.java.version}"
        return listOf(
            DefaultNixPartial(
                packages = listOf(javaNixPackage),
                shellHook = "export JAVA_HOME=${'$'}{$javaNixPackage.home}"
            )
        )
    }
}
