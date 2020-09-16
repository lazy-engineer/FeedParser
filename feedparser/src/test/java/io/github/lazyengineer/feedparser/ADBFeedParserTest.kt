package io.github.lazyengineer.feedparser

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
import java.util.Date

@RunWith(MockitoJUnitRunner::class)
class ADBFeedParserTest {

	private lateinit var adbXML: String
	private lateinit var xmlPullParser: XmlPullParser
	private lateinit var feed: RSSFeed

	@Before
	fun setUp() {
		val adbRssXMLStream =
			this.javaClass.classLoader!!.getResourceAsStream("mock_android_developers_backstage_feed.xml")
		val adbReader = BufferedReader(InputStreamReader(adbRssXMLStream))
		val adbStringBuilder = StringBuilder()

		var adbLine: String?
		while (adbReader.readLine()
						.also { adbLine = it } != null
		) {
			adbStringBuilder.append(adbLine)
					.append('\n')
		}

		adbXML = adbStringBuilder.toString()

		val factory = XmlPullParserFactory.newInstance()
		xmlPullParser = factory.newPullParser()
		val reader: Reader = InputStreamReader(ByteArrayInputStream(adbXML.toByteArray()))
		xmlPullParser.setInput(reader)

		feed = FeedParser.parseFeed(adbXML, xmlPullParser) as RSSFeed
	}

	@Test
	fun `parse adb xml string should return Android Developers Backstage as channel title`() {
		feed.title `should equal` "Android Developers Backstage"
	}

	@Test
	fun `parse adb xml string should 143 podcast episodes`() {
		feed.items.size `should equal` 143
	}

	@Test
	fun `parse adb xml string should return expected channel language`() {
		feed.language `should equal` "en"
	}

	@Test
	fun `parse adb xml string should return expected channel managing editor`() {
		feed.managingEditor `should equal` "noreply@blogger.com (Android Developers)"
	}

	@Test
	fun `parse adb xml string should return expected channel last build date`() {
		feed.lastBuildDate `should equal` Date("Sat, 04 Jul 2020 03:35:24 PDT")
	}

	@Test
	fun `parse adb xml string should return expected channel generator`() {
		feed.generator `should equal` "Blogger http://www.blogger.com"
	}

	@Test
	fun `parse adb xml string should return expected channel category`() {
		feed.categories `should equal` emptyList()
	}

	@Test
	fun `parse adb xml string should return expected channel cloud`() {
		feed.cloud `should equal` null
	}

	@Test
	fun `parse adb xml string should return expected channel docs`() {
		feed.docs `should equal` null
	}

	@Test
	fun `parse adb xml string should return expected channel copyright`() {
		feed.copyright `should equal` null
	}

	@Test
	fun `parse adb xml string should return expected channel image`() {
		feed.image `should equal` null
	}

	@Test
	fun `parse adb xml string should return expected channel publication date`() {
		feed.pubDate `should equal` null
	}

	@Test
	fun `parse adb xml string should return expected channel rating`() {
		feed.rating `should equal` null
	}

	@Test
	fun `parse adb xml string should return expected channel skip days`() {
		feed.skipDays `should equal` emptyList()
	}

	@Test
	fun `parse adb xml string should return expected channel skip hours`() {
		feed.skipHours `should equal` emptyList()
	}

	@Test
	fun `parse adb xml string should return expected channel text input`() {
		feed.textInput `should equal` null
	}

	@Test
	fun `parse adb xml string should return expected channel ttl`() {
		feed.ttl `should equal` null
	}

	@Test
	fun `parse adb xml string should return expected channel web master`() {
		feed.webMaster `should equal` null
	}

	@Test
	fun `parse adb xml string should return expected channel iTunes owner name and email`() {
		feed.iTunes?.owner?.name `should equal` "Android Developers"
		feed.iTunes?.owner?.email `should equal` "developers@android.com"
	}

	@Test
	fun `parse adb xml string should return expected channel iTunes author`() {
		feed.iTunes?.author `should equal` "Android Developers"
	}

	@Test
	fun `parse adb xml string should return expected channel iTunes explicit tag`() {
		feed.iTunes?.explicit `should equal` "no"
	}

	@Test
	fun `parse adb xml string should return expected channel iTunes image url`() {
		feed.iTunes?.image?.attributes?.href `should equal` "http://storage.googleapis.com/androiddevelopers/android_developers_backstage/adb.png"
	}

	@Test
	fun `parse adb xml string should return expected channel iTunes keywords`() {
		feed.iTunes?.keywords `should equal` "Android,programming,development,software,mobile,mobile,programming"
	}

	@Test
	fun `parse adb xml string should return expected channel iTunes subtitle`() {
		feed.iTunes?.subtitle `should equal` "Android developers talking to Android developers about Android development"
	}

	@Test
	fun `parse adb xml string should return expected channel iTunes summary`() {
		feed.iTunes?.summary `should equal` "Android Backstage, a podcast by and for Android developers. Hosted by developers from the Android engineering team, this show covers topics of interest to Android programmers, with in-depth discussions and interviews with engineers on the Android team at Google."
	}

	@Test
	fun `parse adb xml string should return expected channel iTunes categories and subcategories`() {
		feed.iTunes?.categories!![0].attributes?.text `should equal` "Technology"
		feed.iTunes?.categories!![0].subcategory?.attributes?.text `should equal` "Software How-To"
		feed.iTunes?.categories!![1].attributes?.text `should equal` "Technology"
		feed.iTunes?.categories!![1].subcategory?.attributes?.text `should equal` "Tech News"
	}

	@Test
	fun `parse adb xml string should return expected media url of the latest episode`() {
		feed.items[0].media?.url `should equal` "https://storage.googleapis.com/androiddevelopers/android_developers_backstage/ADB%20143%20Shhh%20Private.mp3"
	}

	@Test
	fun `parse adb xml string should return expected media type of the latest episode`() {
		feed.items[0].media?.type `should equal` "audio/mpeg"
	}

	@Test
	fun `parse adb xml string should return expected media length of the latest episode`() {
		feed.items[0].media?.size `should equal` 0
	}

	@Test
	fun `parse adb xml string should return expected author of the latest episode`() {
		feed.items[0].author `should equal` "developers@android.com (Android Developers)"
	}

	@Test
	fun `parse adb xml string should return expected source of the latest episode`() {
		feed.items[0].source `should equal` null
	}

	@Test
	fun `parse adb xml string should return expected category of the latest episode`() {
		feed.items[0].categories `should equal` emptyList()
	}

	@Test
	fun `parse adb xml string should return expected comments of the latest episode`() {
		feed.items[0].comments `should equal` null
	}

	@Test
	fun `parse adb xml string should return expected guid of the latest episode`() {
		feed.items[0].guid `should equal` ItemGUID(
				value = "tag:blogger.com,1999:blog-1052108547998082465.post-3561544422598285224",
				isPermaLink = false
		)
	}

	@Test
	fun `parse adb xml string should return expected publish date of the latest episode`() {
		feed.items[0].pubDate `should equal` Date("Tue, 30 Jun 2020 13:00:45 PDT")
	}

	@Test
	fun `parse adb xml string should return expected iTunes explicit tag of the latest episode`() {
		feed.items[0].iTunes?.explicit `should equal` "no"
	}

	@Test
	fun `parse adb xml string should return expected iTunes subtitle of the latest episode`() {
		feed.items[0].iTunes?.subtitle `should equal` "Tor, Romain, Sara, Philip, and a little tiny Chet top-right In this episode, Tor, Chet, and Romain talk with Sara N-Marandi and Philip Moltmann from the Android framework team about some of the new permissions changes in Android 11. We talk about why thes"
	}

	@Test
	fun `parse adb xml string should return expected iTunes author of the latest episode`() {
		feed.items[0].iTunes?.author `should equal` "Android Developers"
	}

	@Test
	fun `parse adb xml string should return expected iTunes keywords of the latest episode`() {
		feed.items[0].iTunes?.keywords `should equal` "Android,programming,development,software,mobile,mobile,programming"
	}

	@Test
	fun `parse adb xml string should return expected iTunes summary of the latest episode`() {
		feed.items[0].iTunes?.summary `should equal` "Tor, Romain, Sara, Philip, and a little tiny Chet top-right In this episode, Tor, Chet, and Romain talk with Sara N-Marandi and Philip Moltmann from the Android framework team about some of the new permissions changes in Android 11. We talk about why these changes were made, how to use them correctly in your code, and how things actually work on the inside. Subscribe to the&nbsp;podcast feed&nbsp;or download the&nbsp;audio file&nbsp;directly. LinksDeveloper Guide: Request App PermissionsVideo:&nbsp;Developing with the latest privacy changes in Android 11Video:&nbsp;All things privacy in Android 11 Chet:&nbsp;@chethaase Tor:&nbsp;@tornorbye Romain:&nbsp;@romainguy Thanks to continued tolerance and support by our audio engineer, Dustin Elm."
	}
}