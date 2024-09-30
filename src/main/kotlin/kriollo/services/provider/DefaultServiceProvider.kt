package kriollo.services.provider

import gg.jte.ContentType
import gg.jte.TemplateEngine
import kriollo.configuration.CodegenConfiguration
import kriollo.services.configuration.KdlCodegenConfiguration
import kriollo.services.filesystem.DefaultFileSystemService
import kriollo.services.filesystem.FileSystemService
import kriollo.services.templating.DefaultTemplatingService
import kriollo.services.templating.TemplatingService

class DefaultServiceProvider(
) : ServiceProvider {
    override val configuration: CodegenConfiguration by lazy {
        KdlCodegenConfiguration("./codegen/codegen.kdl")
    }

    override val fileSystem: FileSystemService by lazy {
        DefaultFileSystemService()
    }

    override val templatingService: TemplatingService by lazy {
        DefaultTemplatingService(
            TemplateEngine.createPrecompiled(ContentType.valueOf(configuration.templating.jte.contentType)),
        )
    }
}