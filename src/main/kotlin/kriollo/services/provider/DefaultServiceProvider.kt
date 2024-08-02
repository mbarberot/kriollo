package kriollo.services.provider

import kriollo.services.templating.TemplatingService
import kriollo.services.filesystem.FileSystemService

class DefaultServiceProvider(
    override val fileSystem: FileSystemService,
    override val templatingService: TemplatingService,
) : ServiceProvider