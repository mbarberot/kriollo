package factories.services

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class TestServiceProvider(
    override val configuration: CodegenConfiguration = BasicCodegenConfiguration(),
    override val fileSystem: TestFileSystemService = TestFileSystemService(),
    override val templatingService: TestTemplatingService = TestTemplatingService(),
) : ServiceProvider
