package io.github.lazyengineer.feedparser.rss

import io.github.lazyengineer.feedparser.FeedParser
import io.github.lazyengineer.feedparser.model.DateUtil
import io.github.lazyengineer.feedparser.model.feed.RSSFeed
import io.github.lazyengineer.feedparser.model.rss.RSSChannelSkipDay.SATURDAY
import io.github.lazyengineer.feedparser.model.rss.RSSChannelSkipDay.SUNDAY
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
class RSSParserTest {

	private lateinit var rssXML: String
	private lateinit var xmlPullParser: XmlPullParser
	private lateinit var feed: RSSFeed

	@Before
	fun setUp() {
		val rssXMLStream = this.javaClass.classLoader!!.getResourceAsStream("mock_rss_sample.xml")
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
		feed.title `should equal` "GoUpstate.com News Headlines"
		feed.link `should equal` "http://www.goupstate.com/"
		feed.description `should equal` "The latest news from GoUpstate.com, a Spartanburg Herald-Journal Web site."
		feed.language `should equal` "en-us"
		feed.copyright `should equal` "Copyright 2002, Spartanburg Herald-Journal"
		feed.managingEditor `should equal` "geo@herald.com (George Matesky)"
		feed.webMaster `should equal` "betty@herald.com (Betty Guernsey)"
		feed.pubDate `should equal` DateUtil.parseDateString("Sat, 07 Sep 2002 00:00:01 GMT")
		feed.lastBuildDate `should equal` DateUtil.parseDateString("Sat, 07 Sep 2002 09:42:31 GMT")
		feed.categories `should equal` listOf("Newspapers")
		feed.generator `should equal` "MightyInHouse Content System v2.3"
		feed.docs `should equal` "https://www.rssboard.org/rss-specification"
		feed.rating `should equal` "(PICS-1.1 \"http://www.rsac.org/ratingsv01.html\" l by \"webmaster@example.com\" on \"2007.01.29T10:09-0800\" r (n 0 s 0 v 0 l 0))"
		feed.ttl `should equal` 60
	}

	@Test
	fun `parse xml string should return expected channel cloud element`() {
		feed.cloud?.domain `should equal` "radio.xmlstoragesystem.com"
		feed.cloud?.port `should equal` 80
		feed.cloud?.path `should equal` "/RPC2"
		feed.cloud?.registerProcedure `should equal` "xmlStorageSystem.rssPleaseNotify"
		feed.cloud?.protocol `should equal` "xml-rpc"
	}

	@Test
	fun `parse xml string should return expected channel skip days and hours`() {
		feed.skipDays `should equal` listOf(SATURDAY, SUNDAY)
		feed.skipHours `should equal` listOf(0, 1, 2, 22, 23)
	}

	@Test
	fun `parse xml string should return expected channel text input element`() {
		feed.textInput?.title `should equal` "TextInput Inquiry"
		feed.textInput?.description `should equal` "Your aggregator supports the textInput element. What software are you using?"
		feed.textInput?.name `should equal` "query"
		feed.textInput?.link `should equal` "http://www.cadenhead.org/textinput.php"
	}

	@Test
	fun `parse xml string should return expected channel image element`() {
		feed.image?.url `should equal` "http://www.feedforall.com/feedforall-icon.gif"
		feed.image?.title `should equal` "FeedForAll"
		feed.image?.link `should equal` "http://www.feedforall.com/blog.htm"
		feed.image?.description `should equal` "FeedForAll - RSS Software"
		feed.image?.width `should equal` 42
		feed.image?.height `should equal` 39
	}

	@Test
	fun `parse xml string should return four channel item elements`() {
		feed.items.size `should equal` 4
	}

	@Test
	fun `parse xml string should return expected channel item element`() {
		feed.items[0].title `should equal` "RSS Tutorial"
		feed.items[0].link `should equal` "https://www.w3schools.com/xml/xml_rss.asp"
		feed.items[0].description `should equal` "New RSS tutorial on W3Schools"
		feed.items[0].author `should equal` "lawyer@boyer.net (Lawyer Boyer)"
		feed.items[0].categories!![0].value `should equal` "Grateful Dead"
		feed.items[0].categories!![0].domain `should equal` null
		feed.items[0].categories!![1].domain `should equal` "http://www.fool.com/cusips"
		feed.items[0].categories!![1].value `should equal` "MSFT"
		feed.items[0].comments `should equal` "http://ekzemplo.com/entry/4403/comments"
		feed.items[0].media?.url `should equal` "http://www.scripting.com/mp3s/weatherReportSuite.mp3"
		feed.items[0].media?.size `should equal` 12216320
		feed.items[0].media?.type `should equal` "audio/mpeg"
		feed.items[0].guid?.value `should equal` "http://inessential.com/2002/09/01.php#a2"
		feed.items[0].guid?.isPermaLink `should equal` true
		feed.items[0].pubDate `should equal` DateUtil.parseDateString("Sun, 19 May 2002 15:21:36 GMT")
		feed.items[0].source?.value `should equal` "Tomalak's Realm"
		feed.items[0].source?.url `should equal` "http://static.userland.com/tomalak/links2.xml"
	}

	@Test
	fun `parse xml string should return expected channel NASA image item element`() {
		feed.items[1].title `should equal` "Perseverance Rover Mated to Its Atlas V Rocket"
		feed.items[1].link `should equal` "http://www.nasa.gov/image-feature/perseverance-rover-mated-to-its-atlas-v-rocket"
		feed.items[1].description `should equal` "In this image, NASA's Mars 2020 Perseverance rover waits to be lifted onto its Atlas V launch vehicle at the Cape Canaveral Air Force Station in Florida on July 7, 2020."
		feed.items[1].media?.url `should equal` "http://www.nasa.gov/sites/default/files/thumbnails/image/pia23984-16.jpg"
		feed.items[1].media?.size `should equal` 498003
		feed.items[1].media?.type `should equal` "image/jpeg"
		feed.items[1].guid?.value `should equal` "http://www.nasa.gov/image-feature/perseverance-rover-mated-to-its-atlas-v-rocket"
		feed.items[1].guid?.isPermaLink `should equal` false
		feed.items[1].pubDate `should equal` DateUtil.parseDateString("Tue, 14 Jul 2020 10:19 EDT")
		feed.items[1].source?.value `should equal` "NASA Image of the Day"
		feed.items[1].source?.url `should equal` "http://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss"
	}

	@Test
	fun `parse xml string should return expected channel NASA audio item element`() {
		feed.items[2].title `should equal` "This Week @NASA, July 10, 2020"
		feed.items[2].link `should equal` "http://www.nasa.gov/mediacast/this-week-nasa-july-10-2020"
		feed.items[2].description `should equal` "Perseverance Integrated with Launch Vehicle and more ..."
		feed.items[2].media?.url `should equal` "http://www.nasa.gov/sites/default/files/atoms/audio/twan_7_10_2020.mp3"
		feed.items[2].media?.size `should equal` 4888337
		feed.items[2].media?.type `should equal` "audio/mpeg"
		feed.items[2].guid?.value `should equal` "http://www.nasa.gov/mediacast/this-week-nasa-july-10-2020"
		feed.items[2].guid?.isPermaLink `should equal` false
		feed.items[2].pubDate `should equal` DateUtil.parseDateString("Fri, 10 Jul 2020 15:27 EDT")
		feed.items[2].source?.value `should equal` "NASACast: This Week @NASA Audio"
		feed.items[2].source?.url `should equal` "http://www.nasa.gov/rss/dyn/TWAN_podcast.rss"
	}

	@Test
	fun `parse xml string should return expected channel NASA video item element`() {
		feed.items[3].title `should equal` "This Week @NASA, July 10, 2020"
		feed.items[3].link `should equal` "http://www.nasa.gov/mediacast/this-week-nasa-july-10-2020"
		feed.items[3].description `should equal` "Perseverance Integrated with Launch Vehicle and more ..."
		feed.items[3].media?.url `should equal` "http://www.nasa.gov/sites/default/files/atoms/video/twan_7_10_2020_final.mp4"
		feed.items[3].media?.size `should equal` 216692678
		feed.items[3].media?.type `should equal` "video/mp4"
		feed.items[3].guid?.value `should equal` "http://www.nasa.gov/mediacast/this-week-nasa-july-10-2020"
		feed.items[3].guid?.isPermaLink `should equal` false
		feed.items[3].pubDate `should equal` DateUtil.parseDateString("Fri, 10 Jul 2020 15:27 EDT")
		feed.items[3].source?.value `should equal` "NASACast: This Week @ NASA Video"
		feed.items[3].source?.url `should equal` "http://www.nasa.gov/rss/dyn/TWAN_vodcast.rss"
	}
}
