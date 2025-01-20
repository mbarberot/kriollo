package features.generate.maven.pom

import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.java.JavaConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.project.ProjectConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.TestsConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.junit.JUnitConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DependencyOverrideTest {

    @Test
    fun `manually adding a dependency override kriollo generators`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = projectWithOverriddenJunitDependency(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        val pom = fakeFileSystem.getContentOf("pom.xml")
        assertThat(pom)
            .containsIgnoringWhitespaces(
                """
                <dependency>
                    <groupId>org.junit.platform</groupId>
                    <artifactId>junit-platform-commons</artifactId>
                </dependency>
            """.trimIndent()
            )
            .containsOnlyOnce(
               "<artifactId>junit-platform-commons</artifactId>"
            )
    }

    private fun projectWithOverriddenJunitDependency() = BasicCodegenConfiguration(
        project = ProjectConfiguration(
            groupId = "org.acme",
            name = "anvil",
            version = "2.5.9",
            dependencies = listOf(
                JavaArtifact(
                    groupId = "org.junit.platform",
                    artifactId = "junit-platform-commons",
                )
            )
        ),
        java = JavaConfiguration(enabled = true),
        maven = MavenConfiguration(enabled = true),
        tests = TestsConfiguration(
            enabled = true,
            junit = JUnitConfiguration(
                enabled = true
            )
        )
    )
}