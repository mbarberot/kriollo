package features.index

import factories.Configs
import factories.services.TestServiceProvider
import kriollo.features.Generate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreateIndexTest {

    @Test
    fun `kriollo generates an index file`() {
        // Arrange
        val serviceProvider = TestServiceProvider(
            configuration = Configs.basicConfig()
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        val generatedFiles = serviceProvider.fileSystem.getTestGeneratedFiles()
        assertThat(generatedFiles.filenames()).contains("codegen/generated-files.txt")
    }

    @Test
    fun `kriollo's generated file lists any generated file`() {
        // Arrange
        val serviceProvider = TestServiceProvider(
            configuration = Configs.basicConfig()
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        val generatedFiles = serviceProvider.fileSystem.getTestGeneratedFiles()
        assertThat(generatedFiles.getContentOf("codegen/generated-files.txt")).isEqualTo(
            """
                src/main/kotlin/acme/AcmeVersion.kt
            """.trimIndent()
        )
    }
}