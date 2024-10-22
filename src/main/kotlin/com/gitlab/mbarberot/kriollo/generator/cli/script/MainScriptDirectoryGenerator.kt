package com.gitlab.mbarberot.kriollo.generator.cli.script

import com.gitlab.mbarberot.kriollo.generator.base.DirectoryGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class MainScriptDirectoryGenerator(val serviceProvider: ServiceProvider): DirectoryGenerator() {

    override fun getDirectoryPath(): String {
        return serviceProvider.configuration.cli.script.targetDirectory
    }
}