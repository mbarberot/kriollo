package factories

import com.gitlab.mbarberot.kriollo.configuration.CodegenConfiguration
import com.gitlab.mbarberot.kriollo.generator.project.ProjectConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration

object Configs {
    fun basicConfig(): CodegenConfiguration {
        return BasicCodegenConfiguration(
            project = ProjectConfiguration(
                groupId = "org.acme",
                name = "anvil",
                version = "2.5.9"
            )
        )
    }
}