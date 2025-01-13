package features.generate.index

import com.gitlab.mbarberot.kriollo.features.Generate
import factories.Configs
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GenerateIndexFileTest {

    @Test
    fun `kriollo generates an index file`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.basicConfig(),
            fileSystem = TestFileSystemService(fakeFileSystem),
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.filenames()).contains("codegen/generated-files.txt")
    }

    @Test
    fun `kriollo's generated file lists any generated file`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.kotlinProject(),
            fileSystem = TestFileSystemService(fakeFileSystem),
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getContentOf("codegen/generated-files.txt"))
            .contains("src/main/kotlin/org/acme/anvil/AnvilVersion.kt")
    }
}