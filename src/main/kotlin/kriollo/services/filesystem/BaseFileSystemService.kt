package kriollo.services.filesystem

abstract class BaseFileSystemService : FileSystemService {

    private val generatedFiles: MutableList<GeneratedFile> = mutableListOf()

    abstract fun doCreateFile(filename: String, content: String, isScript: Boolean)

    override fun createFile(filename: String, content: String, isScript: Boolean) {
        doCreateFile(filename, content, isScript)
        generatedFiles.add(GeneratedFile(filename, content, isScript))
    }

    abstract fun doCreateDirectory(path: String)

    override fun createDirectory(path: String) {
        doCreateDirectory(path)
    }

    override fun generatedFiles(): List<GeneratedFile> {
        return generatedFiles
    }

}