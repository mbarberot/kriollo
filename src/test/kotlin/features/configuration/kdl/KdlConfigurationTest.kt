package features.configuration.kdl

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.libs.JavaLibConfiguration
import kriollo.generator.libs.jackson.JacksonConfiguration
import kriollo.generator.project.ProjectConfiguration
import kriollo.generator.scripts.ScriptsConfiguration
import kriollo.generator.scripts.build.BuildScriptConfiguration
import kriollo.generator.scripts.build.BuildScriptCustomStepConfiguration
import kriollo.generator.scripts.test.TestScriptConfiguration
import kriollo.services.configuration.BasicCodegenConfiguration
import kriollo.services.configuration.KdlCodegenConfiguration
import kriollo.services.configuration.mapToClass
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class KdlConfigurationTest {
    @Test
    fun `read kdl configuration file`() {
        // Arrange

        // Act
        val configuration: CodegenConfiguration = KdlCodegenConfiguration("./codegen/codegen.kdl")

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
            
            libs {
                jackson {
                    dataformats {
                        - "toml"
                    }
                }
            }
        """.trimIndent()

        // Act
        val configuration = mapToClass(kdl, BasicCodegenConfiguration::class.java)

        // Assert
        assertThat(configuration).isEqualTo(
            BasicCodegenConfiguration(
                project = ProjectConfiguration("acme", "tnt", "1.0.0"),
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
                libs = JavaLibConfiguration(
                    jackson = JacksonConfiguration(
                        dataformats = listOf("toml")
                    )
                )
            )
        )
    }
}

