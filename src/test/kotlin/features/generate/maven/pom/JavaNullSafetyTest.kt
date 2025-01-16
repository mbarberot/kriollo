package features.generate.maven.pom

import com.gitlab.mbarberot.kriollo.features.Generate
import factories.Configs
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JavaNullSafetyTest {

    @Test
    fun `has jspecify`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProject(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getContentOf("pom.xml"))
            .contains("<artifactId>jspecify</artifactId>")
    }

    @Test
    fun `has error prone`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProject(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getContentOf("pom.xml"))
            .contains("-Xplugin:ErrorProne")
            .contains("<artifactId>error_prone_core</artifactId>")
    }

    @Test
    fun `has null away`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProject(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getContentLinesOf("pom.xml"))
            .contains("-Xep:NullAway:ERROR")
            .contains("-XepOpt:NullAway:AnnotatedPackages=org.acme.anvil")
            .contains("<artifactId>nullaway</artifactId>")
    }
}