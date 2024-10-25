package factories.services

import com.gitlab.mbarberot.kriollo.services.filesystem.BaseFileSystemService
import factories.services.fs.FakeFileSystem
import java.io.IOException

class TestFileSystemService(
    private val fakeFileSystem: FakeFileSystem = FakeFileSystem()
) : BaseFileSystemService() {


    override fun doCreateFile(filename: String, content: String, isScript: Boolean) {
        fakeFileSystem.addFile(TestFile(filename, content))
    }

    override fun doCreateDirectory(path: String) {
        TODO("Not yet implemented")
    }

    override fun getFileContent(path: String): List<String> {
        return fakeFileSystem.getFile(path)?.content?.split("\n") ?: throw IOException("File $path not found")
    }

    override fun removeFile(path: String) {
        fakeFileSystem.removeFile(path);
    }

    @Deprecated(message = "Provide a fakeFileSystem in constructor and make assertion directly on it")
    fun getTestGeneratedFiles(): FakeFileSystem {
        return fakeFileSystem
    }
}

