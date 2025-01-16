package com.gitlab.mbarberot.kriollo.generator.kotlin

import com.gitlab.mbarberot.kriollo.generator.java.JavaNixDefaultExtension
import com.gitlab.mbarberot.kriollo.generator.nix.DefaultNixFileExtension
import com.gitlab.mbarberot.kriollo.generator.nix.DefaultNixPartial
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class KotlinDefaultNixExtension(
    serviceProvider: ServiceProvider,
) : JavaNixDefaultExtension(serviceProvider), DefaultNixFileExtension {

    override fun provide(): List<DefaultNixPartial> {
        return buildList {
            addAll(super.provide())
            add(DefaultNixPartial(packages = listOf("kotlin")))
        }
    }
}
