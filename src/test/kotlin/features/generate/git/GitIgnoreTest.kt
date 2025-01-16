package features.generate.git

import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.generator.git.GitConfiguration
import com.gitlab.mbarberot.kriollo.generator.java.JavaConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration
import factories.Configs
import factories.Configs.projectConfiguration
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GitIgnoreTest {

    @Test
    fun `create gitignore file`() {
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
        assertThat(fakeFileSystem.getFile(".gitignore")).isNotNull()
    }

    @Test
    fun `ignore dotenv`() {
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
        assertThat(fakeFileSystem.getContentLinesOf(".gitignore"))
            .contains(
                ".env",
            )
    }

    @Test
    fun `ignore java artifacts`() {
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
        assertThat(fakeFileSystem.getContentLinesOf(".gitignore"))
            .contains(
                "*.class",
                "*.jar"
            )
    }

    @Test
    fun `ignore maven output`() {
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
        assertThat(fakeFileSystem.getContentLinesOf(".gitignore"))
            .contains(
                "target/"
            )
    }

    @Test
    fun `ignore custom value from config`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = BasicCodegenConfiguration(
                project = projectConfiguration,
                java = JavaConfiguration(enabled = true),
                maven = MavenConfiguration(enabled = true),
                git = GitConfiguration(
                    enabled = true,
                    ignore = listOf("foo", "bar/", "*.baz"),
                ),
            ),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getContentLinesOf(".gitignore"))
            .contains(
                "foo",
                "bar/",
                "*.baz"
            )

    }
}