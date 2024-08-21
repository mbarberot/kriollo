package kriollo.services.provider

import kriollo.configuration.CodegenConfiguration
import kriollo.services.filesystem.FileSystemService
import kriollo.services.templating.TemplatingService

class DefaultServiceProvider(
    override val configuration: CodegenConfiguration,
    override val fileSystem: FileSystemService,
    override val templatingService: TemplatingService,
) : ServiceProvider