package io.github.lazyengineer.feedparser.namespaces

import io.github.lazyengineer.feedparser.FeedParser
import io.github.lazyengineer.feedparser.model.feed.ItemGUID
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

@RunWith(MockitoJUnitRunner::class)
class ITunesParserTest {

	private lateinit var rssXML: String
	private lateinit var xmlPullParser: XmlPullParser
	private lateinit var feed: RSSFeed

	@Before
	fun setUp() {
		val rssXMLStream = this.javaClass.classLoader!!.getResourceAsStream("mock_itunes_rss_sample.xml")
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
	fun `parse xml string should return channel itunes element`() {
		feed.iTunes?.author `should equal` "The Sunset Explorers"
		feed.iTunes?.explicit `should equal` "false"
		feed.iTunes?.image?.attributes?.href `should equal` "https://applehosted.podcasts.apple.com/hiking_treks/artwork.png"
		feed.iTunes?.owner?.name `should equal` "Sunset Explorers"
		feed.iTunes?.owner?.email `should equal` "mountainscape@icloud.com"
		feed.iTunes?.type `should equal` "serial"
		feed.iTunes?.categories!![0].attributes?.text `should equal` "Sports"
		feed.iTunes?.categories!![0].subcategory?.attributes?.text `should equal` "Wilderness"
	}

	@Test
	fun `parse xml string should return itunes element of fourth item`() {
		feed.items[3].iTunes?.explicit `should equal` "false"
		feed.items[3].iTunes?.image?.attributes?.href `should equal` "http://example.com/podcasts/everything/AllAboutEverything/Episode3.jpg"
		feed.items[3].iTunes?.episode `should equal` 2
		feed.items[3].iTunes?.season `should equal` 2
		feed.items[3].iTunes?.duration `should equal` 2434 * 1000L
		feed.items[3].iTunes?.episodeType `should equal` "full"
	}

	@Test
	fun `parse xml string should return expected fifth item`() {
		feed.items[4].title `should equal` "S02 EP01 Stawamus Chief"
		feed.items[4].description `should equal` "We tackle Stawamus Chief outside of Vancouver, BC and you should too!"
		feed.items[4].media?.url `should equal` "http://example.com/podcasts/everything/AllAboutEverythingEpisode4.mp3"
		feed.items[4].media?.size `should equal` 498537
		feed.items[4].media?.type `should equal` "audio/mpeg"
		feed.items[4].guid `should equal` ItemGUID(value = "aae20190516", isPermaLink = true)

		feed.items[4].iTunes?.explicit `should equal` "false"
		feed.items[4].iTunes?.episode `should equal` 1
		feed.items[4].iTunes?.season `should equal` 2
		feed.items[4].iTunes?.duration `should equal` (13 * 60 * 1000) + (24 * 1000).toLong() //"13:24"
		feed.items[4].iTunes?.episodeType `should equal` "full"
	}

	@Test
	fun `parse xml string should return  itunes items`() {
		feed.items.size `should equal` 9
	}
}
