package kriollo.generator

interface CodegenExtension<T> {
    fun provide(): List<T>
}