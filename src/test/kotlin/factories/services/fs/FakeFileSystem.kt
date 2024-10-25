package factories.services.fs

import factories.services.TestFile

class FakeFileSystem(
    private vararg val existingFiles: TestFile
) {

    private val addedFiles: MutableList<TestFile> = mutableListOf()
    private val removedFiles: MutableList<String> = mutableListOf()

    fun addFile(file: TestFile) {
        addedFiles.add(file)
    }

    fun filenames(): List<String> {
        return addedFiles.map { it.filename }
    }

    fun getFile(filename: String): TestFile? {
        return buildList {
            addAll(existingFiles)
            addAll(addedFiles)
        }.find { it.filename == filename }
    }

    fun getContentOf(filename: String): String {
        return getFile(filename)?.content ?: ""
    }

    fun removeFile(path: String) {
        removedFiles.add(path)
    }

    fun removedFilenames(): List<String> = removedFiles
}