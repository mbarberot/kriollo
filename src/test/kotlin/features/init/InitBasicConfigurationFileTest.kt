package features.init

import factories.services.TestServiceProvider
import com.gitlab.mbarberot.kriollo.features.Init
import factories.services.TestFileSystemService
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InitBasicConfigurationFileTest {

    @Test
    fun `generate a default configuration file`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            fileSystem = TestFileSystemService(fakeFileSystem),
        )
        val useCase = Init(serviceProvider)

        // Act
        useCase.init()

        // Assert
        assertThat(fakeFileSystem.filenames()).contains("codegen/codegen.kdl")
    }

    @Test
    fun `default configuration file has basic configuration`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            fileSystem = TestFileSystemService(fakeFileSystem),
        )
        val useCase = Init(serviceProvider)

        // Act
        useCase.init()

        // Assert
        assertThat(fakeFileSystem.getContentOf("codegen/codegen.kdl")).contains(
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

