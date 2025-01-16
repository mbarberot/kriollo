package com.gitlab.mbarberot.kriollo.generator.java

import com.gitlab.mbarberot.kriollo.HardcodedValues
import com.gitlab.mbarberot.kriollo.generator.base.extensions.TemplatedExtension
import com.gitlab.mbarberot.kriollo.generator.maven.MavenPluginExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class JavaMavenPluginExtension(
    serviceProvider: ServiceProvider,
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {

    override fun getTemplatePath(): String {
        return "generator/java/maven-compiler-plugin.xml.kte"
    }

    override fun getAllTemplateData(): List<Any> {
        val jdkVersion = serviceProvider.configuration.java.version
        return listOf(
            JavaMavenCompilerPluginModel(
                version = HardcodedValues.MAVEN_COMPILER_PLUGIN_VERSION,
                sourceVersion = jdkVersion,
                targetVersion = jdkVersion,
                errorProneVersion = HardcodedValues.DEFAULT_ERROR_PRONE_VERSION,
                nullAwayVersion = HardcodedValues.DEFAULT_NULL_AWAY_VERSION,
            )
        )
    }
}
