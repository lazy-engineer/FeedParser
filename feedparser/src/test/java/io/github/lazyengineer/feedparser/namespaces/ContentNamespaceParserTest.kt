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

class ContentNamespaceParserTest {

	private lateinit var rssXML: String
	private lateinit var xmlPullParser: XmlPullParser
	private lateinit var feed: RSSFeed

	@Before
	fun setUp() {
		val rssXMLStream = this.javaClass.classLoader!!.getResourceAsStream("mock_content_rss_sample.xml")
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
	fun `parse content namespace mock xml string should return expected content encoded`() {
		feed.items[0].contentNamespace?.encoded `should equal` "<p>What a <em>beautiful</em> day!</p>"
	}
}
