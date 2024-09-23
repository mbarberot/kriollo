package kriollo.features.init

import kriollo.services.provider.ServiceProvider

class Init(val serviceProvider: ServiceProvider) {

    fun init() {
        serviceProvider.fileSystem.createFile(
            filename = "codegen/codegen.toml",
            content = generateBasicConfiguration()
        )
    }

    private fun generateBasicConfiguration(): String {
        return """
          #
          # Project
          #
          
          [project]
          groupId = ""
          name = ""
          version = ""
          
          [java]
          enabled = true
          
          [kotlin]
          enabled = true
          
          [maven]
          enabled = true
          
          [tests]
          enabled = true
          junit.enabled = true
          
          #
          # Dotfiles
          #
          [jetbrains]
          enabled = true
          
          [nix]
          enabled = true
          
          [git]
          enabled = true
          
           """.trimIndent()
    }

}