package io.github.lazyengineer.feedparser.atom

import io.github.lazyengineer.feedparser.AttributeEvent
import io.github.lazyengineer.feedparser.ContainerEvent
import io.github.lazyengineer.feedparser.ParserEvent
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

fun AtomParserElement.classify(): ParserEvent = when (this) {
	FEED -> ContainerEvent
	FEED_TITLE -> ValueEvent
	FEED_SUBTITLE -> ValueEvent
	FEED_LINK -> AttributeEvent
	FEED_UPDATED -> ValueEvent
	FEED_CATEGORY -> AttributeEvent
	FEED_AUTHOR -> ContainerEvent
	FEED_AUTHOR_NAME -> ValueEvent
	FEED_AUTHOR_EMAIL -> ValueEvent
	FEED_AUTHOR_URI -> ValueEvent
	FEED_CONTRIBUTOR -> ContainerEvent
	FEED_CONTRIBUTOR_NAME -> ValueEvent
	FEED_CONTRIBUTOR_EMAIL -> ValueEvent
	FEED_CONTRIBUTOR_URI -> ValueEvent
	FEED_ID -> ValueEvent
	FEED_GENERATOR -> ValueEvent
	FEED_ICON -> ValueEvent
	FEED_LOGO -> ValueEvent
	FEED_RIGHTS -> ValueEvent
	FEED_ENTRY -> ContainerEvent

	ENTRY_TITLE -> ValueEvent
	ENTRY_SUMMARY -> ValueEvent
	ENTRY_LINK -> AttributeEvent
	ENTRY_UPDATED -> ValueEvent
	ENTRY_CATEGORY -> AttributeEvent
	ENTRY_ID -> ValueEvent
	ENTRY_CONTENT -> ValueEvent
	ENTRY_PUBLISHED -> ValueEvent
	ENTRY_SOURCE -> ContainerEvent
	ENTRY_SOURCE_ID -> ValueEvent
	ENTRY_SOURCE_TITLE -> ValueEvent
	ENTRY_SOURCE_UPDATED -> ValueEvent
	ENTRY_RIGHTS -> ValueEvent
	ENTRY_AUTHOR -> ContainerEvent
	ENTRY_AUTHOR_NAME -> ValueEvent
	ENTRY_AUTHOR_EMAIL -> ValueEvent
	ENTRY_AUTHOR_URI -> ValueEvent
	ENTRY_CONTRIBUTOR -> ContainerEvent
	ENTRY_CONTRIBUTOR_NAME -> ValueEvent
	ENTRY_CONTRIBUTOR_EMAIL -> ValueEvent
	ENTRY_CONTRIBUTOR_URI -> ValueEvent

	FEED_ENTRY_MEDIA_THUMBNAIL -> AttributeEvent
	FEED_ENTRY_MEDIA_CONTENT -> AttributeEvent
	FEED_ENTRY_MEDIA_RATING -> ValueEvent
	FEED_ENTRY_MEDIA_TITLE -> ValueEvent
	FEED_ENTRY_MEDIA_DESCRIPTION -> ValueEvent
	FEED_ENTRY_MEDIA_KEYWORDS -> ValueEvent
	FEED_ENTRY_MEDIA_CATEGORY -> ValueEvent
	FEED_ENTRY_MEDIA_CREDIT -> ValueEvent
	FEED_ENTRY_MEDIA_COPYRIGHT -> ValueEvent
	FEED_ENTRY_MEDIA_TEXT -> ValueEvent
	FEED_ENTRY_MEDIA_RIGHTS -> AttributeEvent
	FEED_ENTRY_MEDIA_CONTENT_TITLE -> ValueEvent
	FEED_ENTRY_MEDIA_CONTENT_DESCRIPTION -> ValueEvent
	FEED_ENTRY_MEDIA_CONTENT_PLAYER -> AttributeEvent
	FEED_ENTRY_MEDIA_CONTENT_HASH -> ValueEvent
	FEED_ENTRY_MEDIA_CONTENT_CREDIT -> ValueEvent
	FEED_ENTRY_MEDIA_CONTENT_THUMBNAIL -> AttributeEvent
	FEED_ENTRY_MEDIA_CONTENT_KEYWORDS -> ValueEvent
	FEED_ENTRY_MEDIA_CONTENT_CATEGORY -> ValueEvent
	FEED_ENTRY_MEDIA_CONTENT_TEXT -> ValueEvent
	FEED_ENTRY_MEDIA_CONTENT_RATING -> ValueEvent
	FEED_ENTRY_MEDIA_COMMUNITY -> ContainerEvent
	FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_STAR_RATING -> AttributeEvent
	FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_STATISTICS -> AttributeEvent
	FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_TAGS -> ValueEvent
	FEED_ENTRY_MEDIA_COMMENTS -> ContainerEvent
	FEED_ENTRY_MEDIA_COMMENTS_MEDIA_COMMENT -> ValueEvent
	FEED_ENTRY_MEDIA_EMBED -> AttributeEvent
	FEED_ENTRY_MEDIA_EMBED_MEDIA_PARAM -> ValueEvent
	FEED_ENTRY_MEDIA_RESPONSES -> ContainerEvent
	FEED_ENTRY_MEDIA_RESPONSES_MEDIA_RESPONSE -> ValueEvent
	FEED_ENTRY_MEDIA_BACK_LINKS -> ContainerEvent
	FEED_ENTRY_MEDIA_BACK_LINKS_BACK_LINK -> ValueEvent
	FEED_ENTRY_MEDIA_STATUS -> AttributeEvent
	FEED_ENTRY_MEDIA_PRICE -> AttributeEvent
	FEED_ENTRY_MEDIA_LICENSE -> ValueEvent
	FEED_ENTRY_MEDIA_SUB_TITLE -> AttributeEvent
	FEED_ENTRY_MEDIA_PEER_LINK -> AttributeEvent
	FEED_ENTRY_MEDIA_LOCATION -> AttributeEvent
	FEED_ENTRY_MEDIA_LOCATION_WHERE_POSITION -> ContainerEvent
	FEED_ENTRY_MEDIA_LOCATION_POINT_POSITION -> ContainerEvent
	FEED_ENTRY_MEDIA_LOCATION_POSITION -> ValueEvent
	FEED_ENTRY_MEDIA_RESTRICTION -> ValueEvent
	FEED_ENTRY_MEDIA_SCENES -> ContainerEvent
	FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE -> ContainerEvent
	FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_TITLE -> ValueEvent
	FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_DESCRIPTION -> ValueEvent
	FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_START_TIME -> ValueEvent
	FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_END_TIME -> ValueEvent
	FEED_ENTRY_MEDIA_GROUP -> ContainerEvent
	FEED_ENTRY_MEDIA_GROUP_MEDIA_CREDIT -> ValueEvent
	FEED_ENTRY_MEDIA_GROUP_MEDIA_CATEGORY -> ValueEvent
	FEED_ENTRY_MEDIA_GROUP_MEDIA_CONTENT -> AttributeEvent
	FEED_ENTRY_MEDIA_GROUP_MEDIA_RATING -> ValueEvent
}