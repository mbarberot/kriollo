package com.gitlab.mbarberot.kriollo.generator.maven

import com.gitlab.mbarberot.kriollo.HardcodedValues
import com.gitlab.mbarberot.kriollo.services.provider.ServiceProvider

class MavenExecPluginRenderer(
    val serviceProvider: ServiceProvider,
    executions: List<MavenExecPlugin> = listOf(),
) {

    private val templatePath = "generator/maven/simple-maven-plugin.kte"

    private val templateData = SimpleMavenPluginModel(
        groupId = "org.codehaus.mojo",
        artifactId = "exec-maven-plugin",
        version = HardcodedValues.DEFAULT_MAVEN_EXEC_PLUGIN_VERSION,
        executions = executions.map { execution ->
            /*
            <plugin>
                <executions>
                    <execution>
                        // baseline
             */
            """
                        <id>${execution.id}</id>
                        <phase>${execution.phase}</phase>
                        <goals>
                            <goal>exec</goal>
                        </goals>
                        <configuration>
                            <executable>bash</executable>
                            <arguments>
                                <argument>-c</argument>
                                <argument>${execution.command}</argument>
                            </arguments>
                        </configuration>
            """
        }
    )

    fun render(): String {
        return serviceProvider.templatingService.renderToString(templatePath, templateData)
    }
}
