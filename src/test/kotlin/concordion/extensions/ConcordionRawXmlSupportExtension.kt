package concordion.extensions

import nu.xom.Document
import nu.xom.Element
import org.concordion.api.extension.ConcordionExtender
import org.concordion.api.extension.ConcordionExtension
import org.concordion.api.listener.DocumentParsingListener

class ConcordionRawXmlSupportExtension : ConcordionExtension {

    override fun addTo(extender: ConcordionExtender?) {
        if (extender == null) {
            return
        }

        extender.withDocumentParsingListener(object : DocumentParsingListener {
            override fun beforeParsing(document: Document?) {
                if (document == null) {
                    return
                }

                val xmlCodeBlocks = document.query("//code[@data-type='xml']")
                xmlCodeBlocks.forEach { node ->
                    if (node is Element) {
                        val childrenToText = node.getChildElements().joinToString("\n") { child -> child.toXML() }
                        node.removeChildren()
                        node.appendChild(childrenToText)
                    }
                }
            }
        })
    }
}