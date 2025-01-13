package features.generate.project.version

import factories.Configs
import factories.services.TestServiceProvider
import com.gitlab.mbarberot.kriollo.generator.project.ProjectVersionGenerator
import factories.services.TestFileSystemService
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GenerateVersionUtilityClassTest {

    @Test
    fun `generate kotlin version utility class`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.kotlinProject(),
            fileSystem = TestFileSystemService(fakeFileSystem)
        )
        val generator = ProjectVersionGenerator(serviceProvider)

        // Act
        generator.execute(serviceProvider)

        // Assert
        assertThat(fakeFileSystem.filenames()).contains("src/main/kotlin/org/acme/anvil/AnvilVersion.kt")
    }

    @Test
    fun `generate java version utility class`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProject(),
            fileSystem = TestFileSystemService(fakeFileSystem)
        )
        val generator = ProjectVersionGenerator(serviceProvider)

        // Act
        generator.execute(serviceProvider)

        // Assert
        assertThat(fakeFileSystem.filenames()).contains("src/main/java/org/acme/anvil/AnvilVersion.java")
    }

}