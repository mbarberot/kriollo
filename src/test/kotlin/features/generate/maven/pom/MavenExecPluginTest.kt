package features.generate.maven.pom

import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.generator.java.JavaConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.project.BuildStepConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration
import factories.Configs
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MavenExecPluginTest {

    @Test
    fun `has maven exec plugin`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = BasicCodegenConfiguration(
                project = Configs.projectConfiguration.copy(
                    customBuildSteps = listOf(
                        BuildStepConfiguration(id = "foo", step = "package", command = "tar xvf foo.zip foo/"),
                        BuildStepConfiguration(id = "bar", step = "verify", command = "cat bar.txt | grep 'bar'"),
                    )
                ),
                java = JavaConfiguration(enabled = true),
                maven = MavenConfiguration(enabled = true),
            ),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        assertThat(fakeFileSystem.getContentOf("pom.xml"))
            .containsIgnoringWhitespaces(
                """
                <plugin>
                    <groupId>org.codehaus.mojo</groupId>
                    <artifactId>exec-maven-plugin</artifactId>
                    <version>3.1.0</version>
                    <executions>
                        <execution>
                            <id>foo</id>
                            <phase>package</phase>
                            <goals>
                                <goal>exec</goal>
                            </goals>
                            <configuration>
                                <executable>bash</executable>
                                <arguments>
                                    <argument>-c</argument>
                                    <argument>tar xvf foo.zip foo/</argument>
                                </arguments>
                            </configuration>
                        </execution>
                        <execution>
                            <id>bar</id>
                            <phase>verify</phase>
                            <goals>
                                <goal>exec</goal>
                            </goals>
                            <configuration>
                                <executable>bash</executable>
                                <arguments>
                                    <argument>-c</argument>
                                    <argument>cat bar.txt | grep 'bar'</argument>
                                </arguments>
                            </configuration>
                        </execution>
                    </executions>
                </plugin>
            """.trimIndent()
            )

        println(fakeFileSystem.getContentOf("pom.xml"))
    }

}