package features.clean

import com.gitlab.mbarberot.kriollo.features.Clean
import factories.Configs
import factories.services.TestFile
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CleanTest {

    @Test
    fun `must remove files listed in generated file index`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem(
            TestFile(
                "codegen/generated-files.txt",
                """
                src/main/kotlin/org/acme/anvil/AnvilVersion.kt
                """.trimIndent()
            )
        )
        val testServiceProvider = TestServiceProvider(
            configuration = Configs.basicConfig(),
            fileSystem = TestFileSystemService(fakeFileSystem)
        )
        val clean = Clean(testServiceProvider)

        // Act
        clean.clean()

        // Assert
        assertThat(fakeFileSystem.removedFilenames())
            .containsExactly("src/main/kotlin/org/acme/anvil/AnvilVersion.kt")
    }
}

