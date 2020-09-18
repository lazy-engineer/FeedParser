package io.github.lazyengineer.feedparser.rss.rdf

import io.github.lazyengineer.feedparser.AttributeEvent
import io.github.lazyengineer.feedparser.ContainerEvent
import io.github.lazyengineer.feedparser.ParserEvent
import io.github.lazyengineer.feedparser.ValueEvent
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
}
