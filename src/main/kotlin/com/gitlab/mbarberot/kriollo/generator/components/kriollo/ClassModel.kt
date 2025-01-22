package com.gitlab.mbarberot.kriollo.generator.components.kriollo

import com.gitlab.mbarberot.kriollo.generator.utils.BaseModel

open class ClassModel(
    val namespace: String,
    val name: String,
    private val rootPackage: String,
    private val imports: List<String>,
) : BaseModel() {
    fun imports(): List<String> = imports.sorted()

    fun generatedAnnotationImport() = "import ${rootPackage}.kriollo.Generated"
    fun generatedAnnotation() = "@Generated(\"$generatedBy\")"
}