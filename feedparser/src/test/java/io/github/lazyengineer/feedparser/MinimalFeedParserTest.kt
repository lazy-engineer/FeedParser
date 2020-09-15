package io.github.lazyengineer.feedparser

import io.github.lazyengineer.feedparser.model.feed.RSSFeed
import org.amshove.kluent.`should equal`
import org.junit.*
import org.junit.runner.*
import org.mockito.junit.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.io.Reader

@RunWith(MockitoJUnitRunner::class)
class MinimalFeedParserTest {

	private lateinit var rssXML: String
	private lateinit var xmlPullParser: XmlPullParser
	private lateinit var feed: RSSFeed

	@Before
	fun setUp() {
		val rssXMLStream = this.javaClass.classLoader!!.getResourceAsStream("mock_minimal_rss_sample.xml")
		val rssReader = BufferedReader(InputStreamReader(rssXMLStream))
		val stringBuilder = StringBuilder()

		var line: String?
		while (rssReader.readLine().also { line = it } != null) {
			stringBuilder.append(line).append('\n')
		}

		rssXML = stringBuilder.toString()

		val factory = XmlPullParserFactory.newInstance()
		xmlPullParser = factory.newPullParser()
		val reader: Reader = InputStreamReader(ByteArrayInputStream(rssXML.toByteArray()))
		xmlPullParser.setInput(reader)

		feed = FeedParser.parseFeed(rssXML, xmlPullParser) as RSSFeed
	}

	@Test
	fun `parse xml string should return expected channel title`() {
		feed.title `should equal` "W3Schools Home Page"
	}

	@Test
	fun `parse xml string should return expected channel link`() {
		feed.link `should equal` "https://www.w3schools.com"
	}

	@Test
	fun `parse xml string should return expected channel description`() {
		feed.description `should equal` "Free web building tutorials"
	}

	@Test
	fun `parse xml string should return expected channel items size of two`() {
		feed.items.size `should equal` 2
	}

	@Test
	fun `parse xml string should return expected channel items title of first item`() {
		feed.items[0].title `should equal` "RSS Tutorial"
	}

	@Test
	fun `parse xml string should return expected channel items link of first item`() {
		feed.items[0].link `should equal` "https://www.w3schools.com/xml/xml_rss.asp"
	}

	@Test
	fun `parse xml string should return expected channel items description of first item`() {
		feed.items[0].description `should equal` "New RSS tutorial on W3Schools"
	}

	@Test
	fun `parse xml string should return expected channel items title of second item`() {
		feed.items[1].title `should equal` "XML Tutorial"
	}

	@Test
	fun `parse xml string should return expected channel items link of second item`() {
		feed.items[1].link `should equal` "https://www.w3schools.com/xml"
	}

	@Test
	fun `parse xml string should return expected channel items description of second item`() {
		feed.items[1].description `should equal` "New XML tutorial on W3Schools"
	}

	@Test
	fun `parse xml string should return null or empty lists for all optional elements`() {
		feed.cloud `should equal` null
		feed.copyright `should equal` null
		feed.docs `should equal` null
		feed.generator `should equal` null
		feed.image `should equal` null
		feed.language `should equal` null
		feed.lastBuildDate `should equal` null
		feed.managingEditor `should equal` null
		feed.pubDate `should equal` null
		feed.rating `should equal` null
		feed.textInput `should equal` null
		feed.ttl `should equal` null
		feed.webMaster `should equal` null
		feed.categories `should equal` emptyList()
		feed.skipDays `should equal` emptyList()
		feed.skipHours `should equal` emptyList()

		feed.items[0].media `should equal` null
		feed.items[0].author `should equal` null
		feed.items[0].source `should equal` null
		feed.items[0].comments `should equal` null
		feed.items[0].guid `should equal` null
		feed.items[0].pubDate `should equal` null
		feed.items[0].categories `should equal` emptyList()

		feed.items[1].media `should equal` null
		feed.items[1].author `should equal` null
		feed.items[1].source `should equal` null
		feed.items[1].comments `should equal` null
		feed.items[1].guid `should equal` null
		feed.items[1].pubDate `should equal` null
		feed.items[1].categories `should equal` emptyList()
	}
}
