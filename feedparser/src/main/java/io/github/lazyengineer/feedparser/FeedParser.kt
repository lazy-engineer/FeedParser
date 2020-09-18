package io.github.lazyengineer.feedparser

import io.github.lazyengineer.feedparser.atom.AtomParserElement
import io.github.lazyengineer.feedparser.atom.classify
import io.github.lazyengineer.feedparser.atom.mapEvent
import io.github.lazyengineer.feedparser.atom.toFeed
import io.github.lazyengineer.feedparser.model.channel.AtomChannel
import io.github.lazyengineer.feedparser.model.channel.FeedChannel
import io.github.lazyengineer.feedparser.model.channel.RDFChannel
import io.github.lazyengineer.feedparser.model.channel.RSSChannel
import io.github.lazyengineer.feedparser.model.feed.Feed
import io.github.lazyengineer.feedparser.rss.RSSParserElement
import io.github.lazyengineer.feedparser.rss.classify
import io.github.lazyengineer.feedparser.rss.mapEvent
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement
import io.github.lazyengineer.feedparser.rss.rdf.classify
import io.github.lazyengineer.feedparser.rss.rdf.mapEvent
import io.github.lazyengineer.feedparser.rss.rdf.toFeed
import io.github.lazyengineer.feedparser.rss.toFeed
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.io.Reader

object FeedParser {

	fun parseFeed(
		xml: String,
		xmlPullParser: XmlPullParser
	): Feed {
		val reader: Reader = InputStreamReader(ByteArrayInputStream(xml.toByteArray()))
		xmlPullParser.setInput(reader)

		return parseFeed(xmlPullParser)
	}

	private fun parseFeed(xmlPullParser: XmlPullParser): Feed {
		var feedChannel: FeedChannel = RSSChannel()
		var eventType = xmlPullParser.eventType
		var path = String()

		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				when (xmlPullParser.name) {
					"rss" -> feedChannel = RSSChannel()
					"rdf:RDF" -> feedChannel = RDFChannel()
					"feed" -> feedChannel = AtomChannel()
				}
			}

			xmlPullParser.mapEvent(path, feedChannel)
					?.let { path = it }

			eventType = xmlPullParser.next()
		}

		return when (feedChannel) {
			is RSSChannel -> feedChannel.toFeed()
			is AtomChannel -> feedChannel.toFeed()
			is RDFChannel -> feedChannel.toFeed()
		}
	}

	private fun XmlPullParser.mapEvent(
		path: String,
		feedChannel: FeedChannel
	): String? {
		val event = getEvent(path)

		if (event != null && eventType == XmlPullParser.START_TAG) {
			feedChannel.mapStartEvent(event, this)
		}

		return when (feedChannel) {
			is RSSChannel -> if (event != null) (event as RSSParserElement).element else null
			is RDFChannel -> if (event != null) (event as RDFParserElement).element else null
			is AtomChannel -> if (event != null) (event as AtomParserElement).element else null
		}
	}

	private fun FeedChannel.mapStartEvent(
		element: ParserElement,
		xmlPullParser: XmlPullParser
	) {
		when (element.classifyElement()) {
			ValueEvent -> mapParserEvent(eventType = element, attributes = xmlPullParser.attributes(), value = xmlPullParser.nextTextOrEmpty())
			AttributeEvent -> mapParserEvent(eventType = element, attributes = xmlPullParser.attributes())
			ContainerEvent -> mapParserEvent(eventType = element)
			UnsupportedEvent -> {
			}
		}
	}

	private fun ParserElement.classifyElement() = when (this) {
		is RSSParserElement -> this.classify()
		is RDFParserElement -> this.classify()
		is AtomParserElement -> this.classify()
		else -> UnsupportedEvent
	}

	private fun FeedChannel.mapParserEvent(
		eventType: ParserElement,
		attributes: Map<String, String> = emptyMap(),
		value: String = String()
	) {
		when (this) {
			is RSSChannel -> this.mapEvent(eventType, attributes, value)
			is RDFChannel -> this.mapEvent(eventType, attributes, value)
			is AtomChannel -> this.mapEvent(eventType, attributes, value)
		}
	}

	private fun XmlPullParser.attributes(): MutableMap<String, String> {
		val attributes: MutableMap<String, String> = mutableMapOf()
		for (i in 0 until attributeCount) {
			attributes[getAttributeName(i)] = getAttributeValue(i).trim()
		}
		return attributes
	}

	private fun XmlPullParser.getEvent(path: String): ParserElement? = try {
		if (name != null) ParserElement.from(name, path, depth) else null
	} catch (e: IllegalArgumentException) {
		null
	}

	private fun XmlPullParser.nextTextOrEmpty() = try {
		nextText().replace("\n", "")
				.replace("\t", "")
				.replace("\\s+".toRegex(), " ")
				.trim()
				.trimMargin()
	} catch (e: XmlPullParserException) {
		String()
	}
}
