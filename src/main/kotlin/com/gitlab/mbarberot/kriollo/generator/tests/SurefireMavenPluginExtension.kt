package com.gitlab.mbarberot.kriollo.generator.tests

import com.gitlab.mbarberot.kriollo.generator.base.extensions.TemplatedExtension
import com.gitlab.mbarberot.kriollo.generator.maven.MavenPluginExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class SurefireMavenPluginExtension(
    serviceProvider: ServiceProvider,
) : MavenPluginExtension, TemplatedExtension(serviceProvider) {
    override fun getTemplatePath(): String {
        return "generator/tests/surefire-maven-plugin.kte"
    }

    override fun getAllTemplateData(): List<Any> {

        val testConfiguration = serviceProvider.configurationService.get(TestsConfiguration::class)
        val livingSpec = testConfiguration.livingSpecifications.enabled

        return listOf(
            SurefireMavenPluginModel(
                version = "2.22.2", // TODO > hardcoded versions ?
                systemPropertyVariables = buildMap {
                    if (livingSpec) {
                        put("concordion.output.dir", "docs")
                    }
                },
                includes = buildList {
                    if(livingSpec) {
                        add("**/*Fixture")
                        add("**/*Test")
                    }
                }
            )
        )
    }
}

