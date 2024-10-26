package com.gitlab.mbarberot.kriollo.generator.templating.jte

import com.gitlab.mbarberot.kriollo.generator.base.DirectoryGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class JteDirectoryGenerator(val serviceProvider: ServiceProvider): DirectoryGenerator() {

    override fun getDirectoryPath(): String {
        return serviceProvider.configuration.project.libs.jte.sourceDirectory
    }
}