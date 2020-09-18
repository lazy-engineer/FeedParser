package io.github.lazyengineer.feedparser

import io.github.lazyengineer.feedparser.atom.AtomParserElement
import io.github.lazyengineer.feedparser.rss.RSSParserElement
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement

interface ParserElement {

	companion object {
		fun from(
			elementName: String,
			previousPath: String,
			depth: Int
		): ParserElement? {
			if (depth <= 0) return null
			val eventPath = getEventPathOfElement(elementName, previousPath, depth)

			return when {
				eventPath.startsWith("/rss") -> RSSParserElement.values()
						.find { it.element == eventPath }
				eventPath.startsWith("/rdf:RDF") -> RDFParserElement.values()
						.find { it.element == eventPath }
				eventPath.startsWith("/feed") -> AtomParserElement.values()
						.find { it.element == eventPath }
				else -> null
			}
		}

		private fun getEventPathOfElement(
			elementName: String,
			previousPath: String,
			depth: Int
		): String {
			var eventPath = previousPath

			val elementStack = eventPath.split("/")
					.filter { element -> element.isNotEmpty() }

			when {
				depth < elementStack.size -> eventPath = addElementToPositionOfDepth(elementName, elementStack, depth)
				depth > elementStack.size -> eventPath += "/$elementName"
				depth == elementStack.size -> if (elementName != elementStack[depth - 1]) eventPath =
					replaceElementOnSameDepth(eventPath, elementName)
			}

			return eventPath
		}

		private fun replaceElementOnSameDepth(
			eventPath: String,
			elementName: String
		) = "${eventPath.substringBeforeLast("/")}/$elementName"

		private fun addElementToPositionOfDepth(
			elementName: String,
			elementStack: List<String>,
			depth: Int
		) = "${elementPathTillDepth(elementStack, depth)}/$elementName"

		private fun elementPathTillDepth(
			elementStack: List<String>,
			depth: Int
		): String {
			var elementPath = String()
			for (i in 0 until depth - 1) {
				elementPath += "/${elementStack[i]}"
			}

			return elementPath
		}
	}
}
