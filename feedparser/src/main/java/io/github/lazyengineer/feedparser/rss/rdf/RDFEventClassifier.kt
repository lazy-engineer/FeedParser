package io.github.lazyengineer.feedparser.rss.rdf

import io.github.lazyengineer.feedparser.AttributeEvent
import io.github.lazyengineer.feedparser.ContainerEvent
import io.github.lazyengineer.feedparser.ParserEvent
import io.github.lazyengineer.feedparser.UnsupportedEvent
import io.github.lazyengineer.feedparser.ValueEvent
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.*

fun RDFParserElement.classify(): ParserEvent = when (this) {
	RDF -> ContainerEvent
	RDF_CHANNEL -> AttributeEvent
	RDF_CHANNEL_TITLE -> ValueEvent
	RDF_CHANNEL_LINK -> ValueEvent
	RDF_CHANNEL_DESCRIPTION -> ValueEvent
	RDF_CHANNEL_IMAGE -> AttributeEvent
	RDF_CHANNEL_TEXT_INPUT -> AttributeEvent
	RDF_CHANNEL_ITEMS -> ContainerEvent
	RDF_CHANNEL_ITEMS_RDF_SEQ -> ContainerEvent
	RDF_CHANNEL_ITEMS_RDF_SEQ_RDF_LI -> AttributeEvent

	RDF_IMAGE -> AttributeEvent
	RDF_IMAGE_TITLE -> ValueEvent
	RDF_IMAGE_URL -> ValueEvent
	RDF_IMAGE_LINK -> ValueEvent

	RDF_TEXT_INPUT -> AttributeEvent
	RDF_TEXT_INPUT_TITLE -> ValueEvent
	RDF_TEXT_INPUT_DESCRIPTION -> ValueEvent
	RDF_TEXT_INPUT_NAME -> ValueEvent
	RDF_TEXT_INPUT_LINK -> ValueEvent

	RDF_ITEM -> AttributeEvent
	RDF_ITEM_TITLE -> ValueEvent
	RDF_ITEM_LINK -> ValueEvent
	RDF_ITEM_DESCRIPTION -> ValueEvent

	RDF_CHANNEL_SYNDICATION_UPDATE_PERIOD -> ValueEvent
	RDF_CHANNEL_SYNDICATION_UPDATE_FREQUENCY -> ValueEvent
	RDF_CHANNEL_SYNDICATION_UPDATE_BASE -> ValueEvent

	RDF_CHANNEL_DUBLIN_CORE_TITLE -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_CREATOR -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_SUBJECT -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_DESCRIPTION -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_PUBLISHER -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_CONTRIBUTOR -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_DATE -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_TYPE -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_FORMAT -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_IDENTIFIER -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_SOURCE -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_LANGUAGE -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_RELATION -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_COVERAGE -> ValueEvent
	RDF_CHANNEL_DUBLIN_CORE_RIGHTS -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_TITLE -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_CREATOR -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_SUBJECT -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_DESCRIPTION -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_PUBLISHER -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_CONTRIBUTOR -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_DATE -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_TYPE -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_FORMAT -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_IDENTIFIER -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_SOURCE -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_LANGUAGE -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_RELATION -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_COVERAGE -> ValueEvent
	RDF_ITEM_DUBLIN_CORE_RIGHTS -> ValueEvent

	RDF_ITEM_CONTENT_ENCODED -> ValueEvent

	UNSUPPORTED_RDF_ELEMENT -> UnsupportedEvent
}
