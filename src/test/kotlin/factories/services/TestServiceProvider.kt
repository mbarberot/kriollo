package factories.services

import kriollo.configuration.CodegenConfiguration
import kriollo.services.configuration.BasicCodegenConfiguration
import kriollo.services.provider.ServiceProvider

class TestServiceProvider(
    override val configuration: CodegenConfiguration = BasicCodegenConfiguration(),
    override val fileSystem: TestFileSystemService = TestFileSystemService(),
    override val templatingService: TestTemplatingService = TestTemplatingService(),
) : ServiceProvider
