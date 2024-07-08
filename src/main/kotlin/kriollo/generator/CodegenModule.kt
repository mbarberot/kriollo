package kriollo.generator

import kriollo.configuration.CodegenConfiguration

abstract class CodegenModule(val configuration: CodegenConfiguration) {
    abstract fun isActivated(): Boolean
    abstract fun provideGenerators(): List<Generator>
    open fun provideExtensions(generator: Generator) {
        // Left empty for child classes to implement if needed
    }
}