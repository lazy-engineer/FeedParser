package io.github.lazyengineer.feedparser

import io.github.lazyengineer.feedparser.model.feed.RSSFeed
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.io.Reader
import java.text.SimpleDateFormat

@RunWith(MockitoJUnitRunner::class)
class DateParserTest {

	private lateinit var rssXML: String
	private lateinit var xmlPullParser: XmlPullParser
	private lateinit var feed: RSSFeed
	private val expectedDateFormat = SimpleDateFormat("MMM d, yyyy HH:mm:ss")

	@Before
	fun setUp() {
		val rssXMLStream = this.javaClass.classLoader!!.getResourceAsStream("mock_date_rss_sample.xml")
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
	fun `parse first item pub date should expected date`() {
		expectedDateFormat.format(feed.items[0].pubDate!!) `should equal` "Jan 8, 2019 02:15:00"
	}

	@Test
	fun `parse second item pub date should expected date`() {
		expectedDateFormat.format(feed.items[1].pubDate!!) `should equal` "Mai 7, 2019 14:00:00"
	}

	@Test
	fun `parse third item pub date should expected date`() {
		expectedDateFormat.format(feed.items[2].pubDate!!) `should equal` "Apr 30, 2019 20:00:00"
	}

	@Test
	fun `parse forth item pub date should expected date`() {
		expectedDateFormat.format(feed.items[3].pubDate!!) `should equal` "Mai 23, 2019 11:00:00"
	}

	@Test
	fun `parse fifth item pub date should expected date`() {
		expectedDateFormat.format(feed.items[4].pubDate!!) `should equal` "Feb 16, 2019 08:00:00"
	}

	@Test
	fun `parse sixth item pub date should expected date`() {
		expectedDateFormat.format(feed.items[5].pubDate!!) `should equal` "Nov 27, 2018 02:15:00"
	}

	@Test
	fun `parse seventh item pub date should expected date`() {
		expectedDateFormat.format(feed.items[6].pubDate!!) `should equal` "Okt 23, 2018 03:15:00"
	}

	@Test
	fun `parse eight item pub date should expected date`() {
		expectedDateFormat.format(feed.items[7].pubDate!!) `should equal` "Sep 18, 2018 03:15:00"
	}

	@Test
	fun `parse ninth item pub date should expected date`() {
		expectedDateFormat.format(feed.items[8].pubDate!!) `should equal` "Aug 14, 2018 03:15:00"
	}
}