package unit.configuration

import com.gitlab.mbarberot.kriollo.spi.configuration.ConfigurationCustomizer
import com.gitlab.mbarberot.kriollo.services.configuration.DefaultConfigurationService
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.DeprecationHandler
import com.gitlab.mbarberot.kriollo.spi.configuration.RootNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass

class ConfigurationServiceTest {

    val kdlExample = """
        project { 
            name "foo"
        }
        
        script { 
            enabled "true"
            build { 
                scriptName "bar"
            }
        }
        
        dependencies { 
            jackson "true"
        }
    """.trimIndent()

    data class ProjectConfig(val name: String)
    data class ScriptConfig(val enabled: Boolean, val build: ScriptBuildConfig)
    data class ScriptBuildConfig(val scriptName: String)
    data class DependenciesConfig(val json: Boolean, val version: String)

    @Test
    fun `get config by class`() {
        // Arrange
        val configuration = DefaultConfigurationService(kdlExample)

        // Act
        val projectConfig: ProjectConfig = configuration.get(ProjectConfig::class)

        // Assert
        assertThat(projectConfig).isEqualTo(ProjectConfig("foo"))
    }

    @Test
    fun `get config by class for script`() {
        // Arrange
        val configuration = DefaultConfigurationService(kdlExample)

        // Act
        val projectConfig: ScriptConfig = configuration.get(ScriptConfig::class)

        // Assert
        assertThat(projectConfig).isEqualTo(ScriptConfig(true, ScriptBuildConfig(scriptName = "bar")))
    }

    @Test
    fun `get config by class for jackson`() {
        // Arrange
        val backwardCompatibilityDependencyConfigCustomizer: ConfigurationCustomizer = object :
            ConfigurationCustomizer {
            override val priority: Int = 1
            override fun customize(configuration: RootNode) {
                val dependenciesConfig = configuration.get("$.dependencies")
                if(dependenciesConfig?.get("jackson")?.value() == "true") {
                    dependenciesConfig.remove("jackson")
                    dependenciesConfig.setValue("json", "true")
                }
            }

            override fun check(
                configuration: RootNode,
                deprecationHandler: DeprecationHandler,
            ) {
                // nothing to do here
            }

            override fun <T : Any> accepts(kclass: KClass<T>): Boolean {
                return kclass == DependenciesConfig::class
            }
        }
        val defaultVersionDependenciesConfigCustomizer: ConfigurationCustomizer = object : ConfigurationCustomizer {
            override val priority: Int = 2
            override fun customize(configuration: RootNode) {
                if(configuration.get("$.dependencies.version") == null) {
                    configuration.get("$.dependencies")?.setValue("version", "1.3.4")
                }
            }

            override fun check(
                configuration: RootNode,
                deprecationHandler: DeprecationHandler,
            ) {
                // nothing to do here
            }

            override fun <T : Any> accepts(kclass: KClass<T>): Boolean {
                return kclass == DependenciesConfig::class
            }
        }
        val configuration = DefaultConfigurationService(
            kdlExample,
            listOf(backwardCompatibilityDependencyConfigCustomizer, defaultVersionDependenciesConfigCustomizer),
        )

        // Act
        val config = configuration.get(DependenciesConfig::class)

        // Assert
        assertThat(config).isEqualTo(DependenciesConfig(true, "1.3.4"))
    }
}