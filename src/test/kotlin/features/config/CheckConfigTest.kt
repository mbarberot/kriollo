
import com.gitlab.mbarberot.kriollo.features.Config
import com.gitlab.mbarberot.kriollo.services.configuration.kdl.KdlConfigurationReader
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyCodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyConfigurationAdapter
import features.configuration.BackwardConfigurationAdapter
import features.configuration.BackwardConfigurationReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CheckConfigTest {

    @Test
    fun `the project configuration file should not have deprecations`() {
        // Arrange
        val deprecations = mutableListOf<String>()
        val config = Config(
            KdlConfigurationReader("./codegen/codegen.kdl", LegacyCodegenConfiguration::class.java),
            LegacyConfigurationAdapter { deprecations.add(it) }
        )

        // Act
        config.check()

        // Assert
        assertThat(deprecations).isEmpty()
    }

    @Test
    fun `the legacy configuration should have deprecations`() {
        // Arrange
        val deprecations = mutableListOf<String>()
        val config = Config(
            BackwardConfigurationReader(),
            BackwardConfigurationAdapter { deprecations.add(it) },
        )

        // Act
        config.check()

        // Assert
        assertThat(deprecations).isNotEmpty()
    }

}

