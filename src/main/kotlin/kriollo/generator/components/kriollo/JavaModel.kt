package kriollo.generator.components.kriollo

import kriollo.generator.utils.BaseModel

open class JavaModel(
    val namespace: String,
    val name: String,
    private val imports: List<String>,
) : BaseModel() {
    fun imports(): List<String> = imports.sorted()
}