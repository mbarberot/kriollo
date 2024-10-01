package com.gitlab.mbarberot.kriollo.generator.maven

import com.gitlab.mbarberot.kriollo.KriolloException
import com.gitlab.mbarberot.kriollo.generator.base.TemplatedFileGenerator
import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.java.JavaDependencyExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class MavenPomGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {

    private val propertiesExtension = mutableListOf<MavenPropertiesExtension>()
    private val dependencyExtensions = mutableListOf<JavaDependencyExtension>()
    private val pluginExtensions = mutableListOf<MavenPluginExtension>()
    private val bomExtensions = mutableListOf<MavenBomExtension>()
    private val artifactExtensions = mutableListOf<MavenArtifactExtension>()

    override fun getFilePath() = "pom.xml"

    override fun getTemplatePath() = "generator/maven/pom.xml.kte"

    override fun getTemplateData() = PomModel(
        artifact = getArtifactOrFail(),
        properties = buildMap {
            propertiesExtension
                .flatMap { it.provide() }
                .forEach { (key, value) -> put(key, value) }
        },
        dependencies = buildList {
            dependencyExtensions
                .map { extension -> extension.provide() }
                .forEach { dependencies -> addAll(dependencies) }
        },
        plugins = buildList {
            pluginExtensions
                .map { extension -> extension.provide() }
                .forEach { plugin -> addAll(plugin) }

        },
        boms = buildList {
            bomExtensions
                .map { extension -> extension.provide() }
                .forEach { bom -> addAll(bom) }
        },
        repositories = PomRepositoriesModel(
            jitpack = serviceProvider.configuration.maven.repositories.jitpack
        )
    )

    private fun getArtifactOrFail(): JavaArtifact {
        val artifacts: List<JavaArtifact> = artifactExtensions.flatMap { it.provide() }
        when {
            artifacts.isEmpty() -> throw KriolloException("No artifact defined")
            artifacts.size > 1 -> throw KriolloException("Multiple artifacts defined")
        }
        val artifact = artifacts.first()
        return artifact
    }

    override fun registerExtension(extension: JavaDependencyExtension) {
        dependencyExtensions.add(extension)
    }

    override fun registerExtension(extension: MavenPluginExtension) {
        pluginExtensions.add(extension)
    }

    override fun registerExtension(extension: MavenPropertiesExtension) {
        propertiesExtension.add(extension)
    }

    override fun registerExtension(extension: MavenBomExtension) {
        bomExtensions.add(extension)
    }

    override fun registerExtension(extension: MavenArtifactExtension) {
        when {
            artifactExtensions.isEmpty() -> artifactExtensions.add(extension)
            else -> throw KriolloException("Only one MavenArtifactExtension should be registered")
        }
    }
}

