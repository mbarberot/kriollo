package com.gitlab.mbarberot.kriollo.services.configuration

import kotlin.reflect.KClass

interface ConfigurationService {
    fun <T : Any> get(klass: KClass<T>): T
    fun check()
}
