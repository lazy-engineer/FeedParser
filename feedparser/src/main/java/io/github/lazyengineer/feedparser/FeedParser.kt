package io.github.lazyengineer.feedparser

import io.github.lazyengineer.feedparser.atom.AtomParserElement
import io.github.lazyengineer.feedparser.atom.AtomParserElement.UNSUPPORTED_ATOM_ELEMENT
import io.github.lazyengineer.feedparser.atom.classify
import io.github.lazyengineer.feedparser.atom.mapEvent
import io.github.lazyengineer.feedparser.atom.toFeed
import io.github.lazyengineer.feedparser.model.channel.AtomChannel
import io.github.lazyengineer.feedparser.model.channel.FeedChannel
import io.github.lazyengineer.feedparser.model.channel.RDFChannel
import io.github.lazyengineer.feedparser.model.channel.RSSChannel
import io.github.lazyengineer.feedparser.model.feed.Feed
import io.github.lazyengineer.feedparser.rss.RSSParserElement
import io.github.lazyengineer.feedparser.rss.RSSParserElement.UNSUPPORTED_RSS_ELEMENT
import io.github.lazyengineer.feedparser.rss.classify
import io.github.lazyengineer.feedparser.rss.mapEvent
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement
import io.github.lazyengineer.feedparser.rss.rdf.RDFParserElement.UNSUPPORTED_RDF_ELEMENT
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

			when (feedChannel) {
				is RSSChannel -> xmlPullParser.mapRSSEvent(path, feedChannel)?.let { path = it }
				is RDFChannel -> xmlPullParser.mapRDFEvent(path, feedChannel)?.let { path = it }
				is AtomChannel -> xmlPullParser.mapAtomEvent(path, feedChannel)?.let { path = it }
			}

			eventType = xmlPullParser.next()
		}

		return when (feedChannel) {
			is RSSChannel -> feedChannel.toFeed()
			is AtomChannel -> feedChannel.toFeed()
			is RDFChannel -> feedChannel.toFeed()
		}
	}

	private fun XmlPullParser.mapRSSEvent(path: String, rssChannel: RSSChannel) : String? {
		val event = getRSSEvent(path)

		if (eventType == XmlPullParser.START_TAG) {
			rssChannel.mapStartEvent(event, this)
		}

		return if (event != UNSUPPORTED_RSS_ELEMENT) event.element else null
	}

	private fun XmlPullParser.mapRDFEvent(path: String, rdfChannel: RDFChannel) : String? {
		val event = getRDFEvent(path)

		if (eventType == XmlPullParser.START_TAG) {
			rdfChannel.mapStartEvent(event, this)
		}

		return if (event != UNSUPPORTED_RDF_ELEMENT) event.element else null
	}

	private fun XmlPullParser.mapAtomEvent(path: String, atomChannel: AtomChannel) : String? {
		val event = getAtomEvent(path)

		if (eventType == XmlPullParser.START_TAG) {
			atomChannel.mapStartEvent(event, this)
		}

		return if (event != UNSUPPORTED_ATOM_ELEMENT) event.element else null
	}

	private fun RSSChannel.mapStartEvent(
		element: RSSParserElement,
		xmlPullParser: XmlPullParser
	) {
		when (element.classify()) {
			ValueEvent -> mapEvent(eventType = element, attributes = xmlPullParser.attributes(), value = xmlPullParser.nextTextOrEmpty())
			AttributeEvent -> mapEvent(eventType = element, attributes = xmlPullParser.attributes())
			ContainerEvent -> mapEvent(eventType = element)
			UnsupportedEvent -> {
			}
		}
	}

	private fun RDFChannel.mapStartEvent(
		element: RDFParserElement,
		xmlPullParser: XmlPullParser
	) {
		when (element.classify()) {
			ValueEvent -> mapEvent(eventType = element, attributes = xmlPullParser.attributes(), value = xmlPullParser.nextTextOrEmpty())
			AttributeEvent -> mapEvent(eventType = element, attributes = xmlPullParser.attributes())
			ContainerEvent -> mapEvent(eventType = element)
			UnsupportedEvent -> {
			}
		}
	}

	private fun AtomChannel.mapStartEvent(
		element: AtomParserElement,
		xmlPullParser: XmlPullParser
	) {
		when (element.classify()) {
			ValueEvent -> mapEvent(eventType = element, attributes = xmlPullParser.attributes(), value = xmlPullParser.nextTextOrEmpty())
			AttributeEvent -> mapEvent(eventType = element, attributes = xmlPullParser.attributes())
			ContainerEvent -> mapEvent(eventType = element)
			UnsupportedEvent -> {
			}
		}
	}

	private fun XmlPullParser.attributes(): MutableMap<String, String> {
		val attributes: MutableMap<String, String> = mutableMapOf()
		for (i in 0 until attributeCount) {
			attributes[getAttributeName(i)] = getAttributeValue(i).trim()
		}
		return attributes
	}

	private fun XmlPullParser.getRSSEvent(path: String): RSSParserElement = try {
		if (name != null) RSSParserElement.from(name, path, depth) else UNSUPPORTED_RSS_ELEMENT
	} catch (e: IllegalArgumentException) {
		UNSUPPORTED_RSS_ELEMENT
	}

	private fun XmlPullParser.getRDFEvent(path: String): RDFParserElement = try {
		if (name != null) RDFParserElement.from(name, path, depth) else UNSUPPORTED_RDF_ELEMENT
	} catch (e: IllegalArgumentException) {
		UNSUPPORTED_RDF_ELEMENT
	}

	private fun XmlPullParser.getAtomEvent(path: String): AtomParserElement = try {
		if (name != null) AtomParserElement.from(name, path, depth) else UNSUPPORTED_ATOM_ELEMENT
	} catch (e: IllegalArgumentException) {
		UNSUPPORTED_ATOM_ELEMENT
	}

	private fun XmlPullParser.nextTextOrEmpty() = try {
		nextText().replace("\n", "").replace("\t", "").replace("\\s+".toRegex(), " ").trim().trimMargin()
	} catch (e: XmlPullParserException) {
		String()
	}
}
