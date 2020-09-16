package io.github.lazyengineer.feedparser.namespaces

import io.github.lazyengineer.feedparser.FeedParser
import io.github.lazyengineer.feedparser.model.feed.RSSFeed
import io.github.lazyengineer.feedparser.model.namespace.syndication.SyndicationUpdatePeriod
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

class SyndicationNamespaceParserTest {

	private lateinit var rssXML: String
	private lateinit var xmlPullParser: XmlPullParser
	private lateinit var feed: RSSFeed

	@Before
	fun setUp() {
		val rssXMLStream = this.javaClass.classLoader!!.getResourceAsStream("mock_syndication_rss_sample.xml")
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
	fun `parse syndication namespace mock xml string should return expected syndication update frequency`() {
		feed.syndicationNamespace?.updateFrequency `should equal` 2
	}

	@Test
	fun `parse syndication namespace mock xml string should return expected syndication update base`() {
		val calendar = GregorianCalendar(2000, Calendar.JANUARY, 1, 0, 0, 0) //2000-01-01T12:00+00:00
		calendar.timeZone = TimeZone.getTimeZone("GMT")

		feed.syndicationNamespace?.updateBase `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse syndication namespace mock xml string should return expected syndication update period`() {
		feed.syndicationNamespace?.updatePeriod `should equal` SyndicationUpdatePeriod.HOURLY
	}
}