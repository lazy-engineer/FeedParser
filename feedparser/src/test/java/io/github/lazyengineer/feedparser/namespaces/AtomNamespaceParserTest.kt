package io.github.lazyengineer.feedparser.namespaces

import io.github.lazyengineer.feedparser.FeedParser
import io.github.lazyengineer.feedparser.model.feed.RSSFeed
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.io.Reader
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.TimeZone

class AtomNamespaceParserTest {

	private lateinit var rssXML: String
	private lateinit var xmlPullParser: XmlPullParser
	private lateinit var feed: RSSFeed

	@Before
	fun setUp() {
		val rssXMLStream = this.javaClass.classLoader!!.getResourceAsStream("mock_atom_namespace_rss_sample.xml")
		val rssReader = BufferedReader(InputStreamReader(rssXMLStream))
		val stringBuilder = StringBuilder()

		var line: String?
		while (rssReader.readLine()
						.also { line = it } != null
		) {
			stringBuilder.append(line)
					.append('\n')
		}

		rssXML = stringBuilder.toString()

		val factory = XmlPullParserFactory.newInstance()
		xmlPullParser = factory.newPullParser()
		val reader: Reader = InputStreamReader(ByteArrayInputStream(rssXML.toByteArray()))
		xmlPullParser.setInput(reader)

		feed = FeedParser.parseFeed(rssXML, xmlPullParser) as RSSFeed
	}

	@Test
	fun `parse atom namespace mock xml string should return expected atom element`() {
		val calendar = GregorianCalendar(2005, Calendar.JULY, 31, 12, 29, 29) //2005-07-31T12:29:29Z
		calendar.timeZone = TimeZone.getTimeZone("GMT")

		feed.atomNamespace?.title?.value `should equal` "dive into mark"
		feed.atomNamespace?.title?.attributes?.type `should equal` "text"
		feed.atomNamespace?.subtitle?.value `should equal` "A <em>lot</em> of effort went into making this effortless"
		feed.atomNamespace?.subtitle?.attributes?.type `should equal` "html"
		feed.atomNamespace?.updated `should equal` Date(calendar.timeInMillis)
		feed.atomNamespace?.id `should equal` "tag:example.org,2003:3"
		feed.atomNamespace?.links?.get(0)?.attributes?.rel `should equal` "alternate"
		feed.atomNamespace?.links?.get(0)?.attributes?.type `should equal` "text/html"
		feed.atomNamespace?.links?.get(0)?.attributes?.hreflang `should equal` "en"
		feed.atomNamespace?.links?.get(0)?.attributes?.href `should equal` "http://example.org/"
		feed.atomNamespace?.links?.get(1)?.attributes?.rel `should equal` "self"
		feed.atomNamespace?.links?.get(1)?.attributes?.type `should equal` "application/atom+xml"
		feed.atomNamespace?.links?.get(1)?.attributes?.hreflang `should equal` null
		feed.atomNamespace?.links?.get(1)?.attributes?.href `should equal` "http://example.org/feed.atom"
		feed.atomNamespace?.rights `should equal` "Copyright (c) 2003, Mark Pilgrim"
		feed.atomNamespace?.generator?.value `should equal` "Example Toolkit"
		feed.atomNamespace?.generator?.attributes?.uri `should equal` "http://www.example.com/"
		feed.atomNamespace?.generator?.attributes?.version `should equal` "1.0"
	}

	@Test
	fun `parse atom namespace mock xml string should return expected atom element for first item`() {
		val updatedCalendar = GregorianCalendar(2005, Calendar.JULY, 31, 12, 29, 29) //2005-07-31T12:29:29Z
		updatedCalendar.timeZone = TimeZone.getTimeZone("GMT")
		val publishedCalendar = GregorianCalendar(2003, Calendar.DECEMBER, 13, 12, 29, 29) //2003-12-13T08:29:29-04:00
		publishedCalendar.timeZone = TimeZone.getTimeZone("GMT")

		feed.items[0].atomNamespace?.title?.value `should equal` "Atom draft-07 snapshot"
		feed.items[0].atomNamespace?.links?.get(0)?.attributes?.rel `should equal` "alternate"
		feed.items[0].atomNamespace?.links?.get(0)?.attributes?.type `should equal` "text/html"
		feed.items[0].atomNamespace?.links?.get(0)?.attributes?.href `should equal` "http://example.org/2005/04/02/atom"
		feed.items[0].atomNamespace?.links?.get(1)?.attributes?.rel `should equal` "enclosure"
		feed.items[0].atomNamespace?.links?.get(1)?.attributes?.type `should equal` "audio/mpeg"
		feed.items[0].atomNamespace?.links?.get(1)?.attributes?.length `should equal` 1337
		feed.items[0].atomNamespace?.links?.get(1)?.attributes?.href `should equal` "http://example.org/audio/ph34r_my_podcast.mp3"
		feed.items[0].atomNamespace?.id `should equal` "tag:example.org,2003:3.2397"
		feed.items[0].atomNamespace?.updated `should equal` Date(updatedCalendar.timeInMillis)
		feed.items[0].atomNamespace?.published `should equal` Date(publishedCalendar.timeInMillis)
		feed.items[0].atomNamespace?.authors?.get(0)?.name `should equal` "Mark Pilgrim"
		feed.items[0].atomNamespace?.authors?.get(0)?.email `should equal` "f8dy@example.com"
		feed.items[0].atomNamespace?.authors?.get(0)?.uri `should equal` "http://example.org/"
		feed.items[0].atomNamespace?.contributors?.get(0)?.name `should equal` "Sam Ruby"
		feed.items[0].atomNamespace?.contributors?.get(0)?.email `should equal` null
		feed.items[0].atomNamespace?.contributors?.get(0)?.uri `should equal` null
		feed.items[0].atomNamespace?.contributors?.get(1)?.name `should equal` "Joe Gregorio"
		feed.items[0].atomNamespace?.contributors?.get(1)?.email `should equal` null
		feed.items[0].atomNamespace?.contributors?.get(1)?.uri `should equal` null

		feed.items[0].atomNamespace?.content?.attributes?.type `should equal` "xhtml"
		feed.items[0].atomNamespace?.content?.attributes?.lang `should equal` "en"
		feed.items[0].atomNamespace?.content?.attributes?.base `should equal` "http://diveintomark.org/"
	}
}