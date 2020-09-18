package io.github.lazyengineer.feedparser.rss

import io.github.lazyengineer.feedparser.ParserElement

enum class RSSParserElement(val element: String) : ParserElement {
	// RSS <channel> Element
	RSS("/rss"),
	RSS_CHANNEL("/rss/channel"),
	CHANNEL_TITLE("/rss/channel/title"), // Required. Defines the title of the channel
	CHANNEL_LINK("/rss/channel/link"),  // Required. Defines the hyperlink to the channel
	CHANNEL_DESCRIPTION("/rss/channel/description"), // Required. Describes the channel
	CHANNEL_CATEGORY("/rss/channel/category"), // Optional. Defines one or more categories for the feed
	CHANNEL_CLOUD("/rss/channel/cloud"), // Optional. Register processes to be notified immediately of updates of the feed
	CHANNEL_COPYRIGHT("/rss/channel/copyright"), // Optional. Notifies about copyrighted material
	CHANNEL_DOCS("/rss/channel/docs"), // Optional. Specifies a URL to the documentation of the format used in the feed
	CHANNEL_GENERATOR("/rss/channel/generator"), // Optional. Specifies the program used to generate the feed
	CHANNEL_LANGUAGE("/rss/channel/language"), // Optional. Specifies the language the feed is written in
	CHANNEL_LAST_BUILD_DATE("/rss/channel/lastBuildDate"), // Optional. Defines the last-modified date of the content of the feed
	CHANNEL_MANAGING_EDITOR("/rss/channel/managingEditor"), // Optional. Defines the e-mail address to the editor of the content of the feed
	CHANNEL_PUB_DATE("/rss/channel/pubDate"), // Optional. Defines the last publication date for the content of the feed
	CHANNEL_RATING("/rss/channel/rating"), // Optional. The PICS rating of the feed
	CHANNEL_SKIP_DAYS("/rss/channel/skipDays"), // Optional. Specifies the days where aggregators should skip updating the feed
	CHANNEL_SKIP_DAY("/rss/channel/skipDays/day"), // Optional. Specifies the days where aggregators should skip updating the feed
	CHANNEL_SKIP_HOURS("/rss/channel/skipHours"), // Optional. Specifies the hours where aggregators should skip updating the feed
	CHANNEL_SKIP_HOUR("/rss/channel/skipHours/hour"), // Optional. Specifies the hours where aggregators should skip updating the feed
	CHANNEL_TTL("/rss/channel/ttl"), // Optional. Specifies the number of minutes the feed can stay cached before refreshing it from the source
	CHANNEL_WEB_MASTER("/rss/channel/webMaster"), // Optional. Defines the e-mail address to the webmaster of the feed
	CHANNEL_IMAGE("/rss/channel/image"), // Optional. Allows an image to be displayed when aggregators present a feed
	CHANNEL_TEXT_INPUT("/rss/channel/textInput"), // Optional. Specifies a text input field that should be displayed with the feed
	CHANNEL_ITEM("/rss/channel/item"),

	// RSS <item> Element
	ITEM_TITLE("/rss/channel/item/title"), // Required. Defines the title of the item
	ITEM_LINK("/rss/channel/item/link"), // Required. Defines the hyperlink to the item
	ITEM_DESCRIPTION("/rss/channel/item/description"), // Required. Describes the item
	ITEM_ENCLOSURE("/rss/channel/item/enclosure"), // Optional. Allows a media file to be included with the item
	ITEM_AUTHOR("/rss/channel/item/author"), // Optional. Specifies the e-mail address to the author of the item
	ITEM_SOURCE("/rss/channel/item/source"), // Optional. Specifies a third-party source for the item
	ITEM_CATEGORY("/rss/channel/item/category"), // Optional. Defines one or more categories the item belongs to
	ITEM_COMMENTS("/rss/channel/item/comments"), // Optional. Allows an item to link to comments about that item
	ITEM_GUID("/rss/channel/item/guid"), // Optional. Defines a unique identifier for the item
	ITEM_PUB_DATE("/rss/channel/item/pubDate"), // Optional. Defines the last-publication date for the item,

	// RSS <image> Element,
	IMAGE_URL("/rss/channel/image/url"),
	IMAGE_TITLE("/rss/channel/image/title"),
	IMAGE_LINK("/rss/channel/image/link"),
	IMAGE_WIDTH("/rss/channel/image/width"),
	IMAGE_HEIGHT("/rss/channel/image/height"),
	IMAGE_DESCRIPTION("/rss/channel/image/description"),

	// RSS <textInput> Element,
	TEXT_INPUT_TITLE("/rss/channel/textInput/title"),
	TEXT_INPUT_DESCRIPTION("/rss/channel/textInput/description"),
	TEXT_INPUT_NAME("/rss/channel/textInput/name"),
	TEXT_INPUT_LINK("/rss/channel/textInput/link"),

	// RSS iTunes Namespace
	CHANNEL_ITUNES_AUTHOR("/rss/channel/itunes:author"),
	CHANNEL_ITUNES_BLOCK("/rss/channel/itunes:block"),
	CHANNEL_ITUNES_CATEGORY("/rss/channel/itunes:category"),
	CHANNEL_ITUNES_SUBCATEGORY("/rss/channel/itunes:category/itunes:category"),
	CHANNEL_ITUNES_IMAGE("/rss/channel/itunes:image"),
	CHANNEL_ITUNES_EXPLICIT("/rss/channel/itunes:explicit"),
	CHANNEL_ITUNES_COMPLETE("/rss/channel/itunes:complete"),
	CHANNEL_ITUNES_NEW_FEED_URL("/rss/channel/itunes:new-feed-url"),
	CHANNEL_ITUNES_OWNER("/rss/channel/itunes:owner"),
	CHANNEL_ITUNES_OWNER_EMAIL("/rss/channel/itunes:owner/itunes:email"),
	CHANNEL_ITUNES_OWNER_NAME("/rss/channel/itunes:owner/itunes:name"),
	CHANNEL_ITUNES_TITLE("/rss/channel/itunes:title"),
	CHANNEL_ITUNES_SUBTITLE("/rss/channel/itunes:subtitle"),
	CHANNEL_ITUNES_SUMMARY("/rss/channel/itunes:summary"),
	CHANNEL_ITUNES_KEYWORDS("/rss/channel/itunes:keywords"),
	CHANNEL_ITUNES_TYPE("/rss/channel/itunes:type"),

	CHANNEL_ITEM_ITUNES_AUTHOR("/rss/channel/item/itunes:author"),
	CHANNEL_ITEM_ITUNES_BLOCK("/rss/channel/item/itunes:block"),
	CHANNEL_ITEM_ITUNES_IMAGE("/rss/channel/item/itunes:image"),
	CHANNEL_ITEM_ITUNES_DURATION("/rss/channel/item/itunes:duration"),
	CHANNEL_ITEM_ITUNES_EXPLICIT("/rss/channel/item/itunes:explicit"),
	CHANNEL_ITEM_ITUNES_IS_CLOSED_CAPTIONED("/rss/channel/item/itunes:isClosedCaptioned"),
	CHANNEL_ITEM_ITUNES_ORDER("/rss/channel/item/itunes:order"),
	CHANNEL_ITEM_ITUNES_TITLE("/rss/channel/item/itunes:title"),
	CHANNEL_ITEM_ITUNES_SUBTITLE("/rss/channel/item/itunes:subtitle"),
	CHANNEL_ITEM_ITUNES_SUMMARY("/rss/channel/item/itunes:summary"),
	CHANNEL_ITEM_ITUNES_KEYWORDS("/rss/channel/item/itunes:keywords"),
	CHANNEL_ITEM_ITUNES_EPISODE_TYPE("/rss/channel/item/itunes:episodeType"),
	CHANNEL_ITEM_ITUNES_SEASON("/rss/channel/item/itunes:season"),
	CHANNEL_ITEM_ITUNES_EPISODE("/rss/channel/item/itunes:episode"),

	// RSS Media Namespace
	CHANNEL_ITEM_MEDIA_THUMBNAIL("/rss/channel/item/media:thumbnail"),
	CHANNEL_ITEM_MEDIA_CONTENT("/rss/channel/item/media:content"),
	CHANNEL_ITEM_MEDIA_RATING("/rss/channel/item/media:rating"),
	CHANNEL_ITEM_MEDIA_TITLE("/rss/channel/item/media:title"),
	CHANNEL_ITEM_MEDIA_DESCRIPTION("/rss/channel/item/media:description"),
	CHANNEL_ITEM_MEDIA_KEYWORDS("/rss/channel/item/media:keywords"),
	CHANNEL_ITEM_MEDIA_CATEGORY("/rss/channel/item/media:category"),
	CHANNEL_ITEM_MEDIA_CREDIT("/rss/channel/item/media:credit"),
	CHANNEL_ITEM_MEDIA_COPYRIGHT("/rss/channel/item/media:copyright"),
	CHANNEL_ITEM_MEDIA_TEXT("/rss/channel/item/media:text"),
	CHANNEL_ITEM_MEDIA_RIGHTS("/rss/channel/item/media:rights"),
	CHANNEL_ITEM_MEDIA_CONTENT_TITLE("/rss/channel/item/media:content/media:title"),
	CHANNEL_ITEM_MEDIA_CONTENT_DESCRIPTION("/rss/channel/item/media:content/media:description"),
	CHANNEL_ITEM_MEDIA_CONTENT_PLAYER("/rss/channel/item/media:content/media:player"),
	CHANNEL_ITEM_MEDIA_CONTENT_HASH("/rss/channel/item/media:content/media:hash"),
	CHANNEL_ITEM_MEDIA_CONTENT_CREDIT("/rss/channel/item/media:content/media:credit"),
	CHANNEL_ITEM_MEDIA_CONTENT_THUMBNAIL("/rss/channel/item/media:content/media:thumbnail"),
	CHANNEL_ITEM_MEDIA_CONTENT_KEYWORDS("/rss/channel/item/media:content/media:keywords"),
	CHANNEL_ITEM_MEDIA_CONTENT_CATEGORY("/rss/channel/item/media:content/media:category"),
	CHANNEL_ITEM_MEDIA_CONTENT_TEXT("/rss/channel/item/media:content/media:text"),
	CHANNEL_ITEM_MEDIA_CONTENT_RATING("/rss/channel/item/media:content/media:rating"),
	CHANNEL_ITEM_MEDIA_COMMUNITY("/rss/channel/item/media:community"),
	CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_STAR_RATING("/rss/channel/item/media:community/media:starRating"),
	CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_STATISTICS("/rss/channel/item/media:community/media:statistics"),
	CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_TAGS("/rss/channel/item/media:community/media:tags"),
	CHANNEL_ITEM_MEDIA_COMMENTS("/rss/channel/item/media:comments"),
	CHANNEL_ITEM_MEDIA_COMMENTS_MEDIA_COMMENT("/rss/channel/item/media:comments/media:comment"),
	CHANNEL_ITEM_MEDIA_EMBED("/rss/channel/item/media:embed"),
	CHANNEL_ITEM_MEDIA_EMBED_MEDIA_PARAM("/rss/channel/item/media:embed/media:param"),
	CHANNEL_ITEM_MEDIA_RESPONSES("/rss/channel/item/media:responses"),
	CHANNEL_ITEM_MEDIA_RESPONSES_MEDIA_RESPONSE("/rss/channel/item/media:responses/media:response"),
	CHANNEL_ITEM_MEDIA_BACK_LINKS("/rss/channel/item/media:backLinks"),
	CHANNEL_ITEM_MEDIA_BACK_LINKS_BACK_LINK("/rss/channel/item/media:backLinks/media:backLink"),
	CHANNEL_ITEM_MEDIA_STATUS("/rss/channel/item/media:status"),
	CHANNEL_ITEM_MEDIA_PRICE("/rss/channel/item/media:price"),
	CHANNEL_ITEM_MEDIA_LICENSE("/rss/channel/item/media:license"),
	CHANNEL_ITEM_MEDIA_SUB_TITLE("/rss/channel/item/media:subTitle"),
	CHANNEL_ITEM_MEDIA_PEER_LINK("/rss/channel/item/media:peerLink"),
	CHANNEL_ITEM_MEDIA_LOCATION("/rss/channel/item/media:location"),
	CHANNEL_ITEM_MEDIA_LOCATION_WHERE_POSITION("/rss/channel/item/media:location/georss:where"),
	CHANNEL_ITEM_MEDIA_LOCATION_POINT_POSITION("/rss/channel/item/media:location/georss:where/gml:Point"),
	CHANNEL_ITEM_MEDIA_LOCATION_POSITION("/rss/channel/item/media:location/georss:where/gml:Point/gml:pos"),
	CHANNEL_ITEM_MEDIA_RESTRICTION("/rss/channel/item/media:restriction"),
	CHANNEL_ITEM_MEDIA_SCENES("/rss/channel/item/media:scenes"),
	CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE("/rss/channel/item/media:scenes/media:scene"),
	CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_TITLE("/rss/channel/item/media:scenes/media:scene/sceneTitle"),
	CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_DESCRIPTION("/rss/channel/item/media:scenes/media:scene/sceneDescription"),
	CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_START_TIME("/rss/channel/item/media:scenes/media:scene/sceneStartTime"),
	CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_END_TIME("/rss/channel/item/media:scenes/media:scene/sceneEndTime"),
	CHANNEL_ITEM_MEDIA_GROUP("/rss/channel/item/media:group"),
	CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CREDIT("/rss/channel/item/media:group/media:credit"),
	CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CATEGORY("/rss/channel/item/media:group/media:category"),
	CHANNEL_ITEM_MEDIA_GROUP_MEDIA_RATING("/rss/channel/item/media:group/media:rating"),
	CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CONTENT("/rss/channel/item/media:group/media:content"),

	// RSS Content Namespace
	CHANNEL_ITEM_CONTENT_ENCODED("/rss/channel/item/content:encoded"),

	// RSS Syndication Namespace
	CHANNEL_SYNDICATION_UPDATE_PERIOD("/rss/channel/sy:updatePeriod"),
	CHANNEL_SYNDICATION_UPDATE_FREQUENCY("/rss/channel/sy:updateFrequency"),
	CHANNEL_SYNDICATION_UPDATE_BASE("/rss/channel/sy:updateBase"),

	// RSS Dublin Core Namespace
	CHANNEL_DUBLIN_CORE_TITLE("/rss/channel/dc:title"),
	CHANNEL_DUBLIN_CORE_CREATOR("/rss/channel/dc:creator"),
	CHANNEL_DUBLIN_CORE_SUBJECT("/rss/channel/dc:subject"),
	CHANNEL_DUBLIN_CORE_DESCRIPTION("/rss/channel/dc:description"),
	CHANNEL_DUBLIN_CORE_PUBLISHER("/rss/channel/dc:publisher"),
	CHANNEL_DUBLIN_CORE_CONTRIBUTOR("/rss/channel/dc:contributor"),
	CHANNEL_DUBLIN_CORE_DATE("/rss/channel/dc:date"),
	CHANNEL_DUBLIN_CORE_TYPE("/rss/channel/dc:type"),
	CHANNEL_DUBLIN_CORE_FORMAT("/rss/channel/dc:format"),
	CHANNEL_DUBLIN_CORE_IDENTIFIER("/rss/channel/dc:identifier"),
	CHANNEL_DUBLIN_CORE_SOURCE("/rss/channel/dc:source"),
	CHANNEL_DUBLIN_CORE_LANGUAGE("/rss/channel/dc:language"),
	CHANNEL_DUBLIN_CORE_RELATION("/rss/channel/dc:relation"),
	CHANNEL_DUBLIN_CORE_COVERAGE("/rss/channel/dc:coverage"),
	CHANNEL_DUBLIN_CORE_RIGHTS("/rss/channel/dc:rights"),
	CHANNEL_ITEM_DUBLIN_CORE_TITLE("/rss/channel/item/dc:title"),
	CHANNEL_ITEM_DUBLIN_CORE_CREATOR("/rss/channel/item/dc:creator"),
	CHANNEL_ITEM_DUBLIN_CORE_SUBJECT("/rss/channel/item/dc:subject"),
	CHANNEL_ITEM_DUBLIN_CORE_DESCRIPTION("/rss/channel/item/dc:description"),
	CHANNEL_ITEM_DUBLIN_CORE_PUBLISHER("/rss/channel/item/dc:publisher"),
	CHANNEL_ITEM_DUBLIN_CORE_CONTRIBUTOR("/rss/channel/item/dc:contributor"),
	CHANNEL_ITEM_DUBLIN_CORE_DATE("/rss/channel/item/dc:date"),
	CHANNEL_ITEM_DUBLIN_CORE_TYPE("/rss/channel/item/dc:type"),
	CHANNEL_ITEM_DUBLIN_CORE_FORMAT("/rss/channel/item/dc:format"),
	CHANNEL_ITEM_DUBLIN_CORE_IDENTIFIER("/rss/channel/item/dc:identifier"),
	CHANNEL_ITEM_DUBLIN_CORE_SOURCE("/rss/channel/item/dc:source"),
	CHANNEL_ITEM_DUBLIN_CORE_LANGUAGE("/rss/channel/item/dc:language"),
	CHANNEL_ITEM_DUBLIN_CORE_RELATION("/rss/channel/item/dc:relation"),
	CHANNEL_ITEM_DUBLIN_CORE_COVERAGE("/rss/channel/item/dc:coverage"),
	CHANNEL_ITEM_DUBLIN_CORE_RIGHTS("/rss/channel/item/dc:rights"),

	// RSS Atom Namespace
	CHANNEL_ATOM_TITLE("/rss/channel/atom:title"),
	CHANNEL_ATOM_SUBTITLE("/rss/channel/atom:subtitle"),
	CHANNEL_ATOM_LINK("/rss/channel/atom:link"),
	CHANNEL_ATOM_UPDATED("/rss/channel/atom:updated"),
	CHANNEL_ATOM_CATEGORY("/rss/channel/atom:category"),
	CHANNEL_ATOM_AUTHOR("/rss/channel/atom:author"),
	CHANNEL_ATOM_AUTHOR_NAME("/rss/channel/atom:author/atom:name"),
	CHANNEL_ATOM_AUTHOR_EMAIL("/rss/channel/atom:author/atom:email"),
	CHANNEL_ATOM_AUTHOR_URI("/rss/channel/atom:author/atom:uri"),
	CHANNEL_ATOM_CONTRIBUTOR("/rss/channel/atom:contributor"),
	CHANNEL_ATOM_CONTRIBUTOR_NAME("/rss/channel/atom:contributor/atom:name"),
	CHANNEL_ATOM_CONTRIBUTOR_EMAIL("/rss/channel/atom:contributor/atom:email"),
	CHANNEL_ATOM_CONTRIBUTOR_URI("/rss/channel/atom:contributor/atom:uri"),
	CHANNEL_ATOM_ID("/rss/channel/atom:id"),
	CHANNEL_ATOM_GENERATOR("/rss/channel/atom:generator"),
	CHANNEL_ATOM_ICON("/rss/channel/atom:icon"),
	CHANNEL_ATOM_LOGO("/rss/channel/atom:logo"),
	CHANNEL_ATOM_RIGHTS("/rss/channel/atom:rights"),
	CHANNEL_ITEM_ATOM_TITLE("/rss/channel/item/atom:title"),
	CHANNEL_ITEM_ATOM_SUMMARY("/rss/channel/item/atom:summary"),
	CHANNEL_ITEM_ATOM_LINK("/rss/channel/item/atom:link"),
	CHANNEL_ITEM_ATOM_UPDATED("/rss/channel/item/atom:updated"),
	CHANNEL_ITEM_ATOM_CATEGORY("/rss/channel/item/atom:category"),
	CHANNEL_ITEM_ATOM_ID("/rss/channel/item/atom:id"),
	CHANNEL_ITEM_ATOM_CONTENT("/rss/channel/item/atom:content"),
	CHANNEL_ITEM_ATOM_PUBLISHED("/rss/channel/item/atom:published"),
	CHANNEL_ITEM_ATOM_SOURCE("/rss/channel/item/atom:source"),
	CHANNEL_ITEM_ATOM_SOURCE_ID("/rss/channel/item/atom:source/atom:id"),
	CHANNEL_ITEM_ATOM_SOURCE_TITLE("/rss/channel/item/atom:source/atom:title"),
	CHANNEL_ITEM_ATOM_SOURCE_UPDATED("/rss/channel/item/atom:source/atom:updated"),
	CHANNEL_ITEM_ATOM_RIGHTS("/rss/channel/item/atom:rights"),
	CHANNEL_ITEM_ATOM_AUTHOR("/rss/channel/item/atom:author"),
	CHANNEL_ITEM_ATOM_AUTHOR_NAME("/rss/channel/item/atom:author/atom:name"),
	CHANNEL_ITEM_ATOM_AUTHOR_EMAIL("/rss/channel/item/atom:author/atom:email"),
	CHANNEL_ITEM_ATOM_AUTHOR_URI("/rss/channel/item/atom:author/atom:uri"),
	CHANNEL_ITEM_ATOM_CONTRIBUTOR("/rss/channel/item/atom:contributor"),
	CHANNEL_ITEM_ATOM_CONTRIBUTOR_NAME("/rss/channel/item/atom:contributor/atom:name"),
	CHANNEL_ITEM_ATOM_CONTRIBUTOR_EMAIL("/rss/channel/item/atom:contributor/atom:email"),
	CHANNEL_ITEM_ATOM_CONTRIBUTOR_URI("/rss/channel/item/atom:contributor/atom:uri")
}
