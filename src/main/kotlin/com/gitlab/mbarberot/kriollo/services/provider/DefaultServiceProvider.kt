package com.gitlab.mbarberot.kriollo.services.provider

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationProvider
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyCodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyConfigurationAdapter
import com.gitlab.mbarberot.kriollo.services.configuration.readKdlConfiguration
import com.gitlab.mbarberot.kriollo.services.filesystem.DefaultFileSystemService
import com.gitlab.mbarberot.kriollo.services.filesystem.FileSystemService
import com.gitlab.mbarberot.kriollo.services.templating.DefaultTemplatingService
import com.gitlab.mbarberot.kriollo.services.templating.TemplatingService
import gg.jte.ContentType
import gg.jte.TemplateEngine

class DefaultServiceProvider(
) : ServiceProvider {
    override val configuration: CodegenConfiguration by lazy {
        ConfigurationProvider(
            { readKdlConfiguration("./codegen/codegen.kdl", LegacyCodegenConfiguration::class.java) },
            LegacyConfigurationAdapter()
        )
    }

    override val fileSystem: FileSystemService by lazy {
        DefaultFileSystemService()
    }

    override val templatingService: TemplatingService by lazy {
        DefaultTemplatingService(
            TemplateEngine.createPrecompiled(ContentType.valueOf(configuration.project.libs.jte.contentType)),
        )
    }
}