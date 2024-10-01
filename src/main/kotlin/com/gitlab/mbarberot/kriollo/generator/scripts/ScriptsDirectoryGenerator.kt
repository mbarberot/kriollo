package com.gitlab.mbarberot.kriollo.generator.scripts

import com.gitlab.mbarberot.kriollo.generator.base.DirectoryGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class ScriptsDirectoryGenerator(val serviceProvider: ServiceProvider) : DirectoryGenerator() {
    override fun getDirectoryPath() = "./${serviceProvider.configuration.scripts.dir}"
}