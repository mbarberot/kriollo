package com.gitlab.mbarberot.kriollo.generator.jetbrains

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import com.gitlab.mbarberot.kriollo.generator.CodegenModule
import com.gitlab.mbarberot.kriollo.generator.Generator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.kriollo.Generated

@Generated("Generated by Kriollo")
class JetbrainsModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = serviceProvider.configuration.jetbrains.enabled

    override fun provideGenerators(): List<Generator> = listOf(
    )

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(JetbrainsGitIgnoreExtension(serviceProvider))
    }
}