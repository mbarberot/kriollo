package com.gitlab.mbarberot.kriollo.generator.kotlin

import com.gitlab.mbarberot.kriollo.generator.base.DirectoryGenerator
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class KotlinTestDirectoryGenerator(
    val serviceProvider: ServiceProvider
) : DirectoryGenerator() {
    override fun getDirectoryPath(): String {
        return "./src/test/kotlin"
    }
}