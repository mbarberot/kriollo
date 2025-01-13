package factories.services

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider
import com.gitlab.mbarberot.kriollo.services.templating.DefaultTemplatingService
import com.gitlab.mbarberot.kriollo.services.templating.TemplatingService
import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.resolve.DirectoryCodeResolver
import java.nio.file.Path

class TestServiceProvider(
    override val configuration: CodegenConfiguration = BasicCodegenConfiguration(),
    override val fileSystem: TestFileSystemService = TestFileSystemService(),
    override val templatingService: TemplatingService = TestTemplatingService(),
) : ServiceProvider {

    companion object {
        val jteTemplatingService = makeJteTemplatingService()

        fun makeJteTemplatingService(): TemplatingService {
            val jteTemplateEngine = TemplateEngine.create(
                DirectoryCodeResolver(Path.of("src/main/jte")),
                ContentType.Plain)
            return DefaultTemplatingService(jteTemplateEngine)
        }
    }
}
