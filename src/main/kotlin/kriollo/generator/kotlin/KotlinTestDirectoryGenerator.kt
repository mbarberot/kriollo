package kriollo.generator.kotlin

import kriollo.generator.base.DirectoryGenerator
import kriollo.services.provider.ServiceProvider

class KotlinTestDirectoryGenerator(
    val serviceProvider: ServiceProvider
) : DirectoryGenerator() {
    override fun getDirectoryPath(): String {
        return "./src/test/kotlin"
    }
}