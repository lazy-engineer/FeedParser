package io.github.lazyengineer.feedparser.rss

import io.github.lazyengineer.feedparser.FeedParser
import io.github.lazyengineer.feedparser.model.feed.RSSFeed
import io.github.lazyengineer.feedparser.model.namespace.syndication.SyndicationUpdatePeriod
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
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.TimeZone

@RunWith(MockitoJUnitRunner::class)
class RDFParserTest {

	private lateinit var rssXML: String
	private lateinit var xmlPullParser: XmlPullParser
	private lateinit var feed: RSSFeed

	@Before
	fun setUp() {
		val rssXMLStream = this.javaClass.classLoader!!.getResourceAsStream("mock_rdf_sample.xml")
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
	fun `parse rdf xml string should return expected rdf channel`() {
		feed.title `should equal` "Meerkat"
		feed.link `should equal` "http://meerkat.oreillynet.com"
		feed.description `should equal` "Meerkat: An Open Wire Service"
		feed.about `should equal` "http://meerkat.oreillynet.com/?_fl=rss1.0"
		feed.itemsResource `should equal` "http://c.moreover.com/click/here.pl?r123"
		feed.imageResource `should equal` "http://meerkat.oreillynet.com/icons/meerkat-powered.jpg"
		feed.textInputResource `should equal` "http://meerkat.oreillynet.com"
	}

	@Test
	fun `parse rdf xml string should return expected channel syndication namespace`() {
		val calendar = GregorianCalendar(2000, Calendar.JANUARY, 1, 0, 0, 0) //2000-01-01T12:00+00:00
		calendar.timeZone = TimeZone.getTimeZone("GMT")

		feed.syndicationNamespace?.updatePeriod `should equal` SyndicationUpdatePeriod.HOURLY
		feed.syndicationNamespace?.updateFrequency `should equal` 2
		feed.syndicationNamespace?.updateBase `should equal` Date(calendar.timeInMillis)
	}

	@Test
	fun `parse rdf xml string should return expected channel text input element`() {
		feed.textInput?.title `should equal` "Search Meerkat"
		feed.textInput?.description `should equal` "Search Meerkat's RSS Database..."
		feed.textInput?.name `should equal` "s"
		feed.textInput?.link `should equal` "http://meerkat.oreillynet.com/"
		feed.textInput?.about `should equal` "http://meerkat.oreillynet.com"
	}

	@Test
	fun `parse rdf xml string should return expected channel image element`() {
		feed.image?.title `should equal` "Meerkat Powered!"
		feed.image?.url `should equal` "http://meerkat.oreillynet.com/icons/meerkat-powered.jpg"
		feed.image?.link `should equal` "http://meerkat.oreillynet.com"
		feed.image?.about `should equal` "http://meerkat.oreillynet.com/icons/meerkat-powered.jpg"
	}

	@Test
	fun `parse rdf xml string should return expected channel item element`() {
		feed.items[0].title `should equal` "XML: A Disruptive Technology"
		feed.items[0].link `should equal` "http://c.moreover.com/click/here.pl?r123"
		feed.items[0].description `should equal` "XML is placing increasingly heavy loads on the existing technical infrastructure of the Internet."
		feed.items[0].about `should equal` "http://c.moreover.com/click/here.pl?r123"
	}

	@Test
	fun `parse rdf xml string should return expected dc element`() {
		val calendar = GregorianCalendar(2000, Calendar.JANUARY, 1, 0, 0, 0) //2000-01-01T12:00+00:00
		calendar.timeZone = TimeZone.getTimeZone("GMT")

		feed.dublinCoreNamespace?.title `should equal` "Dublin Core™ in XML"
		feed.dublinCoreNamespace?.creator `should equal` "creator"
		feed.dublinCoreNamespace?.subject `should equal` "seafood"
		feed.dublinCoreNamespace?.description `should equal` "UKOLN is a national focus of expertise in digital information management. It provides policy, research and awareness services to the UK library, information and cultural heritage communities. UKOLN is based at the University of Bath."
		feed.dublinCoreNamespace?.publisher `should equal` "UKOLN, University of Bath"
		feed.dublinCoreNamespace?.contributor `should equal` "contributor"
		feed.dublinCoreNamespace?.date `should equal` Date(calendar.timeInMillis)
		feed.dublinCoreNamespace?.type `should equal` "type"
		feed.dublinCoreNamespace?.format `should equal` "format"
		feed.dublinCoreNamespace?.identifier `should equal` "http://www.ukoln.ac.uk/"
		feed.dublinCoreNamespace?.source `should equal` "source"
		feed.dublinCoreNamespace?.language `should equal` "language"
		feed.dublinCoreNamespace?.relation `should equal` "relation"
		feed.dublinCoreNamespace?.coverage `should equal` "coverage"
		feed.dublinCoreNamespace?.rights `should equal` "rights"
	}

	@Test
	fun `parse rdf xml string should return expected dc element for first item`() {
		val calendar = GregorianCalendar(2000, Calendar.JANUARY, 1, 0, 0, 0) //2000-01-01T12:00+00:00
		calendar.timeZone = TimeZone.getTimeZone("GMT")

		feed.items[0].dublinCoreNamespace?.title `should equal` "title"
		feed.items[0].dublinCoreNamespace?.creator `should equal` "creator"
		feed.items[0].dublinCoreNamespace?.subject `should equal` "subject"
		feed.items[0].dublinCoreNamespace?.description `should equal` "description"
		feed.items[0].dublinCoreNamespace?.publisher `should equal` "publisher"
		feed.items[0].dublinCoreNamespace?.contributor `should equal` "contributor"
		feed.items[0].dublinCoreNamespace?.date `should equal` Date(calendar.timeInMillis)
		feed.items[0].dublinCoreNamespace?.type `should equal` "type"
		feed.items[0].dublinCoreNamespace?.format `should equal` "format"
		feed.items[0].dublinCoreNamespace?.identifier `should equal` "identifier"
		feed.items[0].dublinCoreNamespace?.source `should equal` "source"
		feed.items[0].dublinCoreNamespace?.language `should equal` "language"
		feed.items[0].dublinCoreNamespace?.relation `should equal` "relation"
		feed.items[0].dublinCoreNamespace?.coverage `should equal` "coverage"
		feed.items[0].dublinCoreNamespace?.rights `should equal` "rights"
	}
}
