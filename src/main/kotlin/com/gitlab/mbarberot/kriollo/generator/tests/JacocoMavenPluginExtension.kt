package com.gitlab.mbarberot.kriollo.generator.tests

import com.gitlab.mbarberot.kriollo.HardcodedValues
import com.gitlab.mbarberot.kriollo.generator.base.extensions.TemplatedExtension
import com.gitlab.mbarberot.kriollo.generator.java.JavaArtifact
import com.gitlab.mbarberot.kriollo.generator.maven.MavenPluginExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class JacocoMavenPluginExtension(
    serviceProvider: ServiceProvider,
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
                    version = HardcodedValues.DEFAULT_JACOCO_VERSION
                ),
                branchMinimumCoverage = HardcodedValues.DEFAULT_JACOCO_BRANCH_COVERAGE,
                classMaximumMissed = HardcodedValues.DEFAULT_JACOCO_CLASS_COVERAGE,
            )
        )
    }

}