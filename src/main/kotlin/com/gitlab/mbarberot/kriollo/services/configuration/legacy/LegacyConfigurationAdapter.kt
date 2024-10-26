package com.gitlab.mbarberot.kriollo.services.configuration.legacy

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationAdapter

class LegacyConfigurationAdapter : ConfigurationAdapter<LegacyCodegenConfiguration> {
    override fun adapt(backwardConfig: LegacyCodegenConfiguration): CodegenConfiguration {
        if (backwardConfig.libs.jackson.enabled) {
            logDeprecation("libs has been moved into project section")
            backwardConfig.project.libs = backwardConfig.libs
        }

        if (backwardConfig.templating.enabled) {
            logDeprecation("templating has been removed, related libs have been moved into project.libs")
            if (backwardConfig.templating.jte.enabled) {
                backwardConfig.project.libs.jte = backwardConfig.templating.jte
            }
        }

        return backwardConfig
    }

    private fun logDeprecation(message: String) {
        println("[!] $message")
    }
}