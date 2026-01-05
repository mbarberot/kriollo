package factories.services

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationService
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.services.templating.DefaultTemplatingService
import com.gitlab.mbarberot.kriollo.services.templating.TemplatingService
import com.gitlab.mbarberot.kriollo.spi.ModuleProvider
import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.resolve.DirectoryCodeResolver
import org.jetbrains.kotlin.modules.Module
import java.nio.file.Path
import java.util.ServiceLoader

private fun withServiceLoader(): List<ModuleProvider> = ServiceLoader.load(ModuleProvider::class.java).filterNotNull()

class TestServiceProvider(
    override val configuration: CodegenConfiguration = BasicCodegenConfiguration(),
    override val fileSystem: TestFileSystemService = TestFileSystemService(),
    override val templatingService: TemplatingService = TestTemplatingService(),
    override val configurationService: ConfigurationService = TestConfigurationService(),
    override val moduleProviders: List<ModuleProvider> = withServiceLoader()
) : ServiceProvider {

    companion object {
        val jteTemplatingService = makeJteTemplatingService()

        fun makeJteTemplatingService(): TemplatingService {
            val jteTemplateEngine = TemplateEngine.create(
                DirectoryCodeResolver(Path.of("src/main/jte")),
                ContentType.Plain
            )
            val handlebarsTemplateEngine = Handlebars(ClassPathTemplateLoader("/templates")).apply { setPrettyPrint(true) }
            return DefaultTemplatingService(jteTemplateEngine, handlebarsTemplateEngine)
        }
    }
}
