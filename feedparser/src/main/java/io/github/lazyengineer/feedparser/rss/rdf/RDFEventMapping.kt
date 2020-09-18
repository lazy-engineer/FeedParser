package io.github.lazyengineer.feedparser.rss.rdf

import io.github.lazyengineer.feedparser.ParserElement
import io.github.lazyengineer.feedparser.model.DateUtil
import io.github.lazyengineer.feedparser.model.channel.RDFChannel
import io.github.lazyengineer.feedparser.model.namespace.syndication.SyndicationUpdatePeriod
import io.github.lazyengineer.feedparser.model.rss.RSSChannelImage
import io.github.lazyengineer.feedparser.model.rss.RSSChannelItem
import io.github.lazyengineer.feedparser.model.rss.RSSChannelTextInput
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
import java.util.Locale

fun RDFChannel.mapEvent(
	eventType: ParserElement,
	attributes: Map<String, String> = emptyMap(),
	value: String = String()
) {
	when (eventType) {
		RDF -> {
		}
		RDF_CHANNEL -> this.attributes = RDFChannel.Attributes(attributes)
		RDF_CHANNEL_TITLE -> title = value
		RDF_CHANNEL_LINK -> link = value
		RDF_CHANNEL_DESCRIPTION -> description = value
		RDF_CHANNEL_IMAGE -> imageResource = attributes["rdf:resource"]
		RDF_CHANNEL_ITEMS -> {
		}
		RDF_CHANNEL_TEXT_INPUT -> textInputResource = attributes["rdf:resource"]
		RDF_CHANNEL_ITEMS_RDF_SEQ -> {
		}
		RDF_CHANNEL_ITEMS_RDF_SEQ_RDF_LI -> itemsResource = attributes["resource"]

		RDF_IMAGE -> image = RSSChannelImage(attributes = RSSChannelImage.Attributes(attributes))
		RDF_IMAGE_TITLE -> image?.title = value
		RDF_IMAGE_URL -> image?.url = value
		RDF_IMAGE_LINK -> image?.link = value
		RDF_TEXT_INPUT -> textInput = RSSChannelTextInput(attributes = RSSChannelTextInput.Attributes(attributes))
		RDF_TEXT_INPUT_TITLE -> textInput?.title = value
		RDF_TEXT_INPUT_DESCRIPTION -> textInput?.description = value
		RDF_TEXT_INPUT_NAME -> textInput?.name = value
		RDF_TEXT_INPUT_LINK -> textInput?.link = value

		RDF_ITEM -> items.add(RSSChannelItem(attributes = RSSChannelItem.Attributes(attributes)))

		RDF_ITEM_TITLE -> items.last().title = value
		RDF_ITEM_LINK -> items.last().link = value
		RDF_ITEM_DESCRIPTION -> items.last().description = value

		RDF_CHANNEL_SYNDICATION_UPDATE_PERIOD -> syndicationNamespace?.updatePeriod = SyndicationUpdatePeriod.from(value.toLowerCase(Locale.ROOT))
		RDF_CHANNEL_SYNDICATION_UPDATE_FREQUENCY -> syndicationNamespace?.updateFrequency = value.toInt()
		RDF_CHANNEL_SYNDICATION_UPDATE_BASE -> syndicationNamespace?.updateBase = DateUtil.parseDateString(value)

		RDF_CHANNEL_DUBLIN_CORE_TITLE -> dublinCoreNamespace?.title = value
		RDF_CHANNEL_DUBLIN_CORE_CREATOR -> dublinCoreNamespace?.creator = value
		RDF_CHANNEL_DUBLIN_CORE_SUBJECT -> dublinCoreNamespace?.subject = value
		RDF_CHANNEL_DUBLIN_CORE_DESCRIPTION -> dublinCoreNamespace?.description = value
		RDF_CHANNEL_DUBLIN_CORE_PUBLISHER -> dublinCoreNamespace?.publisher = value
		RDF_CHANNEL_DUBLIN_CORE_CONTRIBUTOR -> dublinCoreNamespace?.contributor = value
		RDF_CHANNEL_DUBLIN_CORE_DATE -> dublinCoreNamespace?.date = DateUtil.parseDateString(value)
		RDF_CHANNEL_DUBLIN_CORE_TYPE -> dublinCoreNamespace?.type = value
		RDF_CHANNEL_DUBLIN_CORE_FORMAT -> dublinCoreNamespace?.format = value
		RDF_CHANNEL_DUBLIN_CORE_IDENTIFIER -> dublinCoreNamespace?.identifier = value
		RDF_CHANNEL_DUBLIN_CORE_SOURCE -> dublinCoreNamespace?.source = value
		RDF_CHANNEL_DUBLIN_CORE_LANGUAGE -> dublinCoreNamespace?.language = value
		RDF_CHANNEL_DUBLIN_CORE_RELATION -> dublinCoreNamespace?.relation = value
		RDF_CHANNEL_DUBLIN_CORE_COVERAGE -> dublinCoreNamespace?.coverage = value
		RDF_CHANNEL_DUBLIN_CORE_RIGHTS -> dublinCoreNamespace?.rights = value
		RDF_ITEM_DUBLIN_CORE_TITLE -> items.last().dublinCoreNamespace?.title = value
		RDF_ITEM_DUBLIN_CORE_CREATOR -> items.last().dublinCoreNamespace?.creator = value
		RDF_ITEM_DUBLIN_CORE_SUBJECT -> items.last().dublinCoreNamespace?.subject = value
		RDF_ITEM_DUBLIN_CORE_DESCRIPTION -> items.last().dublinCoreNamespace?.description = value
		RDF_ITEM_DUBLIN_CORE_PUBLISHER -> items.last().dublinCoreNamespace?.publisher = value
		RDF_ITEM_DUBLIN_CORE_CONTRIBUTOR -> items.last().dublinCoreNamespace?.contributor = value
		RDF_ITEM_DUBLIN_CORE_DATE -> items.last().dublinCoreNamespace?.date = DateUtil.parseDateString(value)
		RDF_ITEM_DUBLIN_CORE_TYPE -> items.last().dublinCoreNamespace?.type = value
		RDF_ITEM_DUBLIN_CORE_FORMAT -> items.last().dublinCoreNamespace?.format = value
		RDF_ITEM_DUBLIN_CORE_IDENTIFIER -> items.last().dublinCoreNamespace?.identifier = value
		RDF_ITEM_DUBLIN_CORE_SOURCE -> items.last().dublinCoreNamespace?.source = value
		RDF_ITEM_DUBLIN_CORE_LANGUAGE -> items.last().dublinCoreNamespace?.language = value
		RDF_ITEM_DUBLIN_CORE_RELATION -> items.last().dublinCoreNamespace?.relation = value
		RDF_ITEM_DUBLIN_CORE_COVERAGE -> items.last().dublinCoreNamespace?.coverage = value
		RDF_ITEM_DUBLIN_CORE_RIGHTS -> items.last().dublinCoreNamespace?.rights = value

		RDF_ITEM_CONTENT_ENCODED -> items.last().contentNamespace?.encoded = value
	}
}