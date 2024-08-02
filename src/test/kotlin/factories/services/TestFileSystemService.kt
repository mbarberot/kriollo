package factories.services

import kriollo.services.filesystem.BaseFileSystemService

class TestFileSystemService : BaseFileSystemService() {

    private val generatedTestFiles = GeneratedTestFiles()

    override fun doCreateFile(filename: String, content: String, isScript: Boolean) {
        generatedTestFiles.addFile(TestFile(filename, content))
    }

    override fun doCreateDirectory(path: String) {
        TODO("Not yet implemented")
    }

    fun getTestGeneratedFiles(): GeneratedTestFiles {
        return generatedTestFiles
    }
}

