package features.generate.project.version

import factories.Configs
import factories.services.TestServiceProvider
import com.gitlab.mbarberot.kriollo.generator.project.ProjectVersionGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GenerateVersionUtilityClassTest {

    @Test
    fun `generate version utility class`() {
        // Arrange
        val serviceProvider = TestServiceProvider(
            configuration = Configs.basicConfig(),
        )
        val generator = ProjectVersionGenerator(serviceProvider)

        // Act
        generator.execute(serviceProvider)

        // Assert
        val generatedFiles = serviceProvider.fileSystem.getTestGeneratedFiles()
        assertThat(generatedFiles.filenames()).contains("src/main/kotlin/org/acme/anvil/AnvilVersion.kt")
    }

}