package com.gitlab.mbarberot.kriollo.generator.tests.coverage

import com.gitlab.mbarberot.kriollo.generator.base.extensions.TemplatedExtension
import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.maven.MavenPluginExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class JacocoMavenPluginExtension(
    serviceProvider: ServiceProvider,
    private val configuration: CoverageConfiguration = serviceProvider.configuration.tests.coverage
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {
    override fun getTemplatePath(): String {
        return "generator/test/jacoco-maven-plugin.xml.kte"
    }

    override fun getAllTemplateData(): List<Any> {
        return listOf(
            JacocoMavenPluginModel(
                artifact = JavaArtifact(
                    groupId = "org.jacoco",
                    artifactId = "jacoco-maven-plugin",
                    version = configuration.jacocoVersion
                ),
                minimumBranchCoverage = configuration.minimumBranchCoverage,
                minimumInstructionCoverage = configuration.minimumInstructionCoverage,
                maximumMissedClasses = configuration.maximumMissedClasses,
                exclusions = configuration.exclusions
            )
        )
    }

}