package com.gitlab.mbarberot.kriollo.generator.java

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import com.gitlab.mbarberot.kriollo.generator.CodegenModule
import com.gitlab.mbarberot.kriollo.generator.Generator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v2024.2.1")
class JavaModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = serviceProvider.configuration.java.enabled

    override fun provideGenerators(): List<Generator> = listOf(
    )

    override fun provideExtensions(generator: Generator) {
        generator.registerExtension(JavaGitIgnoreExtension(serviceProvider))
        generator.registerExtension(JavaNixDefaultExtension(serviceProvider))
    }
}