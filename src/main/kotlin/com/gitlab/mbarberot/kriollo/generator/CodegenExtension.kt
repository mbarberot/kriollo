package com.gitlab.mbarberot.kriollo.generator

interface CodegenExtension<T> {
    fun provide(): List<T>
}