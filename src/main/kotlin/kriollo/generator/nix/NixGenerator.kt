package kriollo.generator.nix

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.Generator
import kriollo.generator.utils.initFile

class NixGenerator: Generator {

    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.nix.enabled
    }

    override fun execute(configuration: CodegenConfiguration) {
        val nixTemplate = """
        # /!\ Warning
        # This file is generated by Kriollo, do not edit it manually
        with (import <nixpkgs> {});
        mkShell {
            buildInputs = [
                jdk21
                kotlin
                maven
            ];
            shellHook = ''
              export JAVA_HOME=${'$'}{jdk21.home}
            '';
        }
    """.trimIndent()

        initFile("default.nix", nixTemplate)
    }
}