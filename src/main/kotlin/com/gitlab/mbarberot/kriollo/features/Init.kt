package com.gitlab.mbarberot.kriollo.features

import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class Init(val serviceProvider: ServiceProvider) {

    fun init() {
        serviceProvider.fileSystem.createFile(
            filename = "codegen/codegen.kdl",
            content = generateBasicConfiguration()
        )
    }

    private fun generateBasicConfiguration(): String {
        return """
            project {
                groupId ""
                name ""
                version ""
            }
            
            java { 
                enabled true
            }
            
            kotlin {
                enabled true
            }
            
            maven {
                enabled true
            }
            
            tests {
                enabled true
                junit {
                    enabled true
                }
            }
          
            jetbrains {
                enabled true
            }
            
            nix {
                enabled true
            }
            
            git {
                enabled true
            }
            
            """.trimIndent()
    }

}