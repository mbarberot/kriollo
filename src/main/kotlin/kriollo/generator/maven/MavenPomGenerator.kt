package kriollo.generator.maven

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.generator.base.extensions.JavaDependencyExtension
import kriollo.generator.base.extensions.MavenPluginExtension

class MavenPomGenerator(val configuration: CodegenConfiguration) : TemplatedFileGenerator() {

    private val dependencyExtensions = mutableListOf<JavaDependencyExtension>()
    private val pluginExtensions = mutableListOf<MavenPluginExtension>()

    override fun getFilePath(configuration: CodegenConfiguration) = "pom.xml"

    override fun getTemplatePath() = "generator/maven/pom.xml.kte"

    override fun getTemplateData() = PomModel(
        configuration.project.mainClass,
        buildList<JavaArtifact> {
            dependencyExtensions
                .map { extension -> extension.provideDependencies() }
                .forEach { dependencies -> addAll(dependencies) }
        },
        kotlin = KotlinModel(
            configuration.kotlin.version
        ),
        jte = JteModel(
            // TODO : do not hardcode plugins
            configuration.templating.jte.version,
            configuration.templating.jte.sourceDirectory,
            configuration.templating.jte.contentType,
        ),
        plugins = buildList {
            pluginExtensions
                .map { extension -> extension.providePlugins() }
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

