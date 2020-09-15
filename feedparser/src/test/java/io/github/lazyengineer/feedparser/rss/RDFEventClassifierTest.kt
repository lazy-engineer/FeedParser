package io.github.lazyengineer.feedparser.rss

import io.github.lazyengineer.feedparser.AttributeEvent
import io.github.lazyengineer.feedparser.ContainerEvent
import io.github.lazyengineer.feedparser.UnsupportedEvent
import io.github.lazyengineer.feedparser.ValueEvent
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_CONTRIBUTOR
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_COVERAGE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_CREATOR
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_DATE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_FORMAT
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_IDENTIFIER
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_LANGUAGE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_PUBLISHER
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_RELATION
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_RIGHTS
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_SOURCE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_SUBJECT
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_TITLE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_DUBLIN_CORE_TYPE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_IMAGE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_ITEMS
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_ITEMS_RDF_SEQ
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_ITEMS_RDF_SEQ_RDF_LI
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_LINK
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_SYNDICATION_UPDATE_BASE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_SYNDICATION_UPDATE_FREQUENCY
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_SYNDICATION_UPDATE_PERIOD
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_TEXT_INPUT
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_CHANNEL_TITLE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_IMAGE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_IMAGE_LINK
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_IMAGE_TITLE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_IMAGE_URL
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_CONTENT_ENCODED
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_CONTRIBUTOR
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_COVERAGE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_CREATOR
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_DATE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_FORMAT
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_IDENTIFIER
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_LANGUAGE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_PUBLISHER
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_RELATION
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_RIGHTS
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_SOURCE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_SUBJECT
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_TITLE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_DUBLIN_CORE_TYPE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_LINK
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_ITEM_TITLE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_TEXT_INPUT
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_TEXT_INPUT_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_TEXT_INPUT_LINK
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_TEXT_INPUT_NAME
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.RDF_TEXT_INPUT_TITLE
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.UNSUPPORTED_RDF_ELEMENT
import io.github.lazyengineer.feedparser.rss.rdf.classify
import org.amshove.kluent.`should be instance of`
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RDFEventClassifierTest {

	@Test
	fun `unsupported element should be classified as unsupported event`() {
		val parserElement: RDFParserElement = UNSUPPORTED_RDF_ELEMENT

		parserElement.classify() `should be instance of` UnsupportedEvent.javaClass
	}

	@Test
	fun `channel title element should be classified as value event`() {
		val parserElement: RDFParserElement = RDF_CHANNEL_TITLE

		parserElement.classify() `should be instance of` ValueEvent.javaClass
	}

	@Test
	fun `all container elements should be classified as such`() {
		val parserElements = mutableListOf<RDFParserElement>()
		parserElements.add(RDF)
		parserElements.add(RDF_CHANNEL_ITEMS)
		parserElements.add(RDF_CHANNEL_ITEMS_RDF_SEQ)

		parserElements.forEach { parserElement ->
			parserElement.classify() `should be instance of` ContainerEvent.javaClass
		}
	}

	@Test
	fun `all attribute elements should be classified as such`() {
		val parserElements = mutableListOf<RDFParserElement>()
		parserElements.add(RDF_CHANNEL)
		parserElements.add(RDF_CHANNEL_IMAGE)
		parserElements.add(RDF_CHANNEL_TEXT_INPUT)
		parserElements.add(RDF_CHANNEL_ITEMS_RDF_SEQ_RDF_LI)
		parserElements.add(RDF_IMAGE)
		parserElements.add(RDF_TEXT_INPUT)
		parserElements.add(RDF_ITEM)

		parserElements.forEach { parserElement ->
			parserElement.classify() `should be instance of` AttributeEvent.javaClass
		}
	}

	@Test
	fun `all value elements should be classified as such`() {
		val parserElements = mutableListOf<RDFParserElement>()
		parserElements.add(RDF_CHANNEL_TITLE)
		parserElements.add(RDF_CHANNEL_LINK)
		parserElements.add(RDF_CHANNEL_DESCRIPTION)
		parserElements.add(RDF_IMAGE_TITLE)
		parserElements.add(RDF_IMAGE_URL)
		parserElements.add(RDF_IMAGE_LINK)
		parserElements.add(RDF_TEXT_INPUT_TITLE)
		parserElements.add(RDF_TEXT_INPUT_DESCRIPTION)
		parserElements.add(RDF_TEXT_INPUT_NAME)
		parserElements.add(RDF_TEXT_INPUT_LINK)
		parserElements.add(RDF_ITEM_TITLE)
		parserElements.add(RDF_ITEM_LINK)
		parserElements.add(RDF_ITEM_DESCRIPTION)
		parserElements.add(RDF_CHANNEL_SYNDICATION_UPDATE_PERIOD)
		parserElements.add(RDF_CHANNEL_SYNDICATION_UPDATE_FREQUENCY)
		parserElements.add(RDF_CHANNEL_SYNDICATION_UPDATE_BASE)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_TITLE)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_CREATOR)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_SUBJECT)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_DESCRIPTION)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_PUBLISHER)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_CONTRIBUTOR)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_DATE)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_TYPE)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_FORMAT)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_IDENTIFIER)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_SOURCE)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_LANGUAGE)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_RELATION)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_COVERAGE)
		parserElements.add(RDF_CHANNEL_DUBLIN_CORE_RIGHTS)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_TITLE)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_CREATOR)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_SUBJECT)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_DESCRIPTION)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_PUBLISHER)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_CONTRIBUTOR)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_DATE)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_TYPE)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_FORMAT)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_IDENTIFIER)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_SOURCE)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_LANGUAGE)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_RELATION)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_COVERAGE)
		parserElements.add(RDF_ITEM_DUBLIN_CORE_RIGHTS)
		parserElements.add(RDF_ITEM_CONTENT_ENCODED)

		parserElements.forEach { parserElement ->
			parserElement.classify() `should be instance of` ValueEvent.javaClass
		}
	}
}