package features.generate.maven.pom

import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.generator.kotlin.KotlinConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenFilteringConfiguration
import factories.Configs
import factories.services.TestConfigurationService
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FilteringConfigurationTest {

    @Test
    fun `filtering is enabled by default with default exclusion list`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProject(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
            configurationService = TestConfigurationService()
                .withTestConfig(MavenConfiguration(enabled = true))
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getContentOf("pom.xml"))
            .containsIgnoringWhitespaces(
                """
                <resources>
                    <resource>
                        <filtering>true</filtering>
                        <directory>src/main/resources</directory>
                        <excludes>
                            <exclude>**/*.eot</exclude>
                            <exclude>**/*.gif</exclude>
                            <exclude>**/*.jpeg</exclude>
                            <exclude>**/*.jpg</exclude>
                            <exclude>**/*.png</exclude>
                            <exclude>**/*.ttf</exclude>
                            <exclude>**/*.woff</exclude>
                            <exclude>**/*.woff2</exclude>
                        </excludes>
                    </resource>
                </resources>
                """.trimIndent()
            )
    }

    @Test
    fun `filtering can be disabled`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProject(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
            configurationService = TestConfigurationService()
                .withTestConfig(
                    MavenConfiguration(
                        enabled = true,
                        filtering = MavenFilteringConfiguration(enabled = false)
                    )
                )
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getContentOf("pom.xml"))
            .doesNotContain("<filtering>true</filtering>")
    }

    @Test
    fun `additional entries can be added to the blacklist`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProject(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
            configurationService = TestConfigurationService()
                .withTestConfig(
                    MavenConfiguration(
                        enabled = true,
                        filtering = MavenFilteringConfiguration(
                            enabled = true,
                            additionalExclusions = setOf("**/*.jar")
                        )
                    )
                )
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getContentOf("pom.xml"))
            .containsIgnoringWhitespaces(
                """
                <resources>
                    <resource>
                        <filtering>true</filtering>
                        <directory>src/main/resources</directory>
                        <excludes>
                            <exclude>**/*.eot</exclude>
                            <exclude>**/*.gif</exclude>
                            <exclude>**/*.jar</exclude>
                            <exclude>**/*.jpeg</exclude>
                            <exclude>**/*.jpg</exclude>
                            <exclude>**/*.png</exclude>
                            <exclude>**/*.ttf</exclude>
                            <exclude>**/*.woff</exclude>
                            <exclude>**/*.woff2</exclude>
                        </excludes>
                    </resource>
                </resources>
                """.trimIndent()
            )
    }

    @Test
    fun `the blacklist is overridable`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = Configs.javaProject(),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
            configurationService = TestConfigurationService()
                .withTestConfig(
                    MavenConfiguration(
                        enabled = true,
                        filtering = MavenFilteringConfiguration(
                            enabled = true,
                            exclusions = setOf("**/*.jar")
                        )
                    )
                )
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getContentOf("pom.xml"))
            .containsIgnoringWhitespaces(
                """
                <resources>
                    <resource>
                        <filtering>true</filtering>
                        <directory>src/main/resources</directory>
                        <excludes>
                            <exclude>**/*.jar</exclude>
                        </excludes>
                    </resource>
                </resources>
                """.trimIndent()
            )
    }
}