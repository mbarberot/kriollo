package com.gitlab.mbarberot.kriollo.generator.maven

import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class MavenCustomBuildStepExtension(val serviceProvider: ServiceProvider) : MavenExecPluginExtension {
    override fun provide(): List<MavenExecPlugin> {
        return serviceProvider
            .configuration
            .project
            .customBuildSteps
            .map { (id, step, command) -> MavenExecPlugin(id,step, command) }
    }
}
