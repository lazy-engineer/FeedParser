package io.github.lazyengineer.feedparser.rss

import io.github.lazyengineer.feedparser.AttributeEvent
import io.github.lazyengineer.feedparser.ContainerEvent
import io.github.lazyengineer.feedparser.ParserEvent
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

fun RSSParserElement.classify(): ParserEvent = when (this) {
	CHANNEL_TITLE -> ValueEvent
	CHANNEL_LINK -> ValueEvent
	CHANNEL_DESCRIPTION -> ValueEvent
	CHANNEL_CATEGORY -> ValueEvent
	CHANNEL_CLOUD -> AttributeEvent
	CHANNEL_COPYRIGHT -> ValueEvent
	CHANNEL_DOCS -> ValueEvent
	CHANNEL_GENERATOR -> ValueEvent
	CHANNEL_LANGUAGE -> ValueEvent
	CHANNEL_LAST_BUILD_DATE -> ValueEvent
	CHANNEL_MANAGING_EDITOR -> ValueEvent
	CHANNEL_PUB_DATE -> ValueEvent
	CHANNEL_RATING -> ValueEvent
	CHANNEL_SKIP_DAYS -> ContainerEvent
	CHANNEL_SKIP_DAY -> ValueEvent
	CHANNEL_SKIP_HOURS -> ContainerEvent
	CHANNEL_SKIP_HOUR -> ValueEvent
	CHANNEL_TTL -> ValueEvent
	CHANNEL_WEB_MASTER -> ValueEvent
	CHANNEL_IMAGE -> ContainerEvent
	CHANNEL_TEXT_INPUT -> ContainerEvent
	CHANNEL_ITEM -> ContainerEvent
	ITEM_TITLE -> ValueEvent
	ITEM_LINK -> ValueEvent
	ITEM_DESCRIPTION -> ValueEvent
	ITEM_ENCLOSURE -> AttributeEvent
	ITEM_AUTHOR -> ValueEvent
	ITEM_SOURCE -> ValueEvent
	ITEM_CATEGORY -> ValueEvent
	ITEM_COMMENTS -> ValueEvent
	ITEM_GUID -> ValueEvent
	ITEM_PUB_DATE -> ValueEvent
	IMAGE_URL -> ValueEvent
	IMAGE_TITLE -> ValueEvent
	IMAGE_LINK -> ValueEvent
	IMAGE_WIDTH -> ValueEvent
	IMAGE_HEIGHT -> ValueEvent
	IMAGE_DESCRIPTION -> ValueEvent
	TEXT_INPUT_TITLE -> ValueEvent
	TEXT_INPUT_DESCRIPTION -> ValueEvent
	TEXT_INPUT_NAME -> ValueEvent
	TEXT_INPUT_LINK -> ValueEvent

	CHANNEL_ITUNES_AUTHOR -> ValueEvent
	CHANNEL_ITUNES_BLOCK -> ValueEvent
	CHANNEL_ITUNES_CATEGORY -> AttributeEvent
	CHANNEL_ITUNES_SUBCATEGORY -> AttributeEvent
	CHANNEL_ITUNES_IMAGE -> AttributeEvent
	CHANNEL_ITUNES_EXPLICIT -> ValueEvent
	CHANNEL_ITUNES_COMPLETE -> ValueEvent
	CHANNEL_ITUNES_NEW_FEED_URL -> ValueEvent
	CHANNEL_ITUNES_OWNER -> ContainerEvent
	CHANNEL_ITUNES_OWNER_EMAIL -> ValueEvent
	CHANNEL_ITUNES_OWNER_NAME -> ValueEvent
	CHANNEL_ITUNES_TITLE -> ValueEvent
	CHANNEL_ITUNES_SUBTITLE -> ValueEvent
	CHANNEL_ITUNES_SUMMARY -> ValueEvent
	CHANNEL_ITUNES_KEYWORDS -> ValueEvent
	CHANNEL_ITUNES_TYPE -> ValueEvent
	CHANNEL_ITEM_ITUNES_AUTHOR -> ValueEvent
	CHANNEL_ITEM_ITUNES_BLOCK -> ValueEvent
	CHANNEL_ITEM_ITUNES_IMAGE -> AttributeEvent
	CHANNEL_ITEM_ITUNES_DURATION -> ValueEvent
	CHANNEL_ITEM_ITUNES_EXPLICIT -> ValueEvent
	CHANNEL_ITEM_ITUNES_IS_CLOSED_CAPTIONED -> ValueEvent
	CHANNEL_ITEM_ITUNES_ORDER -> ValueEvent
	CHANNEL_ITEM_ITUNES_TITLE -> ValueEvent
	CHANNEL_ITEM_ITUNES_SUBTITLE -> ValueEvent
	CHANNEL_ITEM_ITUNES_SUMMARY -> ValueEvent
	CHANNEL_ITEM_ITUNES_KEYWORDS -> ValueEvent
	CHANNEL_ITEM_ITUNES_EPISODE_TYPE -> ValueEvent
	CHANNEL_ITEM_ITUNES_SEASON -> ValueEvent
	CHANNEL_ITEM_ITUNES_EPISODE -> ValueEvent

	CHANNEL_ITEM_MEDIA_THUMBNAIL -> AttributeEvent
	CHANNEL_ITEM_MEDIA_CONTENT -> AttributeEvent
	CHANNEL_ITEM_MEDIA_RATING -> ValueEvent
	CHANNEL_ITEM_MEDIA_TITLE -> ValueEvent
	CHANNEL_ITEM_MEDIA_DESCRIPTION -> ValueEvent
	CHANNEL_ITEM_MEDIA_KEYWORDS -> ValueEvent
	CHANNEL_ITEM_MEDIA_CATEGORY -> ValueEvent
	CHANNEL_ITEM_MEDIA_CREDIT -> ValueEvent
	CHANNEL_ITEM_MEDIA_COPYRIGHT -> ValueEvent
	CHANNEL_ITEM_MEDIA_TEXT -> ValueEvent
	CHANNEL_ITEM_MEDIA_RIGHTS -> AttributeEvent
	CHANNEL_ITEM_MEDIA_CONTENT_TITLE -> ValueEvent
	CHANNEL_ITEM_MEDIA_CONTENT_DESCRIPTION -> ValueEvent
	CHANNEL_ITEM_MEDIA_CONTENT_PLAYER -> AttributeEvent
	CHANNEL_ITEM_MEDIA_CONTENT_HASH -> ValueEvent
	CHANNEL_ITEM_MEDIA_CONTENT_CREDIT -> ValueEvent
	CHANNEL_ITEM_MEDIA_CONTENT_THUMBNAIL -> AttributeEvent
	CHANNEL_ITEM_MEDIA_CONTENT_KEYWORDS -> ValueEvent
	CHANNEL_ITEM_MEDIA_CONTENT_CATEGORY -> ValueEvent
	CHANNEL_ITEM_MEDIA_CONTENT_TEXT -> ValueEvent
	CHANNEL_ITEM_MEDIA_CONTENT_RATING -> ValueEvent
	CHANNEL_ITEM_MEDIA_COMMUNITY -> ContainerEvent
	CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_STAR_RATING -> AttributeEvent
	CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_STATISTICS -> AttributeEvent
	CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_TAGS -> ValueEvent
	CHANNEL_ITEM_MEDIA_COMMENTS -> ContainerEvent
	CHANNEL_ITEM_MEDIA_COMMENTS_MEDIA_COMMENT -> ValueEvent
	CHANNEL_ITEM_MEDIA_EMBED -> AttributeEvent
	CHANNEL_ITEM_MEDIA_EMBED_MEDIA_PARAM -> ValueEvent
	CHANNEL_ITEM_MEDIA_RESPONSES -> ContainerEvent
	CHANNEL_ITEM_MEDIA_RESPONSES_MEDIA_RESPONSE -> ValueEvent
	CHANNEL_ITEM_MEDIA_BACK_LINKS -> ContainerEvent
	CHANNEL_ITEM_MEDIA_BACK_LINKS_BACK_LINK -> ValueEvent
	CHANNEL_ITEM_MEDIA_STATUS -> AttributeEvent
	CHANNEL_ITEM_MEDIA_PRICE -> AttributeEvent
	CHANNEL_ITEM_MEDIA_LICENSE -> ValueEvent
	CHANNEL_ITEM_MEDIA_SUB_TITLE -> AttributeEvent
	CHANNEL_ITEM_MEDIA_PEER_LINK -> AttributeEvent
	CHANNEL_ITEM_MEDIA_LOCATION -> AttributeEvent
	CHANNEL_ITEM_MEDIA_LOCATION_WHERE_POSITION -> ContainerEvent
	CHANNEL_ITEM_MEDIA_LOCATION_POINT_POSITION -> ContainerEvent
	CHANNEL_ITEM_MEDIA_LOCATION_POSITION -> ValueEvent
	CHANNEL_ITEM_MEDIA_RESTRICTION -> ValueEvent
	CHANNEL_ITEM_MEDIA_SCENES -> ContainerEvent
	CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE -> ContainerEvent
	CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_TITLE -> ValueEvent
	CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_DESCRIPTION -> ValueEvent
	CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_START_TIME -> ValueEvent
	CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_END_TIME -> ValueEvent
	CHANNEL_ITEM_MEDIA_GROUP -> ContainerEvent
	CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CREDIT -> ValueEvent
	CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CATEGORY -> ValueEvent
	CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CONTENT -> AttributeEvent
	CHANNEL_ITEM_MEDIA_GROUP_MEDIA_RATING -> ValueEvent

	CHANNEL_ITEM_CONTENT_ENCODED -> ValueEvent

	CHANNEL_SYNDICATION_UPDATE_PERIOD -> ValueEvent
	CHANNEL_SYNDICATION_UPDATE_FREQUENCY -> ValueEvent
	CHANNEL_SYNDICATION_UPDATE_BASE -> ValueEvent

	CHANNEL_DUBLIN_CORE_TITLE -> ValueEvent
	CHANNEL_DUBLIN_CORE_CREATOR -> ValueEvent
	CHANNEL_DUBLIN_CORE_SUBJECT -> ValueEvent
	CHANNEL_DUBLIN_CORE_DESCRIPTION -> ValueEvent
	CHANNEL_DUBLIN_CORE_PUBLISHER -> ValueEvent
	CHANNEL_DUBLIN_CORE_CONTRIBUTOR -> ValueEvent
	CHANNEL_DUBLIN_CORE_DATE -> ValueEvent
	CHANNEL_DUBLIN_CORE_TYPE -> ValueEvent
	CHANNEL_DUBLIN_CORE_FORMAT -> ValueEvent
	CHANNEL_DUBLIN_CORE_IDENTIFIER -> ValueEvent
	CHANNEL_DUBLIN_CORE_SOURCE -> ValueEvent
	CHANNEL_DUBLIN_CORE_LANGUAGE -> ValueEvent
	CHANNEL_DUBLIN_CORE_RELATION -> ValueEvent
	CHANNEL_DUBLIN_CORE_COVERAGE -> ValueEvent
	CHANNEL_DUBLIN_CORE_RIGHTS -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_TITLE -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_CREATOR -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_SUBJECT -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_DESCRIPTION -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_PUBLISHER -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_CONTRIBUTOR -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_DATE -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_TYPE -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_FORMAT -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_IDENTIFIER -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_SOURCE -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_LANGUAGE -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_RELATION -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_COVERAGE -> ValueEvent
	CHANNEL_ITEM_DUBLIN_CORE_RIGHTS -> ValueEvent

	CHANNEL_ATOM_TITLE -> ValueEvent
	CHANNEL_ATOM_SUBTITLE -> ValueEvent
	CHANNEL_ATOM_LINK -> AttributeEvent
	CHANNEL_ATOM_UPDATED -> ValueEvent
	CHANNEL_ATOM_CATEGORY -> AttributeEvent
	CHANNEL_ATOM_AUTHOR -> ContainerEvent
	CHANNEL_ATOM_AUTHOR_NAME -> ValueEvent
	CHANNEL_ATOM_AUTHOR_EMAIL -> ValueEvent
	CHANNEL_ATOM_AUTHOR_URI -> ValueEvent
	CHANNEL_ATOM_CONTRIBUTOR -> ContainerEvent
	CHANNEL_ATOM_CONTRIBUTOR_NAME -> ValueEvent
	CHANNEL_ATOM_CONTRIBUTOR_EMAIL -> ValueEvent
	CHANNEL_ATOM_CONTRIBUTOR_URI -> ValueEvent
	CHANNEL_ATOM_ID -> ValueEvent
	CHANNEL_ATOM_GENERATOR -> ValueEvent
	CHANNEL_ATOM_ICON -> ValueEvent
	CHANNEL_ATOM_LOGO -> ValueEvent
	CHANNEL_ATOM_RIGHTS -> ValueEvent
	CHANNEL_ITEM_ATOM_TITLE -> ValueEvent
	CHANNEL_ITEM_ATOM_SUMMARY -> ValueEvent
	CHANNEL_ITEM_ATOM_LINK -> AttributeEvent
	CHANNEL_ITEM_ATOM_UPDATED -> ValueEvent
	CHANNEL_ITEM_ATOM_CATEGORY -> AttributeEvent
	CHANNEL_ITEM_ATOM_ID -> ValueEvent
	CHANNEL_ITEM_ATOM_CONTENT -> ValueEvent
	CHANNEL_ITEM_ATOM_PUBLISHED -> ValueEvent
	CHANNEL_ITEM_ATOM_SOURCE -> ContainerEvent
	CHANNEL_ITEM_ATOM_SOURCE_ID -> ValueEvent
	CHANNEL_ITEM_ATOM_SOURCE_TITLE -> ValueEvent
	CHANNEL_ITEM_ATOM_SOURCE_UPDATED -> ValueEvent
	CHANNEL_ITEM_ATOM_RIGHTS -> ValueEvent
	CHANNEL_ITEM_ATOM_AUTHOR -> ContainerEvent
	CHANNEL_ITEM_ATOM_AUTHOR_NAME -> ValueEvent
	CHANNEL_ITEM_ATOM_AUTHOR_EMAIL -> ValueEvent
	CHANNEL_ITEM_ATOM_AUTHOR_URI -> ValueEvent
	CHANNEL_ITEM_ATOM_CONTRIBUTOR -> ContainerEvent
	CHANNEL_ITEM_ATOM_CONTRIBUTOR_NAME -> ValueEvent
	CHANNEL_ITEM_ATOM_CONTRIBUTOR_EMAIL -> ValueEvent
	CHANNEL_ITEM_ATOM_CONTRIBUTOR_URI -> ValueEvent

	UNSUPPORTED_RSS_ELEMENT -> UnsupportedEvent
}
