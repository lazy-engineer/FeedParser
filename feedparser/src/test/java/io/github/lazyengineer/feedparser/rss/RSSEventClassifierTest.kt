package io.github.lazyengineer.feedparser.rss

import io.github.lazyengineer.feedparser.AttributeEvent
import io.github.lazyengineer.feedparser.ContainerEvent
import io.github.lazyengineer.feedparser.UnsupportedEvent
import io.github.lazyengineer.feedparser.ValueEvent
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_AUTHOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_AUTHOR_EMAIL
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_AUTHOR_NAME
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_AUTHOR_URI
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_CATEGORY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_CONTRIBUTOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_CONTRIBUTOR_EMAIL
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_CONTRIBUTOR_NAME
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_CONTRIBUTOR_URI
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_GENERATOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_ICON
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_ID
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_LINK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_LOGO
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_RIGHTS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_SUBTITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ATOM_UPDATED
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_CATEGORY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_CLOUD
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_COPYRIGHT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DOCS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_CONTRIBUTOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_COVERAGE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_CREATOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_DATE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_FORMAT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_IDENTIFIER
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_LANGUAGE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_PUBLISHER
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_RELATION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_RIGHTS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_SOURCE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_SUBJECT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_DUBLIN_CORE_TYPE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_GENERATOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_IMAGE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_AUTHOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_AUTHOR_EMAIL
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_AUTHOR_NAME
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_AUTHOR_URI
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_CATEGORY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_CONTENT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_CONTRIBUTOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_CONTRIBUTOR_EMAIL
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_CONTRIBUTOR_NAME
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_CONTRIBUTOR_URI
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_ID
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_LINK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_PUBLISHED
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_RIGHTS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_SOURCE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_SOURCE_ID
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_SOURCE_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_SOURCE_UPDATED
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_SUMMARY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ATOM_UPDATED
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_CONTENT_ENCODED
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_CONTRIBUTOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_COVERAGE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_CREATOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_DATE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_FORMAT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_IDENTIFIER
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_LANGUAGE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_PUBLISHER
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_RELATION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_RIGHTS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_SOURCE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_SUBJECT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_DUBLIN_CORE_TYPE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_AUTHOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_BLOCK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_DURATION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_EPISODE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_EPISODE_TYPE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_EXPLICIT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_IMAGE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_IS_CLOSED_CAPTIONED
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_KEYWORDS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_ORDER
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_SEASON
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_SUBTITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_SUMMARY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_ITUNES_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_BACK_LINKS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_BACK_LINKS_BACK_LINK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CATEGORY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_COMMENTS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_COMMENTS_MEDIA_COMMENT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_COMMUNITY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_STAR_RATING
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_STATISTICS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_TAGS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CONTENT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CONTENT_CATEGORY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CONTENT_CREDIT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CONTENT_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CONTENT_HASH
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CONTENT_KEYWORDS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CONTENT_PLAYER
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CONTENT_RATING
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CONTENT_TEXT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CONTENT_THUMBNAIL
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CONTENT_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_COPYRIGHT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_CREDIT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_EMBED
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_EMBED_MEDIA_PARAM
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_GROUP
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CATEGORY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CONTENT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CREDIT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_GROUP_MEDIA_RATING
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_KEYWORDS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_LICENSE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_LOCATION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_LOCATION_POINT_POSITION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_LOCATION_POSITION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_LOCATION_WHERE_POSITION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_PEER_LINK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_PRICE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_RATING
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_RESPONSES
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_RESPONSES_MEDIA_RESPONSE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_RESTRICTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_RIGHTS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_SCENES
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_END_TIME
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_START_TIME
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_STATUS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_SUB_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_TEXT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_THUMBNAIL
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITEM_MEDIA_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_AUTHOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_BLOCK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_CATEGORY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_COMPLETE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_EXPLICIT
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_IMAGE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_KEYWORDS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_NEW_FEED_URL
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_OWNER
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_OWNER_EMAIL
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_OWNER_NAME
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_SUBCATEGORY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_SUBTITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_SUMMARY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_ITUNES_TYPE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_LANGUAGE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_LAST_BUILD_DATE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_LINK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_MANAGING_EDITOR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_PUB_DATE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_RATING
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_SKIP_DAY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_SKIP_DAYS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_SKIP_HOUR
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_SKIP_HOURS
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_SYNDICATION_UPDATE_BASE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_SYNDICATION_UPDATE_FREQUENCY
import io.github.lazyengineer.feedparser.rss.RSSParserElement.CHANNEL_SYNDICATION_UPDATE_PERIOD
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
import io.github.lazyengineer.feedparser.rss.RSSParserElement.TEXT_INPUT_DESCRIPTION
import io.github.lazyengineer.feedparser.rss.RSSParserElement.TEXT_INPUT_LINK
import io.github.lazyengineer.feedparser.rss.RSSParserElement.TEXT_INPUT_NAME
import io.github.lazyengineer.feedparser.rss.RSSParserElement.TEXT_INPUT_TITLE
import io.github.lazyengineer.feedparser.rss.RSSParserElement.UNSUPPORTED_RSS_ELEMENT
import org.amshove.kluent.`should be instance of`
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RSSEventClassifierTest {

	@Test
	fun `unsupported element should be classified as unsupported event`() {
		val parserElement: RSSParserElement = UNSUPPORTED_RSS_ELEMENT

		parserElement.classify() `should be instance of` UnsupportedEvent.javaClass
	}

	@Test
	fun `channel title element should be classified as value event`() {
		val parserElement: RSSParserElement = CHANNEL_TITLE

		parserElement.classify() `should be instance of` ValueEvent.javaClass
	}

	@Test
	fun `all container elements should be classified as such`() {
		val parserElements = mutableListOf<RSSParserElement>()
		parserElements.add(CHANNEL_ITEM)
		parserElements.add(CHANNEL_TEXT_INPUT)
		parserElements.add(CHANNEL_IMAGE)
		parserElements.add(CHANNEL_SKIP_DAYS)
		parserElements.add(CHANNEL_SKIP_HOURS)
		parserElements.add(CHANNEL_ITUNES_OWNER)
		parserElements.add(CHANNEL_ITEM_MEDIA_COMMUNITY)
		parserElements.add(CHANNEL_ITEM_MEDIA_COMMENTS)
		parserElements.add(CHANNEL_ITEM_MEDIA_RESPONSES)
		parserElements.add(CHANNEL_ITEM_MEDIA_BACK_LINKS)
		parserElements.add(CHANNEL_ITEM_MEDIA_LOCATION_WHERE_POSITION)
		parserElements.add(CHANNEL_ITEM_MEDIA_LOCATION_POINT_POSITION)
		parserElements.add(CHANNEL_ITEM_MEDIA_SCENES)
		parserElements.add(CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE)
		parserElements.add(CHANNEL_ITEM_MEDIA_GROUP)
		parserElements.add(CHANNEL_ATOM_AUTHOR)
		parserElements.add(CHANNEL_ATOM_CONTRIBUTOR)
		parserElements.add(CHANNEL_ITEM_ATOM_SOURCE)
		parserElements.add(CHANNEL_ITEM_ATOM_AUTHOR)
		parserElements.add(CHANNEL_ITEM_ATOM_CONTRIBUTOR)

		parserElements.forEach { parserElement ->
			parserElement.classify() `should be instance of` ContainerEvent.javaClass
		}
	}

	@Test
	fun `all attribute elements should be classified as such`() {
		val parserElements = mutableListOf<RSSParserElement>()
		parserElements.add(ITEM_ENCLOSURE)
		parserElements.add(CHANNEL_CLOUD)
		parserElements.add(CHANNEL_ITUNES_IMAGE)
		parserElements.add(CHANNEL_ITUNES_CATEGORY)
		parserElements.add(CHANNEL_ITUNES_SUBCATEGORY)
		parserElements.add(CHANNEL_ITEM_ITUNES_IMAGE)
		parserElements.add(CHANNEL_ITEM_MEDIA_THUMBNAIL)
		parserElements.add(CHANNEL_ITEM_MEDIA_CONTENT)
		parserElements.add(CHANNEL_ITEM_MEDIA_RIGHTS)
		parserElements.add(CHANNEL_ITEM_MEDIA_CONTENT_PLAYER)
		parserElements.add(CHANNEL_ITEM_MEDIA_CONTENT_THUMBNAIL)
		parserElements.add(CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_STAR_RATING)
		parserElements.add(CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_STATISTICS)
		parserElements.add(CHANNEL_ITEM_MEDIA_EMBED)
		parserElements.add(CHANNEL_ITEM_MEDIA_STATUS)
		parserElements.add(CHANNEL_ITEM_MEDIA_PRICE)
		parserElements.add(CHANNEL_ITEM_MEDIA_SUB_TITLE)
		parserElements.add(CHANNEL_ITEM_MEDIA_PEER_LINK)
		parserElements.add(CHANNEL_ITEM_MEDIA_LOCATION)
		parserElements.add(CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CONTENT)
		parserElements.add(CHANNEL_ATOM_LINK)
		parserElements.add(CHANNEL_ATOM_CATEGORY)
		parserElements.add(CHANNEL_ITEM_ATOM_LINK)
		parserElements.add(CHANNEL_ITEM_ATOM_CATEGORY)

		parserElements.forEach { parserElement ->
			parserElement.classify() `should be instance of` AttributeEvent.javaClass
		}
	}

	@Test
	fun `all value elements should be classified as such`() {
		val parserElements = mutableListOf<RSSParserElement>()
		parserElements.add(CHANNEL_TITLE)
		parserElements.add(CHANNEL_LINK)
		parserElements.add(CHANNEL_DESCRIPTION)
		parserElements.add(CHANNEL_CATEGORY)
		parserElements.add(CHANNEL_COPYRIGHT)
		parserElements.add(CHANNEL_DOCS)
		parserElements.add(CHANNEL_GENERATOR)
		parserElements.add(CHANNEL_LANGUAGE)
		parserElements.add(CHANNEL_LAST_BUILD_DATE)
		parserElements.add(CHANNEL_MANAGING_EDITOR)
		parserElements.add(CHANNEL_PUB_DATE)
		parserElements.add(CHANNEL_RATING)
		parserElements.add(CHANNEL_SKIP_DAY)
		parserElements.add(CHANNEL_SKIP_HOUR)
		parserElements.add(CHANNEL_TTL)
		parserElements.add(CHANNEL_WEB_MASTER)
		parserElements.add(ITEM_TITLE)
		parserElements.add(ITEM_LINK)
		parserElements.add(ITEM_DESCRIPTION)
		parserElements.add(ITEM_AUTHOR)
		parserElements.add(ITEM_SOURCE)
		parserElements.add(ITEM_CATEGORY)
		parserElements.add(ITEM_COMMENTS)
		parserElements.add(ITEM_GUID)
		parserElements.add(ITEM_PUB_DATE)
		parserElements.add(IMAGE_URL)
		parserElements.add(IMAGE_TITLE)
		parserElements.add(IMAGE_LINK)
		parserElements.add(IMAGE_WIDTH)
		parserElements.add(IMAGE_HEIGHT)
		parserElements.add(IMAGE_DESCRIPTION)
		parserElements.add(TEXT_INPUT_TITLE)
		parserElements.add(TEXT_INPUT_DESCRIPTION)
		parserElements.add(TEXT_INPUT_NAME)
		parserElements.add(TEXT_INPUT_LINK)
		parserElements.add(CHANNEL_ITUNES_AUTHOR)
		parserElements.add(CHANNEL_ITUNES_BLOCK)
		parserElements.add(CHANNEL_ITUNES_EXPLICIT)
		parserElements.add(CHANNEL_ITUNES_COMPLETE)
		parserElements.add(CHANNEL_ITUNES_NEW_FEED_URL)
		parserElements.add(CHANNEL_ITUNES_OWNER_EMAIL)
		parserElements.add(CHANNEL_ITUNES_OWNER_NAME)
		parserElements.add(CHANNEL_ITUNES_TITLE)
		parserElements.add(CHANNEL_ITUNES_SUBTITLE)
		parserElements.add(CHANNEL_ITUNES_SUMMARY)
		parserElements.add(CHANNEL_ITUNES_KEYWORDS)
		parserElements.add(CHANNEL_ITUNES_TYPE)
		parserElements.add(CHANNEL_ITEM_ITUNES_AUTHOR)
		parserElements.add(CHANNEL_ITEM_ITUNES_BLOCK)
		parserElements.add(CHANNEL_ITEM_ITUNES_DURATION)
		parserElements.add(CHANNEL_ITEM_ITUNES_EXPLICIT)
		parserElements.add(CHANNEL_ITEM_ITUNES_IS_CLOSED_CAPTIONED)
		parserElements.add(CHANNEL_ITEM_ITUNES_ORDER)
		parserElements.add(CHANNEL_ITEM_ITUNES_TITLE)
		parserElements.add(CHANNEL_ITEM_ITUNES_SUBTITLE)
		parserElements.add(CHANNEL_ITEM_ITUNES_SUMMARY)
		parserElements.add(CHANNEL_ITEM_ITUNES_KEYWORDS)
		parserElements.add(CHANNEL_ITEM_ITUNES_EPISODE_TYPE)
		parserElements.add(CHANNEL_ITEM_ITUNES_SEASON)
		parserElements.add(CHANNEL_ITEM_ITUNES_EPISODE)
		parserElements.add(CHANNEL_ITEM_MEDIA_RATING)
		parserElements.add(CHANNEL_ITEM_MEDIA_TITLE)
		parserElements.add(CHANNEL_ITEM_MEDIA_DESCRIPTION)
		parserElements.add(CHANNEL_ITEM_MEDIA_KEYWORDS)
		parserElements.add(CHANNEL_ITEM_MEDIA_CATEGORY)
		parserElements.add(CHANNEL_ITEM_MEDIA_CREDIT)
		parserElements.add(CHANNEL_ITEM_MEDIA_COPYRIGHT)
		parserElements.add(CHANNEL_ITEM_MEDIA_TEXT)
		parserElements.add(CHANNEL_ITEM_MEDIA_CONTENT_TITLE)
		parserElements.add(CHANNEL_ITEM_MEDIA_CONTENT_DESCRIPTION)
		parserElements.add(CHANNEL_ITEM_MEDIA_CONTENT_HASH)
		parserElements.add(CHANNEL_ITEM_MEDIA_CONTENT_CREDIT)
		parserElements.add(CHANNEL_ITEM_MEDIA_CONTENT_KEYWORDS)
		parserElements.add(CHANNEL_ITEM_MEDIA_CONTENT_CATEGORY)
		parserElements.add(CHANNEL_ITEM_MEDIA_CONTENT_TEXT)
		parserElements.add(CHANNEL_ITEM_MEDIA_CONTENT_RATING)
		parserElements.add(CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_TAGS)
		parserElements.add(CHANNEL_ITEM_MEDIA_COMMENTS_MEDIA_COMMENT)
		parserElements.add(CHANNEL_ITEM_MEDIA_EMBED_MEDIA_PARAM)
		parserElements.add(CHANNEL_ITEM_MEDIA_RESPONSES_MEDIA_RESPONSE)
		parserElements.add(CHANNEL_ITEM_MEDIA_BACK_LINKS_BACK_LINK)
		parserElements.add(CHANNEL_ITEM_MEDIA_LICENSE)
		parserElements.add(CHANNEL_ITEM_MEDIA_LOCATION_POSITION)
		parserElements.add(CHANNEL_ITEM_MEDIA_RESTRICTION)
		parserElements.add(CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_TITLE)
		parserElements.add(CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_DESCRIPTION)
		parserElements.add(CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_START_TIME)
		parserElements.add(CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_END_TIME)
		parserElements.add(CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CREDIT)
		parserElements.add(CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CATEGORY)
		parserElements.add(CHANNEL_ITEM_MEDIA_GROUP_MEDIA_RATING)
		parserElements.add(CHANNEL_ITEM_CONTENT_ENCODED)
		parserElements.add(CHANNEL_SYNDICATION_UPDATE_PERIOD)
		parserElements.add(CHANNEL_SYNDICATION_UPDATE_FREQUENCY)
		parserElements.add(CHANNEL_SYNDICATION_UPDATE_BASE)

		parserElements.add(CHANNEL_DUBLIN_CORE_TITLE)
		parserElements.add(CHANNEL_DUBLIN_CORE_CREATOR)
		parserElements.add(CHANNEL_DUBLIN_CORE_SUBJECT)
		parserElements.add(CHANNEL_DUBLIN_CORE_DESCRIPTION)
		parserElements.add(CHANNEL_DUBLIN_CORE_PUBLISHER)
		parserElements.add(CHANNEL_DUBLIN_CORE_CONTRIBUTOR)
		parserElements.add(CHANNEL_DUBLIN_CORE_DATE)
		parserElements.add(CHANNEL_DUBLIN_CORE_TYPE)
		parserElements.add(CHANNEL_DUBLIN_CORE_FORMAT)
		parserElements.add(CHANNEL_DUBLIN_CORE_IDENTIFIER)
		parserElements.add(CHANNEL_DUBLIN_CORE_SOURCE)
		parserElements.add(CHANNEL_DUBLIN_CORE_LANGUAGE)
		parserElements.add(CHANNEL_DUBLIN_CORE_RELATION)
		parserElements.add(CHANNEL_DUBLIN_CORE_COVERAGE)
		parserElements.add(CHANNEL_DUBLIN_CORE_RIGHTS)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_TITLE)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_CREATOR)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_SUBJECT)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_DESCRIPTION)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_PUBLISHER)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_CONTRIBUTOR)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_DATE)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_TYPE)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_FORMAT)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_IDENTIFIER)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_SOURCE)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_LANGUAGE)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_RELATION)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_COVERAGE)
		parserElements.add(CHANNEL_ITEM_DUBLIN_CORE_RIGHTS)
		parserElements.add(CHANNEL_ATOM_TITLE)
		parserElements.add(CHANNEL_ATOM_SUBTITLE)
		parserElements.add(CHANNEL_ATOM_UPDATED)
		parserElements.add(CHANNEL_ATOM_AUTHOR_NAME)
		parserElements.add(CHANNEL_ATOM_AUTHOR_EMAIL)
		parserElements.add(CHANNEL_ATOM_AUTHOR_URI)
		parserElements.add(CHANNEL_ATOM_CONTRIBUTOR_NAME)
		parserElements.add(CHANNEL_ATOM_CONTRIBUTOR_EMAIL)
		parserElements.add(CHANNEL_ATOM_CONTRIBUTOR_URI)
		parserElements.add(CHANNEL_ATOM_ID)
		parserElements.add(CHANNEL_ATOM_GENERATOR)
		parserElements.add(CHANNEL_ATOM_ICON)
		parserElements.add(CHANNEL_ATOM_LOGO)
		parserElements.add(CHANNEL_ATOM_RIGHTS)
		parserElements.add(CHANNEL_ITEM_ATOM_TITLE)
		parserElements.add(CHANNEL_ITEM_ATOM_SUMMARY)
		parserElements.add(CHANNEL_ITEM_ATOM_UPDATED)
		parserElements.add(CHANNEL_ITEM_ATOM_ID)
		parserElements.add(CHANNEL_ITEM_ATOM_CONTENT)
		parserElements.add(CHANNEL_ITEM_ATOM_PUBLISHED)
		parserElements.add(CHANNEL_ITEM_ATOM_SOURCE_ID)
		parserElements.add(CHANNEL_ITEM_ATOM_SOURCE_TITLE)
		parserElements.add(CHANNEL_ITEM_ATOM_SOURCE_UPDATED)
		parserElements.add(CHANNEL_ITEM_ATOM_RIGHTS)
		parserElements.add(CHANNEL_ITEM_ATOM_AUTHOR_NAME)
		parserElements.add(CHANNEL_ITEM_ATOM_AUTHOR_EMAIL)
		parserElements.add(CHANNEL_ITEM_ATOM_AUTHOR_URI)
		parserElements.add(CHANNEL_ITEM_ATOM_CONTRIBUTOR_NAME)
		parserElements.add(CHANNEL_ITEM_ATOM_CONTRIBUTOR_EMAIL)
		parserElements.add(CHANNEL_ITEM_ATOM_CONTRIBUTOR_URI)

		parserElements.forEach { parserElement ->
			parserElement.classify() `should be instance of` ValueEvent.javaClass
		}
	}
}