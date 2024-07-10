package kriollo.generator.git

import kriollo.generator.utils.BaseModel

data class GitIgnoreModel(
    val blocks: List<GitIgnoreRules> = listOf()
): BaseModel()
