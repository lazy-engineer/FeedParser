package io.github.lazyengineer.feedparser.atom

import io.github.lazyengineer.feedparser.FeedParser
import io.github.lazyengineer.feedparser.model.feed.AtomFeed
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

class AtomFeedParserTest {

	private lateinit var rssXML: String
	private lateinit var xmlPullParser: XmlPullParser
	private lateinit var feed: AtomFeed

	@Before
	fun setUp() {
		val rssXMLStream = this.javaClass.classLoader!!.getResourceAsStream("mock_atom_feed_sample.xml")
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

		feed = FeedParser.parseFeed(rssXML, xmlPullParser) as AtomFeed
	}

	@Test
	fun `parse atom mock xml string should return expected atom title`() {
		feed.title?.value `should equal` "dive into mark"
		feed.title?.attributes?.type `should equal` "text"
	}

	@Test
	fun `parse atom mock xml string should return expected atom subtitle`() {
		feed.subtitle?.value `should equal` "A <em>lot</em> of effort went into making this effortless"
		feed.subtitle?.attributes?.type `should equal` "html"
	}

	@Test
	fun `parse atom mock xml string should return expected atom element`() {
		val calendar = GregorianCalendar(2005, Calendar.JULY, 31, 12, 29, 29) //2005-07-31T12:29:29Z
		calendar.timeZone = TimeZone.getTimeZone("GMT")

		feed.title?.value `should equal` "dive into mark"
		feed.title?.attributes?.type `should equal` "text"
		feed.subtitle?.value `should equal` "A <em>lot</em> of effort went into making this effortless"
		feed.subtitle?.attributes?.type `should equal` "html"
		feed.updated `should equal` Date(calendar.timeInMillis)
		feed.id `should equal` "tag:example.org,2003:3"
		feed.links[0].attributes?.rel `should equal` "alternate"
		feed.links[0].attributes?.type `should equal` "text/html"
		feed.links[0].attributes?.hreflang `should equal` "en"
		feed.links[0].attributes?.href `should equal` "http://example.org/"
		feed.links[1].attributes?.rel `should equal` "self"
		feed.links[1].attributes?.type `should equal` "application/atom+xml"
		feed.links[1].attributes?.hreflang `should equal` null
		feed.links[1].attributes?.href `should equal` "http://example.org/feed.atom"
		feed.rights `should equal` "Copyright (c) 2003, Mark Pilgrim"
		feed.generator?.value `should equal` "Example Toolkit"
		feed.generator?.attributes?.uri `should equal` "http://www.example.com/"
		feed.generator?.attributes?.version `should equal` "1.0"
	}

	@Test
	fun `parse atom mock xml string should return expected atom element for first item`() {
		val updatedCalendar = GregorianCalendar(2005, Calendar.JULY, 31, 12, 29, 29) //2005-07-31T12:29:29Z
		updatedCalendar.timeZone = TimeZone.getTimeZone("GMT")
		val publishedCalendar = GregorianCalendar(2003, Calendar.DECEMBER, 13, 12, 29, 29) //2003-12-13T08:29:29-04:00
		publishedCalendar.timeZone = TimeZone.getTimeZone("GMT")

		feed.entries[0].title?.value `should equal` "Atom draft-07 snapshot"
		feed.entries[0].links[0].attributes?.rel `should equal` "alternate"
		feed.entries[0].links[0].attributes?.type `should equal` "text/html"
		feed.entries[0].links[0].attributes?.href `should equal` "http://example.org/2005/04/02/atom"
		feed.entries[0].links[1].attributes?.rel `should equal` "enclosure"
		feed.entries[0].links[1].attributes?.type `should equal` "audio/mpeg"
		feed.entries[0].links[1].attributes?.length `should equal` 1337
		feed.entries[0].links[1].attributes?.href `should equal` "http://example.org/audio/ph34r_my_podcast.mp3"
		feed.entries[0].id `should equal` "tag:example.org,2003:3.2397"
		feed.entries[0].updated `should equal` Date(updatedCalendar.timeInMillis)
		feed.entries[0].published `should equal` Date(publishedCalendar.timeInMillis)
		feed.entries[0].authors[0].name `should equal` "Mark Pilgrim"
		feed.entries[0].authors[0].email `should equal` "f8dy@example.com"
		feed.entries[0].authors[0].uri `should equal` "http://example.org/"
		feed.entries[0].contributors[0].name `should equal` "Sam Ruby"
		feed.entries[0].contributors[0].email `should equal` null
		feed.entries[0].contributors[0].uri `should equal` null
		feed.entries[0].contributors[1].name `should equal` "Joe Gregorio"
		feed.entries[0].contributors[1].email `should equal` null
		feed.entries[0].contributors[1].uri `should equal` null

		feed.entries[0].content?.attributes?.type `should equal` "xhtml"
		feed.entries[0].content?.attributes?.lang `should equal` "en"
		feed.entries[0].content?.attributes?.base `should equal` "http://diveintomark.org/"
	}

	@Test
	fun `parse atom mock xml string should return expected media rating for first item`() {
		feed.entries[1].mediaNamespace?.rating?.value `should equal` "adult"
		feed.entries[1].mediaNamespace?.rating?.attributes?.scheme `should equal` "urn:simple"
	}

	@Test
	fun `parse atom mock xml string should return expected media rating for second item`() {
		feed.entries[2].mediaNamespace?.rating?.value `should equal` "nonadult"
		feed.entries[2].mediaNamespace?.rating?.attributes?.scheme `should equal` null
	}

	@Test
	fun `parse atom mock xml string should return expected media content attributes for first item`() {
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.url `should equal` "http://www.foo.com/movie.mov"
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.fileSize `should equal` 12216320
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.type `should equal` "video/quicktime"
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.medium `should equal` "video"
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.isDefault `should equal` true
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.expression `should equal` "full"
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.bitrate `should equal` 128
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.framerate `should equal` 25.toDouble()
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.samplingrate `should equal` 44.1
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.channels `should equal` 2
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.duration `should equal` 185
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.height `should equal` 200
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.width `should equal` 300
		feed.entries[1].mediaNamespace?.contents?.get(0)?.attributes?.lang `should equal` "en"
	}

	@Test
	fun `parse atom mock xml string should return expected media content attributes for second item`() {
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.url `should equal` "http://www.foo.com/trailer.mov"
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.fileSize `should equal` 12216320
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.type `should equal` "video/quicktime"
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.expression `should equal` "sample"
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.medium `should equal` null
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.isDefault `should equal` null
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.bitrate `should equal` null
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.framerate `should equal` null
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.samplingrate `should equal` null
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.channels `should equal` null
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.duration `should equal` null
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.height `should equal` null
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.width `should equal` null
		feed.entries[2].mediaNamespace?.contents?.get(0)?.attributes?.lang `should equal` null
	}

	@Test
	fun `parse atom mock xml string should return expected media title for first item`() {
		feed.entries[1].mediaNamespace?.title?.value `should equal` "The Judy's -- The Moo Song"
		feed.entries[1].mediaNamespace?.title?.attributes?.type `should equal` "plain"
	}

	@Test
	fun `parse atom mock xml string should return expected media description for first item`() {
		feed.entries[1].mediaNamespace?.description?.value `should equal` "This was some really bizarre band I listened to as a young lad."
		feed.entries[1].mediaNamespace?.description?.attributes?.type `should equal` "plain"
	}

	@Test
	fun `parse atom mock xml string should return expected media keywords for first item`() {
		feed.entries[1].mediaNamespace?.keywords `should equal` listOf("kitty", "cat", "big dog", "yarn", "fluffy")
	}

	@Test
	fun `parse atom mock xml string should return expected media thumbnails for first item`() {
		feed.entries[1].mediaNamespace?.thumbnails?.get(0)?.attributes?.url `should equal` "http://www.foo.com/keyframe.jpg"
		feed.entries[1].mediaNamespace?.thumbnails?.get(0)?.attributes?.width `should equal` 75
		feed.entries[1].mediaNamespace?.thumbnails?.get(0)?.attributes?.height `should equal` 50
		feed.entries[1].mediaNamespace?.thumbnails?.get(0)?.attributes?.time `should equal` (12 * 60 * 60 * 1000) + (5 * 60 * 1000) + (1 * 1000) + 123L
	}

	@Test
	fun `parse atom mock xml string should return expected media category for first item`() {
		feed.entries[1].mediaNamespace?.category?.value `should equal` "Arts/Movies/Titles/A/Ace_Ventura_Series/Ace_Ventura_ -_Pet_Detective"
		feed.entries[1].mediaNamespace?.category?.attributes?.scheme `should equal` "http://dmoz.org"
		feed.entries[1].mediaNamespace?.category?.attributes?.label `should equal` "Ace Ventura - Pet Detective"
	}

	@Test
	fun `parse atom mock xml string should return expected media credits for first item`() {
		feed.entries[1].mediaNamespace?.credits?.get(0)?.value `should equal` "copyright holder of the entity"
		feed.entries[1].mediaNamespace?.credits?.get(0)?.attributes?.scheme `should equal` "urn:yvs"
		feed.entries[1].mediaNamespace?.credits?.get(0)?.attributes?.role `should equal` "owner"
	}

	@Test
	fun `parse atom mock xml string should return expected media copyright for first item`() {
		feed.entries[1].mediaNamespace?.copyright?.value `should equal` "2005 FooBar Media"
		feed.entries[1].mediaNamespace?.copyright?.attributes?.url `should equal` "http://blah.com/additional-info.html"
	}

	@Test
	fun `parse atom mock xml string should return expected media text for first item`() {
		feed.entries[1].mediaNamespace?.text?.value `should equal` "Oh, say, can you see"
		feed.entries[1].mediaNamespace?.text?.attributes?.type `should equal` "plain"
		feed.entries[1].mediaNamespace?.text?.attributes?.lang `should equal` "en"
		feed.entries[1].mediaNamespace?.text?.attributes?.start `should equal` 3 * 1000L //00:00:03.000
		feed.entries[1].mediaNamespace?.text?.attributes?.end `should equal` 10 * 1000L //00:00:10.000
	}

	@Test
	fun `parse atom mock xml string should return expected media restriction for first item`() {
		feed.entries[1].mediaNamespace?.restriction?.value `should equal` "au us"
		feed.entries[1].mediaNamespace?.restriction?.attributes?.type `should equal` "country"
		feed.entries[1].mediaNamespace?.restriction?.attributes?.relationship `should equal` "allow"
	}

	@Test
	fun `parse atom mock xml string should return expected media status for first item`() {
		feed.entries[1].mediaNamespace?.status?.attributes?.state `should equal` "blocked"
		feed.entries[1].mediaNamespace?.status?.attributes?.reason `should equal` "http://www.reasonforblocking.com"
	}

	@Test
	fun `parse atom mock xml string should return expected media price for first item`() {
		feed.entries[1].mediaNamespace?.price?.get(0)?.attributes?.type `should equal` "subscription"
		feed.entries[1].mediaNamespace?.price?.get(0)?.attributes?.info `should equal` "http://www.dummy.jp/subscription_info"
		feed.entries[1].mediaNamespace?.price?.get(0)?.attributes?.price `should equal` 19.99
		feed.entries[1].mediaNamespace?.price?.get(0)?.attributes?.currency `should equal` "EUR"
	}

	@Test
	fun `parse atom mock xml string should return expected media rights for first item`() {
		feed.entries[1].mediaNamespace?.rights?.attributes?.status `should equal` "official"
	}

	@Test
	fun `parse atom mock xml string should return expected media content player for third item`() {
		feed.entries[3].mediaNamespace?.player?.attributes?.url `should equal` "http://www.foo.com/player?id=1111"
		feed.entries[3].mediaNamespace?.player?.attributes?.height `should equal` 200
		feed.entries[3].mediaNamespace?.player?.attributes?.width `should equal` 400
	}

	@Test
	fun `parse atom mock xml string should return expected media content hash for third item`() {
		feed.entries[3].mediaNamespace?.hash?.value `should equal` "dfdec888b72151965a34b4b59031290a"
		feed.entries[3].mediaNamespace?.hash?.attributes?.algo `should equal` "md5"
	}

	@Test
	fun `parse atom mock xml string should return expected media content credits for third item`() {
		feed.entries[3].mediaNamespace?.contents?.get(0)?.credits?.get(0)?.value `should equal` "producer's name"
		feed.entries[3].mediaNamespace?.contents?.get(0)?.credits?.get(0)?.attributes?.role `should equal` "producer"
		feed.entries[3].mediaNamespace?.contents?.get(0)?.credits?.get(0)?.attributes?.scheme `should equal` null
		feed.entries[3].mediaNamespace?.contents?.get(0)?.credits?.get(1)?.value `should equal` "artist's name"
		feed.entries[3].mediaNamespace?.contents?.get(0)?.credits?.get(1)?.attributes?.role `should equal` "artist"
		feed.entries[3].mediaNamespace?.contents?.get(0)?.credits?.get(1)?.attributes?.scheme `should equal` null
	}

	@Test
	fun `parse atom mock xml string should return expected media content category for third item`() {
		feed.entries[3].mediaNamespace?.contents?.get(0)?.category?.value `should equal` "music/artistname/album/song"
		feed.entries[3].mediaNamespace?.contents?.get(0)?.category?.attributes?.scheme `should equal` "http://blah.com/scheme"
		feed.entries[3].mediaNamespace?.contents?.get(0)?.category?.attributes?.label `should equal` null
	}

	@Test
	fun `parse atom mock xml string should return expected media content text for third item`() {
		feed.entries[3].mediaNamespace?.text?.value `should equal` "Oh, say, can you see, by the dawn's early light"
		feed.entries[3].mediaNamespace?.text?.attributes?.type `should equal` "plain"
		feed.entries[3].mediaNamespace?.text?.attributes?.end `should equal` null
		feed.entries[3].mediaNamespace?.text?.attributes?.start `should equal` null
		feed.entries[3].mediaNamespace?.text?.attributes?.lang `should equal` null
	}

	@Test
	fun `parse atom mock xml string should return expected media content rating for third item`() {
		feed.entries[3].mediaNamespace?.contents?.get(0)?.rating?.value `should equal` "nonadult"
		feed.entries[3].mediaNamespace?.contents?.get(0)?.rating?.attributes?.scheme `should equal` null
	}

	@Test
	fun `parse atom mock xml string should return media content size of 3`() {
		feed.entries[4].mediaNamespace?.contents?.size `should equal` 3
	}

	@Test
	fun `parse atom mock xml string should return multiple expected media content attributes for fourth item`() {
		feed.entries[4].mediaNamespace?.contents?.get(0)?.attributes?.url `should equal` "http://www.foo.com/band1-song1.mp3"
		feed.entries[4].mediaNamespace?.contents?.get(0)?.attributes?.fileSize `should equal` 1000
		feed.entries[4].mediaNamespace?.contents?.get(0)?.attributes?.type `should equal` "audio/mpeg"
		feed.entries[4].mediaNamespace?.contents?.get(0)?.attributes?.expression `should equal` "full"

		feed.entries[4].mediaNamespace?.contents?.get(0)?.credits?.get(0)?.value `should equal` "member of band1"
		feed.entries[4].mediaNamespace?.contents?.get(0)?.credits?.get(0)?.attributes?.role `should equal` "musician"

		feed.entries[4].mediaNamespace?.contents?.get(0)?.category?.value `should equal` "music/band1/album/song"
		feed.entries[4].mediaNamespace?.contents?.get(0)?.rating?.value `should equal` "nonadult"
	}

	@Test
	fun `parse atom mock xml string should return expected media second content elements for fourth item`() {
		feed.entries[4].mediaNamespace?.contents?.get(1)?.attributes?.url `should equal` "http://www.foo.com/band2-song1.mp3"
		feed.entries[4].mediaNamespace?.contents?.get(1)?.attributes?.fileSize `should equal` 2000
		feed.entries[4].mediaNamespace?.contents?.get(1)?.attributes?.type `should equal` "audio/mpeg"
		feed.entries[4].mediaNamespace?.contents?.get(1)?.attributes?.expression `should equal` "full"

		feed.entries[4].mediaNamespace?.contents?.get(1)?.credits?.get(0)?.value `should equal` "member of band2"
		feed.entries[4].mediaNamespace?.contents?.get(1)?.credits?.get(0)?.attributes?.role `should equal` "musician"

		feed.entries[4].mediaNamespace?.contents?.get(1)?.category?.value `should equal` "music/band2/album/song"
		feed.entries[4].mediaNamespace?.contents?.get(1)?.rating?.value `should equal` "nonadult"
	}

	@Test
	fun `parse atom mock xml string should return expected media third content elements for fourth item`() {
		feed.entries[4].mediaNamespace?.contents?.get(2)?.attributes?.url `should equal` "http://www.foo.com/band3-song1.mp3"
		feed.entries[4].mediaNamespace?.contents?.get(2)?.attributes?.fileSize `should equal` 1500
		feed.entries[4].mediaNamespace?.contents?.get(2)?.attributes?.type `should equal` "audio/mpeg"
		feed.entries[4].mediaNamespace?.contents?.get(2)?.attributes?.expression `should equal` "full"

		feed.entries[4].mediaNamespace?.contents?.get(2)?.credits?.get(0)?.value `should equal` "member of band3"
		feed.entries[4].mediaNamespace?.contents?.get(2)?.credits?.get(0)?.attributes?.role `should equal` "musician"

		feed.entries[4].mediaNamespace?.contents?.get(2)?.category?.value `should equal` "music/band3/album/song"
		feed.entries[4].mediaNamespace?.contents?.get(2)?.rating?.value `should equal` "nonadult"
	}

	@Test
	fun `parse atom mock xml string should return media group with expected content elements for fifth item`() {
		feed.entries[5].mediaNamespace?.group?.contents?.get(0)?.attributes?.url `should equal` "http://www.foo.com/song64kbps.mp3"
		feed.entries[5].mediaNamespace?.group?.contents?.get(0)?.attributes?.bitrate `should equal` 64
		feed.entries[5].mediaNamespace?.group?.contents?.get(0)?.attributes?.expression `should equal` "full"
		feed.entries[5].mediaNamespace?.group?.contents?.get(0)?.attributes?.fileSize `should equal` 1000
		feed.entries[5].mediaNamespace?.group?.contents?.get(0)?.attributes?.isDefault `should equal` true
		feed.entries[5].mediaNamespace?.group?.contents?.get(0)?.attributes?.type `should equal` "audio/mpeg"

		feed.entries[5].mediaNamespace?.group?.contents?.get(1)?.attributes?.url `should equal` "http://www.foo.com/song128kbps.mp3"
		feed.entries[5].mediaNamespace?.group?.contents?.get(1)?.attributes?.bitrate `should equal` 128
		feed.entries[5].mediaNamespace?.group?.contents?.get(1)?.attributes?.expression `should equal` "full"
		feed.entries[5].mediaNamespace?.group?.contents?.get(1)?.attributes?.fileSize `should equal` 2000
		feed.entries[5].mediaNamespace?.group?.contents?.get(1)?.attributes?.type `should equal` "audio/mpeg"

		feed.entries[5].mediaNamespace?.group?.contents?.get(2)?.attributes?.url `should equal` "http://www.foo.com/song256kbps.mp3"
		feed.entries[5].mediaNamespace?.group?.contents?.get(2)?.attributes?.bitrate `should equal` 256
		feed.entries[5].mediaNamespace?.group?.contents?.get(2)?.attributes?.expression `should equal` "full"
		feed.entries[5].mediaNamespace?.group?.contents?.get(2)?.attributes?.fileSize `should equal` 4000
		feed.entries[5].mediaNamespace?.group?.contents?.get(2)?.attributes?.type `should equal` "audio/mpeg"

		feed.entries[5].mediaNamespace?.group?.contents?.get(3)?.attributes?.url `should equal` "http://www.foo.com/song512kbps.mp3.torrent"
		feed.entries[5].mediaNamespace?.group?.contents?.get(3)?.attributes?.expression `should equal` "full"
		feed.entries[5].mediaNamespace?.group?.contents?.get(3)?.attributes?.fileSize `should equal` 8000
		feed.entries[5].mediaNamespace?.group?.contents?.get(3)?.attributes?.type `should equal` "application/x-bittorrent;enclosed=audio/mpeg"

		feed.entries[5].mediaNamespace?.group?.contents?.get(4)?.attributes?.url `should equal` "http://www.foo.com/song.wav"
		feed.entries[5].mediaNamespace?.group?.contents?.get(4)?.attributes?.expression `should equal` "full"
		feed.entries[5].mediaNamespace?.group?.contents?.get(4)?.attributes?.fileSize `should equal` 16000
		feed.entries[5].mediaNamespace?.group?.contents?.get(4)?.attributes?.type `should equal` "audio/x-wav"
	}

	@Test
	fun `parse atom mock xml string should return media group with expected credit elements for fifth item`() {
		feed.entries[5].mediaNamespace?.group?.credits?.size `should equal` 2

		feed.entries[5].mediaNamespace?.group?.credits?.get(0)?.value `should equal` "band member 1"
		feed.entries[5].mediaNamespace?.group?.credits?.get(0)?.attributes?.role `should equal` "musician"

		feed.entries[5].mediaNamespace?.group?.credits?.get(1)?.value `should equal` "band member 2"
		feed.entries[5].mediaNamespace?.group?.credits?.get(1)?.attributes?.role `should equal` "musician"
	}

	@Test
	fun `parse atom mock xml string should return media group with expected category element for fifth item`() {
		feed.entries[5].mediaNamespace?.group?.category?.value `should equal` "music/artist name/album/song"
	}

	@Test
	fun `parse atom mock xml string should return media group with expected rating element for fifth item`() {
		feed.entries[5].mediaNamespace?.group?.rating?.value `should equal` "nonadult"
	}

	@Test
	fun `parse atom mock xml string should return media content element for sixth item`() {
		feed.entries[6].mediaNamespace?.contents?.get(0)?.attributes?.bitrate `should equal` 128
		feed.entries[6].mediaNamespace?.contents?.get(0)?.attributes?.expression `should equal` "full"
		feed.entries[6].mediaNamespace?.contents?.get(0)?.attributes?.fileSize `should equal` 2000
		feed.entries[6].mediaNamespace?.contents?.get(0)?.attributes?.type `should equal` "video/quicktime"
		feed.entries[6].mediaNamespace?.contents?.get(0)?.attributes?.url `should equal` "http://www.foo.com/video.mov"
	}

	@Test
	fun `parse atom mock xml string should return media community with expected star rating element for sixth item`() {
		feed.entries[6].mediaNamespace?.community?.starRating?.attributes?.average `should equal` 3.5
		feed.entries[6].mediaNamespace?.community?.starRating?.attributes?.count `should equal` 20
		feed.entries[6].mediaNamespace?.community?.starRating?.attributes?.max `should equal` 10
		feed.entries[6].mediaNamespace?.community?.starRating?.attributes?.min `should equal` 1
	}

	@Test
	fun `parse atom mock xml string should return media community with expected statistics element for sixth item`() {
		feed.entries[6].mediaNamespace?.community?.statistics?.attributes?.favorites `should equal` 5
		feed.entries[6].mediaNamespace?.community?.statistics?.attributes?.views `should equal` 5
	}

	@Test
	fun `parse atom mock xml string should return media community with expected tags element for sixth item`() {
		feed.entries[6].mediaNamespace?.community?.tags?.value `should equal` "news: 5, abc:3, reuters"
	}

	@Test
	fun `parse atom mock xml string should return media comments elements for sixth item`() {
		feed.entries[6].mediaNamespace?.comments?.get(0) `should equal` "comment1"
		feed.entries[6].mediaNamespace?.comments?.get(1) `should equal` "comment2"
	}

	@Test
	fun `parse atom mock xml string should return media embed attributes for sixth item`() {
		feed.entries[6].mediaNamespace?.embed?.attributes?.height `should equal` 323
		feed.entries[6].mediaNamespace?.embed?.attributes?.url `should equal` "http://www.foo.com/player.swf"
		feed.entries[6].mediaNamespace?.embed?.attributes?.width `should equal` 512
	}

	@Test
	fun `parse atom mock xml string should return media embed parameter for sixth item`() {
		feed.entries[6].mediaNamespace?.embed?.params?.get(0)?.value `should equal` "application/x-shockwave-flash"
		feed.entries[6].mediaNamespace?.embed?.params?.get(0)?.attributes?.name `should equal` "type"

		feed.entries[6].mediaNamespace?.embed?.params?.get(1)?.value `should equal` "512"
		feed.entries[6].mediaNamespace?.embed?.params?.get(1)?.attributes?.name `should equal` "width"

		feed.entries[6].mediaNamespace?.embed?.params?.get(2)?.value `should equal` "323"
		feed.entries[6].mediaNamespace?.embed?.params?.get(2)?.attributes?.name `should equal` "height"

		feed.entries[6].mediaNamespace?.embed?.params?.get(3)?.value `should equal` "true"
		feed.entries[6].mediaNamespace?.embed?.params?.get(3)?.attributes?.name `should equal` "allowFullScreen"

		feed.entries[6].mediaNamespace?.embed?.params?.get(4
		)?.value `should equal` "id=12345&vid=678912i&lang=en-us&intl=us&thumbUrl=http://www.foo.com/thumbnail.jpg"
		feed.entries[6].mediaNamespace?.embed?.params?.get(4)?.attributes?.name `should equal` "flashVars"
	}

	@Test
	fun `parse atom mock xml string should return media responses elements for sixth item`() {
		feed.entries[6].mediaNamespace?.responses?.get(0) `should equal` "http://www.response1.com"
		feed.entries[6].mediaNamespace?.responses?.get(1) `should equal` "http://www.response2.com"
	}

	@Test
	fun `parse atom mock xml string should return media backLinks elements for sixth item`() {
		feed.entries[6].mediaNamespace?.backLinks?.get(0) `should equal` "http://www.backlink1.com"
		feed.entries[6].mediaNamespace?.backLinks?.get(1) `should equal` "http://www.backlink2.com"
	}

	@Test
	fun `parse atom mock xml string should return media status element for sixth item`() {
		feed.entries[6].mediaNamespace?.status?.attributes?.state `should equal` "active"
	}

	@Test
	fun `parse atom mock xml string should return media price element for sixth item`() {
		feed.entries[6].mediaNamespace?.price?.get(0)?.attributes?.currency `should equal` "EUR"
		feed.entries[6].mediaNamespace?.price?.get(0)?.attributes?.price `should equal` 19.99
		feed.entries[6].mediaNamespace?.price?.get(0)?.attributes?.type `should equal` "rent"
	}

	@Test
	fun `parse atom mock xml string should return media license element for sixth item`() {
		feed.entries[6].mediaNamespace?.license?.value `should equal` "Sample license for a video"
		feed.entries[6].mediaNamespace?.license?.attributes?.href `should equal` "http://www.licensehost.com/license"
		feed.entries[6].mediaNamespace?.license?.attributes?.type `should equal` "text/html"
	}

	@Test
	fun `parse atom mock xml string should return media subTitle element for sixth item`() {
		feed.entries[6].mediaNamespace?.subTitle?.attributes?.href `should equal` "http://www.foo.org/subtitle.smil"
		feed.entries[6].mediaNamespace?.subTitle?.attributes?.lang `should equal` "en-us"
		feed.entries[6].mediaNamespace?.subTitle?.attributes?.type `should equal` "application/smil"
	}

	@Test
	fun `parse atom mock xml string should return media peerLink element for sixth item`() {
		feed.entries[6].mediaNamespace?.peerLink?.attributes?.href `should equal` "http://www.foo.org/sampleFile.torrent"
		feed.entries[6].mediaNamespace?.peerLink?.attributes?.type `should equal` "application/x-bittorrent"
	}

	@Test
	fun `parse atom mock xml string should return media location element for sixth item`() {
		feed.entries[6].mediaNamespace?.location?.attributes?.description `should equal` "My house"
		feed.entries[6].mediaNamespace?.location?.attributes?.start `should equal` 1 * 1000L //00:00:01.000
		feed.entries[6].mediaNamespace?.location?.attributes?.end `should equal` 1 * 60 * 1000L //00:01:00.000
	}

	@Test
	fun `parse atom mock xml string should return media location position element for sixth item`() {
		feed.entries[6].mediaNamespace?.location?.position `should equal` "35.669998 139.770004"
	}

	@Test
	fun `parse atom mock xml string should return media restriction element for sixth item`() {
		feed.entries[6].mediaNamespace?.restriction?.attributes?.relationship `should equal` "deny"
		feed.entries[6].mediaNamespace?.restriction?.attributes?.type `should equal` "sharing"
	}

	@Test
	fun `parse atom mock xml string should return media scenes element for sixth item`() {
		feed.entries[6].mediaNamespace?.scenes?.get(0)?.sceneTitle `should equal` "sceneTitle1"
		feed.entries[6].mediaNamespace?.scenes?.get(0)?.sceneDescription `should equal` "sceneDesc1"
		feed.entries[6].mediaNamespace?.scenes?.get(0)?.sceneStartTime `should equal` 15 * 1000L //00:00:15.000
		feed.entries[6].mediaNamespace?.scenes?.get(0)?.sceneEndTime `should equal` 45 * 1000L //00:00:45.000
	}

	@Test
	fun `parse atom mock xml string should return media content title and description element for seventh item`() {
		feed.entries[7].mediaNamespace?.contents?.get(0)?.title?.value `should equal` "The Judy's -- The Moo Song"
		feed.entries[7].mediaNamespace?.contents?.get(0)?.title?.attributes?.type `should equal` "plain"
		feed.entries[7].mediaNamespace?.contents?.get(0
		)?.description?.value `should equal` "This was some really bizarre band I listened to as a young lad."
		feed.entries[7].mediaNamespace?.contents?.get(0)?.description?.attributes?.type `should equal` "plain"
	}

	@Test
	fun `parse atom mock xml string should return media content thumbnail elements for seventh item`() {
		feed.entries[7].mediaNamespace?.contents?.get(0)?.thumbnails?.get(0)?.attributes?.url `should equal` "http://www.foo.com/keyframe.jpg"
		feed.entries[7].mediaNamespace?.contents?.get(0)?.thumbnails?.get(0)?.attributes?.width `should equal` 75
		feed.entries[7].mediaNamespace?.contents?.get(0)?.thumbnails?.get(0)?.attributes?.height `should equal` 50
		feed.entries[7].mediaNamespace?.contents?.get(0)?.thumbnails?.get(0
		)?.attributes?.time `should equal` (12 * 60 * 60 * 1000) + (5 * 60 * 1000) + (1 * 1000) + 123L
	}

	@Test
	fun `parse atom mock xml string should return expected media keywords for seventh item`() {
		feed.entries[7].mediaNamespace?.contents?.get(0)?.keywords `should equal` listOf("kitty", "cat", "big dog", "yarn", "fluffy")
	}
}