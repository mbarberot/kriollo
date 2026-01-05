package com.gitlab.mbarberot.kriollo.services.provider

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationProvider
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationService
import com.gitlab.mbarberot.kriollo.services.configuration.kdl.KdlConfigurationReader
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyCodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyConfigurationAdapter
import com.gitlab.mbarberot.kriollo.services.filesystem.DefaultFileSystemService
import com.gitlab.mbarberot.kriollo.services.filesystem.FileSystemService
import com.gitlab.mbarberot.kriollo.services.templating.DefaultTemplatingService
import com.gitlab.mbarberot.kriollo.services.templating.TemplatingService
import com.gitlab.mbarberot.kriollo.spi.ModuleProvider
import gg.jte.ContentType
import gg.jte.TemplateEngine

class DefaultServiceProvider(
    override val moduleProviders: List<ModuleProvider>,
    override val configurationService: ConfigurationService,
) : ServiceProvider {
    override val configuration: CodegenConfiguration by lazy {
        ConfigurationProvider(
            KdlConfigurationReader("./codegen/codegen.kdl", LegacyCodegenConfiguration::class.java),
            LegacyConfigurationAdapter()
        )
    }

    override val fileSystem: FileSystemService by lazy {
        DefaultFileSystemService()
    }

    override val templatingService: TemplatingService by lazy {
        DefaultTemplatingService(
            TemplateEngine.createPrecompiled(ContentType.valueOf(configuration.project.libs.jte.contentType)),
            Handlebars(ClassPathTemplateLoader("/templates")).apply { setPrettyPrint(true) }
        )
    }
}