package com.gitlab.mbarberot.kriollo.spi.configuration

import com.gitlab.mbarberot.kriollo.services.configuration.legacy.DeprecationHandler
import kotlin.reflect.KClass

interface ConfigurationCustomizer {
    val priority: Int
    fun customize(configuration: RootNode)
    fun check(configuration: RootNode, deprecationHandler: DeprecationHandler)
    fun <T : Any> accepts(kclass: KClass<T>): Boolean
}