package features.configuration

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.configuration.KriolloConfiguration
import com.gitlab.mbarberot.kriollo.generator.cli.CliConfiguration
import com.gitlab.mbarberot.kriollo.generator.components.ComponentsConfiguration
import com.gitlab.mbarberot.kriollo.generator.git.GitConfiguration
import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.java.JavaConfiguration
import com.gitlab.mbarberot.kriollo.generator.jetbrains.JetbrainsConfiguration
import com.gitlab.mbarberot.kriollo.generator.kotlin.KotlinConfiguration
import com.gitlab.mbarberot.kriollo.generator.libs.JavaLibConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.nix.NixConfiguration
import com.gitlab.mbarberot.kriollo.generator.project.IProjectConfiguration
import com.gitlab.mbarberot.kriollo.generator.scripts.ScriptsConfiguration
import com.gitlab.mbarberot.kriollo.generator.templating.TemplatingConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.TestsConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationAdapter
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationProvider
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConfigurationTest {
    @Test
    fun `allow backward compatibility`() {
        // Arrange
        val deprecations: MutableList<String> = mutableListOf()

        // Act
        val config: CodegenConfiguration = ConfigurationProvider(
            ConfigurationReader { return@ConfigurationReader TestBackwardCompatibleConfiguration() },
            ConfigurationAdapter { return@ConfigurationAdapter adapt(it, deprecations) },
        )

        // Assert
        assertThat(deprecations).contains("project.testProperty is deprecated, please use project.name instead")
        assertThat(config.project.name).isEqualTo("test-property")
    }
}

/**
 * A simple adapter example featuring :
 * - moving the legacy property "testProperty" to the expected location for CodegenConfiguration
 * - handling a deprecation (here in a list for assertions in test, but may be printed or logged in production code)
 *
 * As the lenient configuration is child of the CodegenConfiguration, this object may be returned as is.
 * The rest of the code will never know what is the actual implementation
 */
fun adapt(config: TestBackwardCompatibleConfiguration, deprecations: MutableList<String>): CodegenConfiguration {
    if (config.project.testProperty.isNotEmpty()) {
        deprecations.add("project.testProperty is deprecated, please use project.name instead")
        config.project.name = config.project.testProperty
    }

    return config
}

/**
 * A lenient configuration class allowing more than the CodegenConfiguration contract
 *
 * Allows deserializing a configuration file that did not migrate to the latest configuration
 */
data class TestBackwardCompatibleConfiguration(
    override val kriollo: KriolloConfiguration = KriolloConfiguration(),
    override val project: BackwardCompatibleProjectConfiguration = BackwardCompatibleProjectConfiguration(),
    override val cli: CliConfiguration = CliConfiguration(),
    override val jetbrains: JetbrainsConfiguration = JetbrainsConfiguration(),
    override val java: JavaConfiguration = JavaConfiguration(),
    override val kotlin: KotlinConfiguration = KotlinConfiguration(),
    override val nix: NixConfiguration = NixConfiguration(),
    override val git: GitConfiguration = GitConfiguration(),
    override val scripts: ScriptsConfiguration = ScriptsConfiguration(),
    override val maven: MavenConfiguration = MavenConfiguration(),
    override val templating: TemplatingConfiguration = TemplatingConfiguration(),
    override val tests: TestsConfiguration = TestsConfiguration(),
    override val components: ComponentsConfiguration = ComponentsConfiguration(),
) : CodegenConfiguration

/**
 * A configuration part featuring a non-CodegenConfiguration field "testProperty" for testing purpose
 *
 * It's better to fork the whole data class to customize it (val => var)
 */
data class BackwardCompatibleProjectConfiguration(
    override val groupId: String = "",
    override var name: String = "",
    override val version: String = "",
    override val mainClass: String = "",
    override val encoding: String = "UTF-8",
    override val dependencies: List<JavaArtifact> = listOf(),
    override val libs: JavaLibConfiguration = JavaLibConfiguration(),
    val testProperty: String = "test-property",
) : IProjectConfiguration
