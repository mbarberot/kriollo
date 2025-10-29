package com.gitlab.mbarberot.kriollo.services.provider

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationService
import com.gitlab.mbarberot.kriollo.services.filesystem.FileSystemService
import com.gitlab.mbarberot.kriollo.services.templating.TemplatingService
import com.gitlab.mbarberot.kriollo.spi.ModuleProvider

interface ServiceProvider {
    val configuration: CodegenConfiguration
    val fileSystem: FileSystemService
    val templatingService: TemplatingService
    val configurationService: ConfigurationService
    val moduleProviders: List<ModuleProvider>
}