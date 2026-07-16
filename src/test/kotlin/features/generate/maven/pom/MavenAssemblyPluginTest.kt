package features.generate.maven.pom

import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.generator.project.ProjectConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration
import factories.services.TestConfigurationService
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MavenAssemblyPluginTest {

    @Test
    fun `has maven assembly plugin`() {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = BasicCodegenConfiguration(),
            configurationService = TestConfigurationService.javaMavenProject()
                .withTestConfig(ProjectConfiguration(
                    type = "application",
                    groupId = "org.acme",
                    name = "anvil",
                    version = "2.5.9",
                    mainClass = "AnvilApp.kt"
                )),
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
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-assembly-plugin</artifactId>
                    <executions>
                        <execution>
                            <phase>package</phase>
                            <goals>
                                <goal>single</goal>
                            </goals>
                            <configuration>
                                <archive>
                                    <manifest>
                                        <mainClass>org.acme.anvil.AnvilApp.kt</mainClass>
                                    </manifest>
                                </archive>
                                <descriptorRefs>
                                    <descriptorRef>jar-with-dependencies</descriptorRef>
                                </descriptorRefs>
                                <appendAssemblyId>false</appendAssemblyId>
                            </configuration>
                        </execution>
                    </executions>
                </plugin>
            """.trimIndent()
            )

        //println(fakeFileSystem.getContentOf("pom.xml"))

    }

}