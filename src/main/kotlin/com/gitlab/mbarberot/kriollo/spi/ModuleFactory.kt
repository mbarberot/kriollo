package com.gitlab.mbarberot.kriollo.spi

import com.gitlab.mbarberot.kriollo.generator.CodegenModule
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

fun interface ModuleFactory {
    fun createModule(serviceProvider: ServiceProvider): CodegenModule?
}