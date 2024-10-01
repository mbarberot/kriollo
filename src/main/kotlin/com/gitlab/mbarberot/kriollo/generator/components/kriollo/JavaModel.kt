package com.gitlab.mbarberot.kriollo.generator.components.kriollo

import com.gitlab.mbarberot.kriollo.generator.utils.BaseModel

open class JavaModel(
    val namespace: String,
    val name: String,
    private val imports: List<String>,
) : BaseModel() {
    fun imports(): List<String> = imports.sorted()
}