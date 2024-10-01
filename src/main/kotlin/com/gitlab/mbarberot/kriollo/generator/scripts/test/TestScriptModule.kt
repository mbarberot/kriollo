package com.gitlab.mbarberot.kriollo.generator.scripts.test

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import com.gitlab.mbarberot.kriollo.generator.CodegenModule
import com.gitlab.mbarberot.kriollo.generator.Generator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import javax.annotation.processing.Generated

@Generated("Generated by Kriollo v2024.2.1")
class TestScriptModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = serviceProvider.configuration.scripts.tests.enabled

    override fun provideGenerators(): List<Generator> = listOf(
        TestScriptGenerator(serviceProvider),
    )

}