package features.generate.maven.pom

import com.gitlab.mbarberot.kriollo.features.Generate
import factories.Configs
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MavenCoveragePluginTest {

    @Test
    fun `has jacoco`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProjectWithTest(),
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
                    <groupId>org.jacoco</groupId>
                    <artifactId>jacoco-maven-plugin</artifactId>
            """.trimIndent()
            )
    }

    fun `has coverage thresholds`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProjectWithTest(),
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
                <limit>
                    <counter>BRANCH</counter>
                    <value>COVEREDRATIO</value>
                    <minimum>0.5</minimum>
                </limit>
            """.trimIndent()
            )
            .containsIgnoringWhitespaces(
                """
                <limit>
                    <counter>CLASS</counter>
                    <value>MISSEDCOUNT</value>
                    <maximum>0</maximum>
                </limit>
                """.trimIndent()
            )
    }
}