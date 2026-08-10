package specs.tests.concordion

import com.gitlab.mbarberot.kriollo.features.Generate
import com.gitlab.mbarberot.kriollo.generator.java.JavaConfiguration
import com.gitlab.mbarberot.kriollo.generator.maven.MavenConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.TestsConfiguration
import com.gitlab.mbarberot.kriollo.generator.tests.livingspecifications.LivingSpecificationsConfiguration
import com.gitlab.mbarberot.kriollo.services.configuration.BasicCodegenConfiguration
import factories.Configs.projectConfiguration
import factories.services.TestConfigurationService
import factories.services.TestFileSystemService
import factories.services.TestServiceProvider
import factories.services.fs.FakeFileSystem
import org.concordion.api.ConcordionFixture
import org.concordion.api.MultiValueResult
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import specs.BaseFixture
import java.io.StringWriter
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

@ConcordionFixture
class ConcordionTestsFixture : BaseFixture() {

    companion object {
        private val xmlParserFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance().apply {
            isIgnoringElementContentWhitespace = true
        }
        private val xmlParser: DocumentBuilder = xmlParserFactory.newDocumentBuilder()
        private val xpath: XPath = XPathFactory.newInstance().newXPath()
        private val xmlWriter: Transformer = TransformerFactory.newInstance().newTransformer().apply {
            setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes")
            setOutputProperty(OutputKeys.INDENT, "yes");
            setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        }
    }

    fun activateConcordion(): MultiValueResult {
        // Arrange
        val fakeFileSystem = FakeFileSystem()
        val serviceProvider = TestServiceProvider(
            configuration = BasicCodegenConfiguration(
                project = projectConfiguration,
                java = JavaConfiguration(enabled = true),
                maven = MavenConfiguration(enabled = true),
                tests = TestsConfiguration(
                    enabled = true,
                    livingSpecifications = LivingSpecificationsConfiguration(enabled = true)
                )
            ),
            configurationService = TestConfigurationService.javaMavenProject()
                .withTestConfig(
                    TestsConfiguration(
                        enabled = true,
                        livingSpecifications = LivingSpecificationsConfiguration(enabled = true)
                    )
                ),
            fileSystem = TestFileSystemService(fakeFileSystem),
            templatingService = TestServiceProvider.jteTemplatingService,
        )
        val useCase = Generate(serviceProvider)

        // Act
        useCase.generate()

        // Assert
        val pom = fakeFileSystem.getContentOf("pom.xml")
        val parsedPom = parseXml(pom)
        val dependencyXml = extractDependency(parsedPom)
        val pluginXml = extractPlugin(parsedPom)

        return MultiValueResult()
            .with("dependency", dependencyXml)
            .with("plugin", pluginXml)
    }

    private fun extractDependency(parsedPom: Document?): String {
        val selector = "/project/dependencies/dependency[scope='test' and artifactId='concordion']"
        val nodes = xpath.compile(selector).evaluate(parsedPom, XPathConstants.NODESET) as NodeList
        return convertXml(nodes.item(0))
    }

    private fun extractPlugin(parsedPom: Document?): String {
        val selector = "/project/build/plugins/plugin[artifactId='maven-surefire-plugin']"
        val nodes = xpath.compile(selector).evaluate(parsedPom, XPathConstants.NODESET) as NodeList
        return convertXml(nodes.item(0))
    }

    private fun parseXml(pom: String): Document? {
        val parsedXml = xmlParser.parse(pom.byteInputStream())
        clean(parsedXml);
        return parsedXml;
    }

    private fun clean(node: Node) {
        val emptyTextNodes = xpath.evaluate("//text()[normalize-space(.)='']", node, XPathConstants.NODESET) as NodeList
        for (i in 0..<emptyTextNodes.length) {
            val node = emptyTextNodes.item(i)
            node.parentNode.removeChild(node)
        }
    }

    private fun convertXml(node: Node?): String {
        return if (node == null) {
            "[NOT FOUND]"
        } else {
            val stringWriter = StringWriter()
            xmlWriter.transform(DOMSource(node), StreamResult(stringWriter))
            stringWriter.toString()
        }
    }

}