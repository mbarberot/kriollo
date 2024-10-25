package features.init

import factories.services.TestServiceProvider
import com.gitlab.mbarberot.kriollo.features.Init
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InitBasicConfigurationFileTest {

    @Test
    fun `generate a default configuration file`() {
        // Arrange
        val serviceProvider = TestServiceProvider()
        val useCase = Init(serviceProvider)

        // Act
        useCase.init()

        // Assert
        val generatedFiles = serviceProvider.fileSystem.getTestGeneratedFiles()
        assertThat(generatedFiles.filenames()).contains("codegen/codegen.kdl")
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
        assertThat(generatedFiles.getContentOf("codegen/codegen.kdl")).contains(
            """
                project {
                    groupId ""
                    name ""
                    version ""
                }
            """.trimIndent()
        )
    }
}

