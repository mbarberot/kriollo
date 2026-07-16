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
        
        libs { 
            jackson "true"
        }
        
        dependencies { 
            - { 
                name "logger"
                version "1.0.0"
            }
            - { 
                name "webserver"
                version "2.4.3"
            }
        }
        
        test {
            unit {
                tools { 
                    - "junit"
                    - "assertj"
                    - "mockito"
                 }
             }
         }
    """.trimIndent()

    data class ProjectConfig(val name: String)
    data class ScriptConfig(val enabled: Boolean, val build: ScriptBuildConfig)
    data class ScriptBuildConfig(val scriptName: String)
    data class LibsConfig(val json: Boolean, val version: String)
    data class Dependency(val name: String, val version: String)
    data class DependenciesConfig(val dependencies: List<Dependency>)
    data class TestConfig(val unit: UnitTestConfig)
    data class UnitTestConfig(val tools: List<String>)

    @Test
    fun `get key value object config`() {
        // Arrange
        val configuration = DefaultConfigurationService(kdlExample)

        // Act
        val projectConfig: ProjectConfig = configuration.get(ProjectConfig::class)

        // Assert
        assertThat(projectConfig).isEqualTo(ProjectConfig("foo"))
    }

    @Test
    fun `get nested object config`() {
        // Arrange
        val configuration = DefaultConfigurationService(kdlExample)

        // Act
        val projectConfig: ScriptConfig = configuration.get(ScriptConfig::class)

        // Assert
        assertThat(projectConfig).isEqualTo(ScriptConfig(true, ScriptBuildConfig(scriptName = "bar")))
    }

    @Test
    fun `get array config`() {
        // Arrange
        val configuration = DefaultConfigurationService(kdlExample)

        // Act
        val config = configuration.get(DependenciesConfig::class)

        // Assert
        assertThat(config).isEqualTo(DependenciesConfig(listOf(
            Dependency("logger", "1.0.0"),
            Dependency("webserver", "2.4.3")
        )))
    }


    @Test
    fun `get nested array config`() {
        // Arrange
        val configuration = DefaultConfigurationService(kdlExample)

        // Act
        val config = configuration.get(TestConfig::class)

        // Assert
        assertThat(config).isEqualTo(
            TestConfig(UnitTestConfig(listOf("junit", "assertj", "mockito")))
        )
    }

    @Test
    fun `get backward compatible config`() {
        // Arrange
        val backwardCompatibilityDependencyConfigCustomizer: ConfigurationCustomizer = object :
            ConfigurationCustomizer {
            override val priority: Int = 1
            override fun customize(configuration: RootNode) {
                val dependenciesConfig = configuration.get("$.libs")
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
                return kclass == LibsConfig::class
            }
        }
        val defaultVersionDependenciesConfigCustomizer: ConfigurationCustomizer = object : ConfigurationCustomizer {
            override val priority: Int = 2
            override fun customize(configuration: RootNode) {
                if(configuration.get("$.libs.version") == null) {
                    configuration.get("$.libs")?.setValue("version", "1.3.4")
                }
            }

            override fun check(
                configuration: RootNode,
                deprecationHandler: DeprecationHandler,
            ) {
                // nothing to do here
            }

            override fun <T : Any> accepts(kclass: KClass<T>): Boolean {
                return kclass == LibsConfig::class
            }
        }
        val configuration = DefaultConfigurationService(
            kdlExample,
            listOf(backwardCompatibilityDependencyConfigCustomizer, defaultVersionDependenciesConfigCustomizer),
        )

        // Act
        val config = configuration.get(LibsConfig::class)

        // Assert
        assertThat(config).isEqualTo(LibsConfig(true, "1.3.4"))
    }
}