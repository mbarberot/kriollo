package com.gitlab.mbarberot.kriollo.generator

interface CodegenExtension<T> {
    fun provide(): List<T>
    fun priority(): Int = 0
}