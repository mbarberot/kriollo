package kriollo.services.provider

import kriollo.configuration.CodegenConfiguration
import kriollo.services.filesystem.FileSystemService
import kriollo.services.templating.TemplatingService

interface ServiceProvider {
    val configuration: CodegenConfiguration
    val fileSystem: FileSystemService
    val templatingService: TemplatingService
}