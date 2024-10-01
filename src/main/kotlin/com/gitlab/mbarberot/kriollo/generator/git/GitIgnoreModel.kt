package com.gitlab.mbarberot.kriollo.generator.git

import com.gitlab.mbarberot.kriollo.generator.utils.BaseModel

data class GitIgnoreModel(
    val blocks: List<GitIgnoreRules> = listOf()
): BaseModel()
