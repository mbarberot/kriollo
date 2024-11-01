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
import com.gitlab.mbarberot.kriollo.generator.tests.TestsConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationAdapter
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationProvider
import com.gitlab.mbarberot.kriollo.services.configuration.ConfigurationReader
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.DeprecationHandler
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConfigurationTest {
    @Test
    fun `allow backward compatibility`() {
        // Arrange
        val deprecations: MutableList<String> = mutableListOf()

        // Act
        val config: CodegenConfiguration = ConfigurationProvider(
            BackwardConfigurationReader(),
            BackwardConfigurationAdapter { deprecations.add(it) },
        )

        // Assert
        assertThat(deprecations).contains("project.testProperty is deprecated, please use project.name instead")
        assertThat(config.project.name).isEqualTo("test-property")
    }
}

class BackwardConfigurationReader: ConfigurationReader<TestBackwardCompatibleConfiguration> {
    override fun read(): TestBackwardCompatibleConfiguration {
        return TestBackwardCompatibleConfiguration()
    }
}

class BackwardConfigurationAdapter(
    private val deprecationHandler: DeprecationHandler

    ): ConfigurationAdapter<TestBackwardCompatibleConfiguration> {
    override fun adapt(backwardConfig: TestBackwardCompatibleConfiguration): CodegenConfiguration {
        if (backwardConfig.project.testProperty.isNotEmpty()) {
            deprecationHandler.logDeprecation("project.testProperty is deprecated, please use project.name instead")
            backwardConfig.project.name = backwardConfig.project.testProperty
        }
        return backwardConfig
    }
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
