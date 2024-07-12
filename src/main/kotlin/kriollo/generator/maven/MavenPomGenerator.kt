package kriollo.generator.maven

import kriollo.configuration.CodegenConfiguration
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.generator.base.extensions.JavaDependencyExtension

class MavenPomGenerator(val configuration: CodegenConfiguration) : TemplatedFileGenerator() {

    private val dependencyExtensions = mutableListOf<JavaDependencyExtension>()
    private val pluginExtensions = mutableListOf<MavenPluginExtension>()

    override fun getFilePath(configuration: CodegenConfiguration) = "pom.xml"

    override fun getTemplatePath() = "generator/maven/pom.xml.kte"

    override fun getTemplateData() = PomModel(
        properties = mapOf(
            Pair("project.build.sourceEncoding", "UTF-8"),
            Pair("kotlin.code.style", "official"),
            Pair("kotlin.compiler.jvmTarget", "21"),
        ),
        dependencies = buildList {
            dependencyExtensions
                .map { extension -> extension.provideDependencies() }
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
}

