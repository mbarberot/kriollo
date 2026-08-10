package specs

import com.gitlab.mbarberot.kriollo.features.Generate
import factories.Configs
import factories.services.TestConfigurationService
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.concordion.api.ConcordionFixture

@ConcordionFixture
class GeneratedFilesIndexFixture: BaseFixture() {

    var indexFileName: String = ""

    fun isGenerated(result: GeneratedFiles, name: String): Boolean = result.contains(name)

    fun runGenerationPhase(): GeneratedFiles {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.basicConfig(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            configurationService = TestConfigurationService.javaMavenProject(),
        )

        // Act
        Generate(serviceProvider).generate()

        // Assert
        return GeneratedFiles(
            fakeFileSystem.filenames()
        )
    }
}

class GeneratedFiles(
    val filenames: List<String>
) {
    fun contains(path: String): Boolean = filenames.contains(path)
    fun getByPath(path: String): String =
        filenames.find { it.startsWith(path) }
            ?: "File $path not found"
}