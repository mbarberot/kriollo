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

        // 2024.x

        if (backwardConfig.libs.jackson.enabled) {
            deprecationHandler.logDeprecation("libs has been moved into project section")
            backwardConfig.project.libs = backwardConfig.libs
        }

        if (backwardConfig.templating.enabled) {
            deprecationHandler.logDeprecation("templating has been removed, related libs have been moved into project.libs")
            if (backwardConfig.templating.jte.enabled) {
                backwardConfig.project.libs.jte = backwardConfig.templating.jte
            }
        }

        // 2025.x

        if(backwardConfig.tests.junit.enabled) {
            deprecationHandler.logDeprecation("tests.junit.enabled as been removed, junit is now enabled using the tests.enabled flag")
        }

        return backwardConfig
    }
}