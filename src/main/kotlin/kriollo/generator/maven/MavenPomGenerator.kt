package kriollo.generator.maven

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.generator.java.JavaDependencyExtension
import kriollo.services.provider.ServiceProvider

class MavenPomGenerator(val serviceProvider: ServiceProvider) : TemplatedFileGenerator() {

    private val propertiesExtension = mutableListOf<MavenPropertiesExtension>()
    private val dependencyExtensions = mutableListOf<JavaDependencyExtension>()
    private val pluginExtensions = mutableListOf<MavenPluginExtension>()
    private val bomExtensions = mutableListOf<MavenBomExtension>()

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

        },
        boms = buildList {
           bomExtensions
               .map { extension -> extension.provide() }
               .forEach { bom -> addAll(bom) }
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

    override fun registerExtension(extension: MavenBomExtension) {
        bomExtensions.add(extension)
    }
}

