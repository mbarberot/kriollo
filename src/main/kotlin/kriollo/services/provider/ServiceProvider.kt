package kriollo.services.provider

import kriollo.services.templating.TemplatingService
import kriollo.services.filesystem.FileSystemService

interface ServiceProvider {
    val fileSystem: FileSystemService
    val templatingService: TemplatingService
}