package com.gitlab.mbarberot.kriollo.services.configuration.legacy

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationAdapter

fun interface DeprecationHandler {
    fun logDeprecation(message: String)
}

class LegacyConfigurationAdapter(
    private val deprecationHandler: DeprecationHandler = DeprecationHandler { message -> println("[!] $message") }
) : ConfigurationAdapter<LegacyCodegenConfiguration> {

    override fun adapt(backwardConfig: LegacyCodegenConfiguration): CodegenConfiguration {

        if(backwardConfig.tests.junit.enabled) {
            deprecationHandler.logDeprecation("tests.junit.enabled as been removed, junit is now enabled using the tests.enabled flag")
        }

        return backwardConfig
    }
}