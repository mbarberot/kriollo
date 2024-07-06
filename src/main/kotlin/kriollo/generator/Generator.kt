package kriollo.generator

import kriollo.configuration.CodegenConfiguration

interface Generator {
    fun isActivated(configuration: CodegenConfiguration): Boolean
    fun execute(configuration: CodegenConfiguration)
}