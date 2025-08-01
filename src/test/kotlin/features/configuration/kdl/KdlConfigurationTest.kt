package features.configuration.kdl

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.generator.libs.JacksonConfiguration
import com.gitlab.mbarberot.kriollo.generator.libs.JavaLibConfiguration
import com.gitlab.mbarberot.kriollo.generator.project.ProjectConfiguration
import com.gitlab.mbarberot.kriollo.generator.scripts.ScriptsConfiguration
import com.gitlab.mbarberot.kriollo.generator.scripts.build.BuildScriptConfiguration
import com.gitlab.mbarberot.kriollo.generator.scripts.build.BuildScriptCustomStepConfiguration
import com.gitlab.mbarberot.kriollo.generator.scripts.test.TestScriptConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.kdl.KdlConfigurationReader
import com.gitlab.mbarberot.kriollo.services.configuration.kdl.mapToClass
import com.gitlab.mbarberot.kriollo.services.configuration.legacy.LegacyCodegenConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class KdlConfigurationTest {

    @Test
    fun `read kdl configuration file`() {
        // Arrange
        val configurationReader = KdlConfigurationReader(
            "./codegen/codegen.kdl",
            LegacyCodegenConfiguration::class.java
        )

        // Act
        val configuration: CodegenConfiguration = configurationReader.read()

        // Assert
        assertThat(configuration).isNotNull()
    }

    @Test
    fun `read kdl`() {
        // Arrange
        val kdl = """
            project {
                groupId "acme"
                name "tnt"
                version "1.0.0"
            }
        """.trimIndent()

        // Act
        val configuration = mapToClass(kdl, BasicCodegenConfiguration::class.java)

        // Assert
        assertThat(configuration).isEqualTo(
            BasicCodegenConfiguration(
                project = ProjectConfiguration("acme", "tnt", "1.0.0")
            )
        )
    }

    @Test
    fun `read complex kdl`() {
        // Arrange
        val kdl = """
            project {
                groupId "acme"
                name "tnt"
                version "1.0.0"
                libs {
                    jackson {
                        enabled true
                        core {
                            - "databind"
                        }
                    }
                }
            }
            
            scripts {
                enabled true
                build {
                    enabled true
                    customSteps {
                        - { 
                            name "Copy jar into codegen dir"
                            command "cp target/kriollo.jar codegen/" 
                        }
                    }
                }
                tests {
                    enabled true
                }
            }
        """.trimIndent()

        // Act
        val configuration = mapToClass(kdl, LegacyCodegenConfiguration::class.java)

        // Assert
        val expectedConfiguration = LegacyCodegenConfiguration(
            project = ProjectConfiguration(
                groupId = "acme",
                name = "tnt",
                version = "1.0.0",
                libs = JavaLibConfiguration(
                    jackson = JacksonConfiguration(
                        enabled = true,
                        core = listOf("databind"),
                    )
                )
            ),
            scripts = ScriptsConfiguration(
                enabled = true,
                build = BuildScriptConfiguration(
                    enabled = true,
                    customSteps = listOf(
                        BuildScriptCustomStepConfiguration(
                            name = "Copy jar into codegen dir",
                            command = "cp target/kriollo.jar codegen/",
                        )
                    )
                ),
                tests = TestScriptConfiguration(
                    enabled = true
                )
            ),
        )

        assertThat(configuration).isEqualTo(expectedConfiguration)
    }
}

