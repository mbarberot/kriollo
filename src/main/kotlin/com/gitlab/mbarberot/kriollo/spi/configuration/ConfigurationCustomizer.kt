package com.gitlab.mbarberot.kriollo.spi.configuration

import kotlin.reflect.KClass

interface ConfigurationCustomizer {
    val priority: Int
    fun customize(configuration: RootNode)
    fun <T : Any> accepts(kclass: KClass<T>): Boolean
}