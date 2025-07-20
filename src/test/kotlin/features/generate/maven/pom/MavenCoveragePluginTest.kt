package features.generate.maven.pom

import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.generator.java.JavaConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.TestsConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.coverage.CoverageConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration
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

    @Test
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
                    <counter>INSTRUCTION</counter>
                    <value>COVEREDRATIO</value>
                    <minimum>0.8</minimum>
                </limit>
                """.trimIndent()
            )
    }

    @Test
    fun `has report generation`() {
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
                <execution>
                   <id>coverage-report</id>
                   <phase>prepare-package</phase>
                   <goals>
                       <goal>report</goal>
                   </goals>
                </execution>
                """.trimIndent()
            )
    }

    @Test
    fun `report phase is configurable`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = BasicCodegenConfiguration(
                project = Configs.projectConfiguration,
                java = JavaConfiguration(enabled = true),
                maven = MavenConfiguration(enabled = true),
                tests = TestsConfiguration(
                    enabled = true,
                    coverage = CoverageConfiguration(
                        reportPhase = "verify"
                    )
                )
            ),
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
                <execution>
                   <id>coverage-report</id>
                   <phase>verify</phase>
                   <goals>
                       <goal>report</goal>
                   </goals>
                </execution>
                """.trimIndent()
            )
    }

}