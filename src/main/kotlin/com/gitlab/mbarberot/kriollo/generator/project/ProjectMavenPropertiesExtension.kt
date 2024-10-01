package com.gitlab.mbarberot.kriollo.generator.project

import com.gitlab.mbarberot.kriollo.generator.maven.MavenPropertiesExtension
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class ProjectMavenPropertiesExtension(
    val serviceProvider: ServiceProvider
) : MavenPropertiesExtension {
    override fun provide(): List<Pair<String, String>> {
        return listOf(
            Pair("project.build.sourceEncoding", "UTF-8")
        )
    }
}
