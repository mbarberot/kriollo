package features.generate.maven.pom

import com.gitlab.mbarberot.kriollo.features.Generate
import factories.Configs
import factories.services.TestConfigurationService
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MavenKotlinPluginTest {

    @Test
    fun `has maven kotlin plugin`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.kotlinProject(),
            configurationService = TestConfigurationService.kotlinMavenProject(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getContentOf("pom.xml"))
            .containsIgnoringWhitespaces(
                """
                <plugin>
                    <groupId>org.jetbrains.kotlin</groupId>
                    <artifactId>kotlin-maven-plugin</artifactId>
            """.trimIndent()
            )

        println(fakeFileSystem.getContentOf("pom.xml"))
    }

}