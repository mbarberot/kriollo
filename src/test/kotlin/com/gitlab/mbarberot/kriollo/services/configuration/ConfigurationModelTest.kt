package com.gitlab.mbarberot.kriollo.services.configuration

import com.gitlab.mbarberot.kriollo.configuration.kdl.fromKdl
import com.gitlab.mbarberot.kriollo.spi.configuration.Node
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ConfigurationModelTest {

    private lateinit var model: Node

    val kdlExample = """
        project { 
            name "foo"
        }
        
        script { 
            enabled true
            build { 
                scriptName "bar"
            }
        }
        
        tags { 
            - "java"
            - "kotlin"
        }
        
        dependencies { 
            - { 
                name "jackson" 
                repo "maven central"
            }
            - { 
                name "picocli" 
            }
        }
    """.trimIndent()

    @BeforeEach
    fun setUp() {
        model = fromKdl(kdlExample)
    }

    @Test
    fun getFirstLevelKey() {
        // Assert
        assertThat(model.get("$.project")).isNotNull()
    }

    @Test
    fun getValueInFirstLevelKey() {
        // Assert
        assertThat(model.get("$.project.name")?.value()).isEqualTo("foo")
    }

    @Test
    fun getValueInSimpleArray() {
        // Assert
        assertThat(model.get("$.tags.[0]")?.value()).isEqualTo("java")
    }

    @Test
    fun getValueInComplexArray() {
        // Assert
        assertThat(model.get("$.dependencies.[1].name")?.value()).isEqualTo("picocli")
    }

    @Test
    fun setValueInFirstLevelKey() {
        // Act
        model.get("$.project.name")?.setValue("acme")

        // Assert
        assertThat(model.get("$.project.name")?.value()).isEqualTo("acme")
    }

    @Test
    fun setValueInSimpleArray() {
        // Act
        model.get("$.tags.[0]")?.setValue("acme")

        // Assert
        assertThat(model.get("$.tags.[0]")?.value()).isEqualTo("acme")
    }

    @Test
    fun setValueInComplexArray() {
        // Act
        model.get("$.dependencies.[1].name")?.setValue("acme")

        // Assert
        assertThat(model.get("$.dependencies.[1].name")?.value()).isEqualTo("acme")
    }

    @Test
    fun addValueInFirstLevelKey() {
        // Act
        model.get("$.project")?.setValue("version", "acme")

        // Assert
        assertThat(model.get("$.project.version")?.value()).isEqualTo("acme")
    }

    @Test
    fun addValueInComplexArray() {
        // Act
        model.get("$.dependencies.[1]")?.setValue("version", "acme")

        // Assert
        assertThat(model.get("$.dependencies.[1].version")?.value()).isEqualTo("acme")
    }

    @Test
    fun removeFromFirstLevelKey() {
        // Act
        model.get("$.project")?.remove("name")

        // Assert
        assertThat(model.get("$.project.name")).isNull()
    }

    @Test
    fun removeFromComplexArray() {
        // Act
        model.get("$.dependencies.[0]")?.remove("repo")

        // Assert
        assertThat(model.get("$.dependencies.[0].repo")).isNull()
    }
}