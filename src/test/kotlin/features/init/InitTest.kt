package features.init

import factories.services.TestServiceProvider
import kriollo.features.init.Init
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InitTest {

    @Test
    fun `generate a default configuration file`() {
        // Arrange
        val serviceProvider = TestServiceProvider()
        val useCase = Init(serviceProvider)

        // Act
        useCase.init()

        // Assert
        val generatedFiles = serviceProvider.fileSystem.getTestGeneratedFiles()
        assertThat(generatedFiles.filenames()).contains("codegen/codegen.toml")
    }

    @Test
    fun `default configuration file has basic configuration`() {
        // Arrange
        val serviceProvider = TestServiceProvider()
        val useCase = Init(serviceProvider)

        // Act
        useCase.init()

        // Assert
        val generatedFiles = serviceProvider.fileSystem.getTestGeneratedFiles()
        assertThat(generatedFiles.getContentOf("codegen/codegen.toml")).contains(
            """
                [project]
                groupId = ""
                name = ""
                version = ""
            """.trimIndent()
        )
    }
}

