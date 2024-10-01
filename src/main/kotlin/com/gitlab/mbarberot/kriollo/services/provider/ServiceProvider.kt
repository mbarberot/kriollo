package com.gitlab.mbarberot.kriollo.services.provider

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.filesystem.FileSystemService
import com.gitlab.mbarberot.kriollo.services.templating.TemplatingService

interface ServiceProvider {
    val configuration: CodegenConfiguration
    val fileSystem: FileSystemService
    val templatingService: TemplatingService
}