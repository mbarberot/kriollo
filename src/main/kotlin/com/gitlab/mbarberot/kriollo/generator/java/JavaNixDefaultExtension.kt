package com.gitlab.mbarberot.kriollo.generator.java

import com.gitlab.mbarberot.kriollo.generator.nix.DefaultNixFileExtension
import com.gitlab.mbarberot.kriollo.generator.nix.DefaultNixPartial
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

open class JavaNixDefaultExtension(
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
