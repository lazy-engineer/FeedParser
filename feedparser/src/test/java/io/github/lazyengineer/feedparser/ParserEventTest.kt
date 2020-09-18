package io.github.lazyengineer.feedparser

import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_CATEGORY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_CLOUD
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_COPYRIGHT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DOCS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_GENERATOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_IMAGE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_LANGUAGE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_LAST_BUILD_DATE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_LINK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_MANAGING_EDITOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_PUB_DATE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_RATING
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_SKIP_DAYS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_SKIP_HOURS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_TEXT_INPUT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_TTL
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_WEB_MASTER
import io.github.lazyengineer.feedparser.rss.RSSParserElement.IMAGE_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.IMAGE_HEIGHT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.IMAGE_LINK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.IMAGE_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.IMAGE_URL
import io.github.lazyengineer.feedparser.rss.RSSParserElement.IMAGE_WIDTH
import io.github.lazyengineer.feedparser.rss.RSSParserElement.ITEM_AUTHOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.ITEM_CATEGORY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.ITEM_COMMENTS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.ITEM_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.ITEM_ENCLOSURE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.ITEM_GUID
import io.github.lazyengineer.feedparser.rss.RSSParserElement.ITEM_LINK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.ITEM_PUB_DATE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.ITEM_SOURCE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.ITEM_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.RSS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.TEXT_INPUT_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.TEXT_INPUT_LINK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.TEXT_INPUT_NAME
import io.github.lazyengineer.feedparser.rss.RSSParserElement.TEXT_INPUT_TITLE
import org.amshove.kluent.`should equal`
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ParserEventTest {

	@Test
	fun `find parser event with depth smaller one should return unsupported event`() {
		val depth = (Int.MIN_VALUE until 1).random()

		val event = ParserElement.from(elementName = "some element name", previousPath = "random path", depth = depth)

		event `should equal` null
	}

	@Test
	fun `find rss parser event should return unsupported event`() {
		val elementName = "rss"
		val depth = 1

		val event = ParserElement.from(elementName = elementName, previousPath = "", depth = depth)

		event `should equal` RSS
	}

	@Test
	fun `find channel parser event should return unsupported event`() {
		val elementName = "channel"
		val depth = 2

		val event = ParserElement.from(elementName = elementName, previousPath = "", depth = depth)

		event `should equal` null
	}

	@Test
	fun `find title parser event in channel path should return channel title event`() {
		val elementName = "title"
		val path = "/rss/channel"
		val depth = 3

		val event = ParserElement.from(elementName = elementName, previousPath = path, depth = depth)

		event `should equal` CHANNEL_TITLE
	}

	@Test
	fun `find title parser event in item path should return item title event`() {
		val elementName = "title"
		val path = "/rss/channel/item"
		val depth = 4

		val event = ParserElement.from(elementName = elementName, previousPath = path, depth = depth)

		event `should equal` ITEM_TITLE
	}

	@Test
	fun `find title parser event with previous item path but smaller depth should return channel title event`() {
		val elementName = "title"
		val previousItemPath = "/rss/channel/item"
		val channelLevelDepth = 3

		val event =
			ParserElement.from(elementName = elementName, previousPath = previousItemPath, depth = channelLevelDepth)

		event `should equal` CHANNEL_TITLE
	}

	@Test
	fun `find new item parser event after previous item ends should return channel item event`() {
		val newItemEvent = "item"
		val previousItemPath = "/rss/channel/item/title"
		val depth = 3

		val event = ParserElement.from(elementName = newItemEvent, previousPath = previousItemPath, depth = depth)

		event `should equal` CHANNEL_ITEM
	}

	@Test
	fun `find new item parser event after previous item ends should return channel item event no matter how deep previous path is`() {
		val newItemEvent = "item"
		val previousItemPath = "/rss/channel/item/title/deep/deeper/deepest"
		val depth = 3

		val event = ParserElement.from(elementName = newItemEvent, previousPath = previousItemPath, depth = depth)

		event `should equal` CHANNEL_ITEM
	}

	@Test
	fun `find image parser event after previous item ends should return channel image event no matter how deep item path is`() {
		val newItemEvent = "image"
		val previousItemPath = "/rss/channel/item/title/deep/deeper/deepest"
		val depth = 3

		val event = ParserElement.from(elementName = newItemEvent, previousPath = previousItemPath, depth = depth)

		event `should equal` CHANNEL_IMAGE
	}

	@Test
	fun `find another title parser event after previous path is also channel title event should return channel title event again`() {
		val newItemEvent = "title"
		val previousItemPath = "/rss/channel/title"
		val depth = 3

		val event = ParserElement.from(elementName = newItemEvent, previousPath = previousItemPath, depth = depth)

		event `should equal` CHANNEL_TITLE
	}

	@Test
	fun `parser channel element should return equivalent parser event`() {
		val previousPath = "/rss/channel"
		val depth = 3

		val event1 = ParserElement.from(elementName = "title", previousPath = previousPath, depth = depth)
		val event2 = ParserElement.from(elementName = "link", previousPath = previousPath, depth = depth)
		val event3 = ParserElement.from(elementName = "description", previousPath = previousPath, depth = depth)
		val event4 = ParserElement.from(elementName = "category", previousPath = previousPath, depth = depth)
		val event5 = ParserElement.from(elementName = "cloud", previousPath = previousPath, depth = depth)
		val event6 = ParserElement.from(elementName = "copyright", previousPath = previousPath, depth = depth)
		val event7 = ParserElement.from(elementName = "docs", previousPath = previousPath, depth = depth)
		val event8 = ParserElement.from(elementName = "generator", previousPath = previousPath, depth = depth)
		val event9 = ParserElement.from(elementName = "language", previousPath = previousPath, depth = depth)
		val event10 = ParserElement.from(elementName = "lastBuildDate", previousPath = previousPath, depth = depth)
		val event11 = ParserElement.from(elementName = "managingEditor", previousPath = previousPath, depth = depth)
		val event12 = ParserElement.from(elementName = "pubDate", previousPath = previousPath, depth = depth)
		val event13 = ParserElement.from(elementName = "rating", previousPath = previousPath, depth = depth)
		val event14 = ParserElement.from(elementName = "skipDays", previousPath = previousPath, depth = depth)
		val event15 = ParserElement.from(elementName = "skipHours", previousPath = previousPath, depth = depth)
		val event16 = ParserElement.from(elementName = "ttl", previousPath = previousPath, depth = depth)
		val event17 = ParserElement.from(elementName = "webMaster", previousPath = previousPath, depth = depth)
		val event18 = ParserElement.from(elementName = "image", previousPath = previousPath, depth = depth)
		val event19 = ParserElement.from(elementName = "textInput", previousPath = previousPath, depth = depth)
		val event20 = ParserElement.from(elementName = "item", previousPath = previousPath, depth = depth)

		event1 `should equal` CHANNEL_TITLE
		event2 `should equal` CHANNEL_LINK
		event3 `should equal` CHANNEL_DESCRIPTION
		event4 `should equal` CHANNEL_CATEGORY
		event5 `should equal` CHANNEL_CLOUD
		event6 `should equal` CHANNEL_COPYRIGHT
		event7 `should equal` CHANNEL_DOCS
		event8 `should equal` CHANNEL_GENERATOR
		event9 `should equal` CHANNEL_LANGUAGE
		event10 `should equal` CHANNEL_LAST_BUILD_DATE
		event11 `should equal` CHANNEL_MANAGING_EDITOR
		event12 `should equal` CHANNEL_PUB_DATE
		event13 `should equal` CHANNEL_RATING
		event14 `should equal` CHANNEL_SKIP_DAYS
		event15 `should equal` CHANNEL_SKIP_HOURS
		event16 `should equal` CHANNEL_TTL
		event17 `should equal` CHANNEL_WEB_MASTER
		event18 `should equal` CHANNEL_IMAGE
		event19 `should equal` CHANNEL_TEXT_INPUT
		event20 `should equal` CHANNEL_ITEM
	}

	@Test
	fun `parser item element should return equivalent parser event`() {
		val previousPath = "/rss/channel/item"
		val depth = 4

		val event1 = ParserElement.from(elementName = "title", previousPath = previousPath, depth = depth)
		val event2 = ParserElement.from(elementName = "link", previousPath = previousPath, depth = depth)
		val event3 = ParserElement.from(elementName = "description", previousPath = previousPath, depth = depth)
		val event4 = ParserElement.from(elementName = "enclosure", previousPath = previousPath, depth = depth)
		val event5 = ParserElement.from(elementName = "author", previousPath = previousPath, depth = depth)
		val event6 = ParserElement.from(elementName = "source", previousPath = previousPath, depth = depth)
		val event7 = ParserElement.from(elementName = "category", previousPath = previousPath, depth = depth)
		val event8 = ParserElement.from(elementName = "comments", previousPath = previousPath, depth = depth)
		val event9 = ParserElement.from(elementName = "guid", previousPath = previousPath, depth = depth)
		val event10 = ParserElement.from(elementName = "pubDate", previousPath = previousPath, depth = depth)

		event1 `should equal` ITEM_TITLE
		event2 `should equal` ITEM_LINK
		event3 `should equal` ITEM_DESCRIPTION
		event4 `should equal` ITEM_ENCLOSURE
		event5 `should equal` ITEM_AUTHOR
		event6 `should equal` ITEM_SOURCE
		event7 `should equal` ITEM_CATEGORY
		event8 `should equal` ITEM_COMMENTS
		event9 `should equal` ITEM_GUID
		event10 `should equal` ITEM_PUB_DATE
	}

	@Test
	fun `parser image element should return equivalent parser event`() {
		val previousPath = "/rss/channel/image"
		val depth = 4

		val event1 = ParserElement.from(elementName = "url", previousPath = previousPath, depth = depth)
		val event2 = ParserElement.from(elementName = "title", previousPath = previousPath, depth = depth)
		val event3 = ParserElement.from(elementName = "link", previousPath = previousPath, depth = depth)
		val event4 = ParserElement.from(elementName = "width", previousPath = previousPath, depth = depth)
		val event5 = ParserElement.from(elementName = "height", previousPath = previousPath, depth = depth)
		val event6 = ParserElement.from(elementName = "description", previousPath = previousPath, depth = depth)

		event1 `should equal` IMAGE_URL
		event2 `should equal` IMAGE_TITLE
		event3 `should equal` IMAGE_LINK
		event4 `should equal` IMAGE_WIDTH
		event5 `should equal` IMAGE_HEIGHT
		event6 `should equal` IMAGE_DESCRIPTION
	}

	@Test
	fun `parser textInput element should return equivalent parser event`() {
		val previousPath = "/rss/channel/textInput"
		val depth = 4

		val event1 = ParserElement.from(elementName = "title", previousPath = previousPath, depth = depth)
		val event2 = ParserElement.from(elementName = "description", previousPath = previousPath, depth = depth)
		val event3 = ParserElement.from(elementName = "name", previousPath = previousPath, depth = depth)
		val event4 = ParserElement.from(elementName = "link", previousPath = previousPath, depth = depth)

		event1 `should equal` TEXT_INPUT_TITLE
		event2 `should equal` TEXT_INPUT_DESCRIPTION
		event3 `should equal` TEXT_INPUT_NAME
		event4 `should equal` TEXT_INPUT_LINK
	}
}
