package com.gitlab.mbarberot.kriollo.services.configuration

fun interface ConfigurationReader<T> {
    fun read(): T
}