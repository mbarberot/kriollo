package com.gitlab.mbarberot.kriollo.services.provider

import gg.jte.ContentType
import gg.jte.TemplateEngine
import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.*
import com.gitlab.mbarberot.kriollo.services.filesystem.DefaultFileSystemService
import com.gitlab.mbarberot.kriollo.services.filesystem.FileSystemService
import com.gitlab.mbarberot.kriollo.services.templating.DefaultTemplatingService
import com.gitlab.mbarberot.kriollo.services.templating.TemplatingService

class DefaultServiceProvider(
) : ServiceProvider {
    override val configuration: CodegenConfiguration by lazy {
        ConfigurationProvider(
            ConfigurationReader { return@ConfigurationReader readKdlConfiguration("./codegen/codegen.kdl") },
            NoOpConfigurationAdapter()
        )
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