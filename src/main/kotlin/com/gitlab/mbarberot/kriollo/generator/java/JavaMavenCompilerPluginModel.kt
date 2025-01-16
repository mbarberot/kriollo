package com.gitlab.mbarberot.kriollo.generator.java

data class JavaMavenCompilerPluginModel(
    val version: String,
    val sourceVersion: String,
    val targetVersion: String,
    val errorProneVersion: String,
    val nullAwayVersion: String,
)
