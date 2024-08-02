package factories.services

class GeneratedTestFiles {

    private val testGeneratedFiles: MutableList<TestFile> = mutableListOf()

    fun addFile(file: TestFile) {
        testGeneratedFiles.add(file)
    }

    fun filenames(): List<String> {
        return testGeneratedFiles.map { it.filename }
    }

    fun getFile(filename: String): TestFile? {
        return testGeneratedFiles.find { it.filename == filename }
    }

    fun getContentOf(filename: String): String {
        return getFile(filename)?.content ?: ""
    }

}