package kriollo.generator.maven

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.generator.java.JavaDependencyExtension

class MavenPomGenerator(val configuration: CodegenConfiguration) : TemplatedFileGenerator() {

    private val propertiesExtension = mutableListOf<MavenPropertiesExtension>()
    private val dependencyExtensions = mutableListOf<JavaDependencyExtension>()
    private val pluginExtensions = mutableListOf<MavenPluginExtension>()

    override fun getFilePath(configuration: CodegenConfiguration) = "pom.xml"

    override fun getTemplatePath() = "generator/maven/pom.xml.kte"

    override fun getTemplateData() = PomModel(
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

        }
    )

    override fun registerExtension(extension: JavaDependencyExtension) {
        dependencyExtensions.add(extension)
    }

    override fun registerExtension(extension: MavenPluginExtension) {
        pluginExtensions.add(extension)
    }

    override fun registerExtension(extension: MavenPropertiesExtension) {
        propertiesExtension.add(extension)
    }
}

