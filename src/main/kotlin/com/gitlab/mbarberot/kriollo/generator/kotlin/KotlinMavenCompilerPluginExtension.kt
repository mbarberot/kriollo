package com.gitlab.mbarberot.kriollo.generator.kotlin

import com.gitlab.mbarberot.kriollo.HardcodedValues
import com.gitlab.mbarberot.kriollo.generator.base.extensions.TemplatedExtension
import com.gitlab.mbarberot.kriollo.generator.maven.MavenPluginExtension
import com.gitlab.mbarberot.kriollo.generator.maven.SimpleMavenPluginModel
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class KotlinMavenCompilerPluginExtension(
    serviceProvider: ServiceProvider,
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {

    override fun getTemplatePath(): String {
        return "generator/kotlin/maven-compiler-plugin.xml.kte"
    }

    override fun getAllTemplateData(): List<Any> {
        val jdkVersion = serviceProvider.configuration.java.version
        return listOf(
            SimpleMavenPluginModel(
                artifactId = "maven-compiler-plugin",
                version = HardcodedValues.MAVEN_COMPILER_PLUGIN_VERSION,
                configuration = mapOf(
                    Pair("source", jdkVersion),
                    Pair("target", jdkVersion),
                )
            )
        )
    }
}
