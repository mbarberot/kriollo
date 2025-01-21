package features.generate.project.version

import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.generator.project.ProjectVersionGenerator
import factories.Configs
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GeneratedAnnotationTest {

    @Test
    fun `generate kotlin annotation`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.kotlinProject(),
            fileSystem = TestFileSystemService(fakeFileSystem)
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.filenames()).contains("src/main/kotlin/org/acme/anvil/kriollo/Generated.kt")
    }

    @Test
    fun `generate java version utility class`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProject(),
            fileSystem = TestFileSystemService(fakeFileSystem)
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.filenames()).contains("src/main/java/org/acme/anvil/kriollo/Generated.java")
    }

    @Test
    fun `use the generated annotation in classes`() {
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
        assertThat(fakeFileSystem.getContentOf("src/main/java/org/acme/anvil/AnvilVersion.java"))
            .contains("import org.acme.anvil.kriollo.Generated;")
            .contains("@Generated")
    }
}