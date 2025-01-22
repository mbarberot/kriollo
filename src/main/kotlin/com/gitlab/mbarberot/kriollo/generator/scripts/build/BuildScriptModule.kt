package com.gitlab.mbarberot.kriollo.generator.scripts.build

// /!\ Warning
// This file is generated by Kriollo, do not edit it manually 

import com.gitlab.mbarberot.kriollo.generator.CodegenModule
import com.gitlab.mbarberot.kriollo.generator.Generator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.kriollo.Generated

@Generated("Generated by Kriollo")
class BuildScriptModule(
    val serviceProvider: ServiceProvider,
) : CodegenModule() {

    override fun isActivated(): Boolean = serviceProvider.configuration.scripts.build.enabled

    override fun provideGenerators(): List<Generator> = listOf(
        BuildScriptGenerator(serviceProvider),
    )

}