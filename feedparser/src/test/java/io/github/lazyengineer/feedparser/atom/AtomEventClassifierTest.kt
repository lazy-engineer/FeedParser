package io.github.lazyengineer.feedparser.atom

import io.github.lazyengineer.feedparser.AttributeEvent
import io.github.lazyengineer.feedparser.ContainerEvent
import io.github.lazyengineer.feedparser.UnsupportedEvent
import io.github.lazyengineer.feedparser.ValueEvent
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_AUTHOR
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_AUTHOR_EMAIL
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_AUTHOR_NAME
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_AUTHOR_URI
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_CATEGORY
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_CONTENT
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_CONTRIBUTOR
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_CONTRIBUTOR_EMAIL
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_CONTRIBUTOR_NAME
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_CONTRIBUTOR_URI
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_ID
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_LINK
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_PUBLISHED
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_RIGHTS
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_SOURCE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_SOURCE_ID
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_SOURCE_TITLE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_SOURCE_UPDATED
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_SUMMARY
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_TITLE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.ENTRY_UPDATED
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_AUTHOR
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_AUTHOR_EMAIL
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_AUTHOR_NAME
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_AUTHOR_URI
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_CATEGORY
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_CONTRIBUTOR
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_CONTRIBUTOR_EMAIL
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_CONTRIBUTOR_NAME
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_CONTRIBUTOR_URI
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_BACK_LINKS
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_BACK_LINKS_BACK_LINK
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CATEGORY
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_COMMENTS
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_COMMENTS_MEDIA_COMMENT
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_COMMUNITY
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_STAR_RATING
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_STATISTICS
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_TAGS
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CONTENT
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CONTENT_CATEGORY
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CONTENT_CREDIT
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CONTENT_DESCRIPTION
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CONTENT_HASH
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CONTENT_KEYWORDS
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CONTENT_PLAYER
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CONTENT_RATING
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CONTENT_TEXT
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CONTENT_THUMBNAIL
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CONTENT_TITLE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_COPYRIGHT
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_CREDIT
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_DESCRIPTION
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_EMBED
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_EMBED_MEDIA_PARAM
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_GROUP
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_GROUP_MEDIA_CATEGORY
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_GROUP_MEDIA_CONTENT
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_GROUP_MEDIA_CREDIT
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_GROUP_MEDIA_RATING
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_KEYWORDS
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_LICENSE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_LOCATION
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_LOCATION_POINT_POSITION
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_LOCATION_POSITION
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_LOCATION_WHERE_POSITION
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_PEER_LINK
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_PRICE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_RATING
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_RESPONSES
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_RESPONSES_MEDIA_RESPONSE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_RESTRICTION
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_RIGHTS
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_SCENES
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_DESCRIPTION
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_END_TIME
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_START_TIME
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_TITLE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_STATUS
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_SUB_TITLE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_TEXT
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_THUMBNAIL
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ENTRY_MEDIA_TITLE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_GENERATOR
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ICON
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_ID
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_LINK
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_LOGO
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_RIGHTS
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_SUBTITLE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_TITLE
import io.github.lazyengineer.feedparser.atom.AtomParserElement.FEED_UPDATED
import org.amshove.kluent.`should be instance of`
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AtomEventClassifierTest {

	@Test
	fun `feed title element should be classified as value event`() {
		val parserElement: AtomParserElement = FEED_TITLE

		parserElement.classify() `should be instance of` ValueEvent.javaClass
	}

	@Test
	fun `all container elements should be classified as such`() {
		val parserElements = mutableListOf<AtomParserElement>()
		parserElements.add(FEED)
		parserElements.add(FEED_AUTHOR)
		parserElements.add(FEED_CONTRIBUTOR)
		parserElements.add(FEED_ENTRY)
		parserElements.add(ENTRY_SOURCE)
		parserElements.add(ENTRY_AUTHOR)
		parserElements.add(ENTRY_CONTRIBUTOR)
		parserElements.add(FEED_ENTRY_MEDIA_COMMUNITY)
		parserElements.add(FEED_ENTRY_MEDIA_COMMENTS)
		parserElements.add(FEED_ENTRY_MEDIA_RESPONSES)
		parserElements.add(FEED_ENTRY_MEDIA_BACK_LINKS)
		parserElements.add(FEED_ENTRY_MEDIA_LOCATION_WHERE_POSITION)
		parserElements.add(FEED_ENTRY_MEDIA_LOCATION_POINT_POSITION)
		parserElements.add(FEED_ENTRY_MEDIA_SCENES)
		parserElements.add(FEED_ENTRY_MEDIA_GROUP)
		parserElements.add(FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE)

		parserElements.forEach { parserElement ->
			parserElement.classify() `should be instance of` ContainerEvent.javaClass
		}
	}

	@Test
	fun `all attribute elements should be classified as such`() {
		val parserElements = mutableListOf<AtomParserElement>()
		parserElements.add(FEED_LINK)
		parserElements.add(FEED_CATEGORY)
		parserElements.add(ENTRY_LINK)
		parserElements.add(ENTRY_CATEGORY)

		parserElements.add(FEED_ENTRY_MEDIA_THUMBNAIL)
		parserElements.add(FEED_ENTRY_MEDIA_CONTENT)
		parserElements.add(FEED_ENTRY_MEDIA_RIGHTS)
		parserElements.add(FEED_ENTRY_MEDIA_CONTENT_PLAYER)
		parserElements.add(FEED_ENTRY_MEDIA_CONTENT_THUMBNAIL)
		parserElements.add(FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_STAR_RATING)
		parserElements.add(FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_STATISTICS)
		parserElements.add(FEED_ENTRY_MEDIA_EMBED)
		parserElements.add(FEED_ENTRY_MEDIA_STATUS)
		parserElements.add(FEED_ENTRY_MEDIA_PRICE)
		parserElements.add(FEED_ENTRY_MEDIA_SUB_TITLE)
		parserElements.add(FEED_ENTRY_MEDIA_PEER_LINK)
		parserElements.add(FEED_ENTRY_MEDIA_LOCATION)
		parserElements.add(FEED_ENTRY_MEDIA_GROUP_MEDIA_CONTENT)

		parserElements.forEach { parserElement ->
			parserElement.classify() `should be instance of` AttributeEvent.javaClass
		}
	}

	@Test
	fun `all value elements should be classified as such`() {
		val parserElements = mutableListOf<AtomParserElement>()
		parserElements.add(FEED_TITLE)
		parserElements.add(FEED_SUBTITLE)
		parserElements.add(FEED_UPDATED)
		parserElements.add(FEED_AUTHOR_NAME)
		parserElements.add(FEED_AUTHOR_EMAIL)
		parserElements.add(FEED_AUTHOR_URI)
		parserElements.add(FEED_CONTRIBUTOR_NAME)
		parserElements.add(FEED_CONTRIBUTOR_EMAIL)
		parserElements.add(FEED_CONTRIBUTOR_URI)
		parserElements.add(FEED_ID)
		parserElements.add(FEED_GENERATOR)
		parserElements.add(FEED_ICON)
		parserElements.add(FEED_LOGO)
		parserElements.add(FEED_RIGHTS)

		parserElements.add(ENTRY_TITLE)
		parserElements.add(ENTRY_SUMMARY)
		parserElements.add(ENTRY_UPDATED)
		parserElements.add(ENTRY_ID)
		parserElements.add(ENTRY_CONTENT)
		parserElements.add(ENTRY_PUBLISHED)
		parserElements.add(ENTRY_SOURCE_ID)
		parserElements.add(ENTRY_SOURCE_TITLE)
		parserElements.add(ENTRY_SOURCE_UPDATED)
		parserElements.add(ENTRY_RIGHTS)
		parserElements.add(ENTRY_AUTHOR_NAME)
		parserElements.add(ENTRY_AUTHOR_EMAIL)
		parserElements.add(ENTRY_AUTHOR_URI)
		parserElements.add(ENTRY_CONTRIBUTOR_NAME)
		parserElements.add(ENTRY_CONTRIBUTOR_EMAIL)
		parserElements.add(ENTRY_CONTRIBUTOR_URI)
		parserElements.add(FEED_ENTRY_MEDIA_RATING)
		parserElements.add(FEED_ENTRY_MEDIA_TITLE)
		parserElements.add(FEED_ENTRY_MEDIA_DESCRIPTION)
		parserElements.add(FEED_ENTRY_MEDIA_KEYWORDS)
		parserElements.add(FEED_ENTRY_MEDIA_CATEGORY)
		parserElements.add(FEED_ENTRY_MEDIA_CREDIT)
		parserElements.add(FEED_ENTRY_MEDIA_COPYRIGHT)
		parserElements.add(FEED_ENTRY_MEDIA_TEXT)
		parserElements.add(FEED_ENTRY_MEDIA_CONTENT_TITLE)
		parserElements.add(FEED_ENTRY_MEDIA_CONTENT_DESCRIPTION)
		parserElements.add(FEED_ENTRY_MEDIA_CONTENT_HASH)
		parserElements.add(FEED_ENTRY_MEDIA_CONTENT_CREDIT)
		parserElements.add(FEED_ENTRY_MEDIA_CONTENT_KEYWORDS)
		parserElements.add(FEED_ENTRY_MEDIA_CONTENT_CATEGORY)
		parserElements.add(FEED_ENTRY_MEDIA_CONTENT_TEXT)
		parserElements.add(FEED_ENTRY_MEDIA_CONTENT_RATING)
		parserElements.add(FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_TAGS)
		parserElements.add(FEED_ENTRY_MEDIA_COMMENTS_MEDIA_COMMENT)
		parserElements.add(FEED_ENTRY_MEDIA_EMBED_MEDIA_PARAM)
		parserElements.add(FEED_ENTRY_MEDIA_RESPONSES_MEDIA_RESPONSE)
		parserElements.add(FEED_ENTRY_MEDIA_BACK_LINKS_BACK_LINK)
		parserElements.add(FEED_ENTRY_MEDIA_LICENSE)
		parserElements.add(FEED_ENTRY_MEDIA_LOCATION_POSITION)
		parserElements.add(FEED_ENTRY_MEDIA_RESTRICTION)
		parserElements.add(FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_TITLE)
		parserElements.add(FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_DESCRIPTION)
		parserElements.add(FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_START_TIME)
		parserElements.add(FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_END_TIME)
		parserElements.add(FEED_ENTRY_MEDIA_GROUP_MEDIA_CREDIT)
		parserElements.add(FEED_ENTRY_MEDIA_GROUP_MEDIA_CATEGORY)
		parserElements.add(FEED_ENTRY_MEDIA_GROUP_MEDIA_RATING)

		parserElements.forEach { parserElement ->
			parserElement.classify() `should be instance of` ValueEvent.javaClass
		}
	}
}