package com.gitlab.mbarberot.kriollo.services.configuration.legacy

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationAdapter

class LegacyConfigurationAdapter: ConfigurationAdapter<LegacyCodegenConfiguration> {
    override fun adapt(backwardConfig: LegacyCodegenConfiguration): CodegenConfiguration {
        if(backwardConfig.libs.jackson.enabled) {
            logDeprecation("libs has been moved into project section")
            backwardConfig.project.libs = backwardConfig.libs
        }

        return backwardConfig
    }

    private fun logDeprecation(message: String){
        println("[!] $message")
    }
}