package specs.dev.concordion

import org.concordion.api.ConcordionFixture
import org.concordion.api.MultiValueResult
import specs.BaseFixture

@ConcordionFixture
class ConcordionXMLExtensionFixture : BaseFixture() {

    private val tab = " ".repeat(4);
    private val lineCount = 3
    var textInput = "";

    fun generatePlainText(): String = (1..lineCount).joinToString("\n") { "Line $it" }

    fun generatedTextContains(text: String): Boolean {
       return generatePlainText().containsIgnoringWhitespace(text.trim())
    }

    fun generateXML(): String = buildString {
        appendLine("<lines>")
        (1..lineCount).forEach { appendLine("$tab<line>$it</line>") }
        append("</lines>")
    }

    fun generatedXMLContains(text: String): Boolean {
        return generateXML().containsIgnoringWhitespace(text.trim())
    }
}

fun String.containsIgnoringWhitespace(otherString: String): Boolean {
    val actual = this.removeWhitespaces()
    val searched = otherString.removeWhitespaces()
    return actual.contains(searched)
}

fun String.removeWhitespaces(): String = this.replace(Regex("\\s"), "")