package kriollo.generator

abstract class CodegenModule {
    abstract fun isActivated(): Boolean
    abstract fun provideGenerators(): List<Generator>
    open fun provideExtensions(generator: Generator) {
        // Left empty for child classes to implement if needed
    }
}