package kriollo.generator.maven

import kriollo.configuration.CodegenConfiguration
import kriollo.configuration.JavaArtifact
import kriollo.generator.base.TemplatedFileGenerator
import kriollo.generator.base.extensions.JavaDependenciesGeneratorExtensions

class MavenPomGenerator(val configuration: CodegenConfiguration) : TemplatedFileGenerator() {

    private val extensions = mutableListOf<JavaDependenciesGeneratorExtensions>()

    override fun isActivated(configuration: CodegenConfiguration): Boolean {
        return configuration.maven.enabled
    }

    override fun getFilePath(configuration: CodegenConfiguration) = "pom.xml"

    override fun getTemplatePath() = "generator/maven/pom.xml.kte"

    override fun getTemplateData() = PomModel(
        configuration.project.mainClass,
        findDependencies(configuration),
        kotlin = KotlinModel(
            configuration.kotlin.version
        ),
        jte = JteModel(
            // TODO : do not hardcode plugins
            configuration.templating.jte.version,
            configuration.templating.jte.sourceDirectory,
            configuration.templating.jte.contentType,
        )
    )

    private fun findDependencies(configuration: CodegenConfiguration): List<JavaArtifact> {
        return buildList {
            extensions
                .map { extension -> extension.provideDependencies() }
                .forEach { dependencies -> addAll(dependencies) }
        }
    }

    override fun registerExtension(extension: JavaDependenciesGeneratorExtensions) {
        extensions.add(extension)
    }
}

