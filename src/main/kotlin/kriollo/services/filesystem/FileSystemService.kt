package kriollo.services.filesystem

interface FileSystemService {
    fun createFile(filename: String, content: String = "", isScript: Boolean = false)
    fun createDirectory(path: String)
    fun generatedFiles(): List<GeneratedFile>
}