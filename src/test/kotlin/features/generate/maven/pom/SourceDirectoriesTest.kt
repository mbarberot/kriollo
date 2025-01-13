package features.generate.maven.pom

import com.gitlab.mbarberot.kriollo.features.Generate
import factories.Configs
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SourceDirectoriesTest {

    @Test
    fun `sourceDirectory et testSourceDirectory in pom for kotlin`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.kotlinProject(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getFile("pom.xml")).isNotNull()
        assertThat(fakeFileSystem.getContentOf("pom.xml"))
            .contains("<sourceDirectory>src/main/kotlin</sourceDirectory>")
            .contains("<testSourceDirectory>src/test/kotlin</testSourceDirectory>")
    }

    @Test
    fun `sourceDirectory et testSourceDirectory in pom for java`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProject(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getFile("pom.xml")).isNotNull()
        assertThat(fakeFileSystem.getContentOf("pom.xml"))
            .contains("<sourceDirectory>src/main/java</sourceDirectory>")
            .contains("<testSourceDirectory>src/test/java</testSourceDirectory>")
    }
}