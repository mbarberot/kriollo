package specs

import concordion.extensions.ConcordionRawXmlSupportExtension
import org.concordion.api.ConcordionResources
import org.concordion.api.extension.Extensions

@ConcordionResources(
    "theme.css", "theme.js", "index.html"
)
@Extensions(ConcordionRawXmlSupportExtension::class)
open class BaseFixture