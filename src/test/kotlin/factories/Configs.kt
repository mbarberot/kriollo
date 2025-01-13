package factories

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.generator.java.JavaConfiguration
import com.gitlab.mbarberot.kriollo.generator.kotlin.KotlinConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.project.ProjectConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration

object Configs {
    private val projectConfiguration = ProjectConfiguration(
        groupId = "org.acme",
        name = "anvil",
        version = "2.5.9"
    )

    fun basicConfig(): CodegenConfiguration {
        return BasicCodegenConfiguration(
            project = projectConfiguration
        )
    }

    fun kotlinProject(): CodegenConfiguration {
        return BasicCodegenConfiguration(
            project = projectConfiguration,
            kotlin = KotlinConfiguration(enabled = true),
            maven = MavenConfiguration(enabled = true)
        )
    }

    fun javaProject(): CodegenConfiguration {
        return BasicCodegenConfiguration(
            project = projectConfiguration,
            java = JavaConfiguration(enabled = true),
            maven = MavenConfiguration(enabled = true)
        )
    }
}