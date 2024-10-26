package com.gitlab.mbarberot.kriollo.generator.templating.jte

import com.gitlab.mbarberot.kriollo.generator.base.FileGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class JteRootFileGenerator(val serviceProvider: ServiceProvider): FileGenerator() {

    override fun getFilePath(): String {
        return "${serviceProvider.configuration.project.libs.jte.sourceDirectory}/.jteroot"
    }

    override fun getContent(serviceProvider: ServiceProvider) = ""
}