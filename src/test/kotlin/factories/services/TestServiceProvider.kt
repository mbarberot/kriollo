package factories.services

import kriollo.services.provider.ServiceProvider

class TestServiceProvider : ServiceProvider {
    override val fileSystem: TestFileSystemService = TestFileSystemService()
    override val templatingService: TestTemplatingService = TestTemplatingService()
}