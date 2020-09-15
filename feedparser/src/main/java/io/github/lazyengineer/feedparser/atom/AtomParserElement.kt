package io.github.lazyengineer.feedparser.atom

enum class AtomParserElement(val element: String) {

	// Atom <feed> Element
	FEED("/feed"),
	FEED_TITLE("/feed/title"),
	FEED_SUBTITLE("/feed/subtitle"),
	FEED_LINK("/feed/link"),
	FEED_UPDATED("/feed/updated"),
	FEED_CATEGORY("/feed/category"),
	FEED_AUTHOR("/feed/author"),
	FEED_AUTHOR_NAME("/feed/author/name"),
	FEED_AUTHOR_EMAIL("/feed/author/email"),
	FEED_AUTHOR_URI("/feed/author/uri"),
	FEED_CONTRIBUTOR("/feed/contributor"),
	FEED_CONTRIBUTOR_NAME("/feed/contributor/name"),
	FEED_CONTRIBUTOR_EMAIL("/feed/contributor/email"),
	FEED_CONTRIBUTOR_URI("/feed/contributor/uri"),
	FEED_ID("/feed/id"),
	FEED_GENERATOR("/feed/generator"),
	FEED_ICON("/feed/icon"),
	FEED_LOGO("/feed/logo"),
	FEED_RIGHTS("/feed/rights"),

	// Atom <entry> Element
	FEED_ENTRY("/feed/entry"),
	ENTRY_TITLE("/feed/entry/title"),
	ENTRY_SUMMARY("/feed/entry/summary"),
	ENTRY_LINK("/feed/entry/link"),
	ENTRY_UPDATED("/feed/entry/updated"),
	ENTRY_CATEGORY("/feed/entry/category"),
	ENTRY_ID("/feed/entry/id"),
	ENTRY_CONTENT("/feed/entry/content"),
	ENTRY_PUBLISHED("/feed/entry/published"),
	ENTRY_SOURCE("/feed/entry/source"),
	ENTRY_SOURCE_ID("/feed/entry/source/id"),
	ENTRY_SOURCE_TITLE("/feed/entry/source/title"),
	ENTRY_SOURCE_UPDATED("/feed/entry/source/updated"),
	ENTRY_RIGHTS("/feed/entry/rights"),
	ENTRY_AUTHOR("/feed/entry/author"),
	ENTRY_AUTHOR_NAME("/feed/entry/author/name"),
	ENTRY_AUTHOR_EMAIL("/feed/entry/author/email"),
	ENTRY_AUTHOR_URI("/feed/entry/author/uri"),
	ENTRY_CONTRIBUTOR("/feed/entry/contributor"),
	ENTRY_CONTRIBUTOR_NAME("/feed/entry/contributor/name"),
	ENTRY_CONTRIBUTOR_EMAIL("/feed/entry/contributor/email"),
	ENTRY_CONTRIBUTOR_URI("/feed/entry/contributor/uri"),

	// Atom Media Namespace
	FEED_ENTRY_MEDIA_THUMBNAIL("/feed/entry/media:thumbnail"),
	FEED_ENTRY_MEDIA_CONTENT("/feed/entry/media:content"),
	FEED_ENTRY_MEDIA_RATING("/feed/entry/media:rating"),
	FEED_ENTRY_MEDIA_TITLE("/feed/entry/media:title"),
	FEED_ENTRY_MEDIA_DESCRIPTION("/feed/entry/media:description"),
	FEED_ENTRY_MEDIA_KEYWORDS("/feed/entry/media:keywords"),
	FEED_ENTRY_MEDIA_CATEGORY("/feed/entry/media:category"),
	FEED_ENTRY_MEDIA_CREDIT("/feed/entry/media:credit"),
	FEED_ENTRY_MEDIA_COPYRIGHT("/feed/entry/media:copyright"),
	FEED_ENTRY_MEDIA_TEXT("/feed/entry/media:text"),
	FEED_ENTRY_MEDIA_RIGHTS("/feed/entry/media:rights"),
	FEED_ENTRY_MEDIA_CONTENT_TITLE("/feed/entry/media:content/media:title"),
	FEED_ENTRY_MEDIA_CONTENT_DESCRIPTION("/feed/entry/media:content/media:description"),
	FEED_ENTRY_MEDIA_CONTENT_PLAYER("/feed/entry/media:content/media:player"),
	FEED_ENTRY_MEDIA_CONTENT_HASH("/feed/entry/media:content/media:hash"),
	FEED_ENTRY_MEDIA_CONTENT_CREDIT("/feed/entry/media:content/media:credit"),
	FEED_ENTRY_MEDIA_CONTENT_THUMBNAIL("/feed/entry/media:content/media:thumbnail"),
	FEED_ENTRY_MEDIA_CONTENT_KEYWORDS("/feed/entry/media:content/media:keywords"),
	FEED_ENTRY_MEDIA_CONTENT_CATEGORY("/feed/entry/media:content/media:category"),
	FEED_ENTRY_MEDIA_CONTENT_TEXT("/feed/entry/media:content/media:text"),
	FEED_ENTRY_MEDIA_CONTENT_RATING("/feed/entry/media:content/media:rating"),
	FEED_ENTRY_MEDIA_COMMUNITY("/feed/entry/media:community"),
	FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_STAR_RATING("/feed/entry/media:community/media:starRating"),
	FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_STATISTICS("/feed/entry/media:community/media:statistics"),
	FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_TAGS("/feed/entry/media:community/media:tags"),
	FEED_ENTRY_MEDIA_COMMENTS("/feed/entry/media:comments"),
	FEED_ENTRY_MEDIA_COMMENTS_MEDIA_COMMENT("/feed/entry/media:comments/media:comment"),
	FEED_ENTRY_MEDIA_EMBED("/feed/entry/media:embed"),
	FEED_ENTRY_MEDIA_EMBED_MEDIA_PARAM("/feed/entry/media:embed/media:param"),
	FEED_ENTRY_MEDIA_RESPONSES("/feed/entry/media:responses"),
	FEED_ENTRY_MEDIA_RESPONSES_MEDIA_RESPONSE("/feed/entry/media:responses/media:response"),
	FEED_ENTRY_MEDIA_BACK_LINKS("/feed/entry/media:backLinks"),
	FEED_ENTRY_MEDIA_BACK_LINKS_BACK_LINK("/feed/entry/media:backLinks/media:backLink"),
	FEED_ENTRY_MEDIA_STATUS("/feed/entry/media:status"),
	FEED_ENTRY_MEDIA_PRICE("/feed/entry/media:price"),
	FEED_ENTRY_MEDIA_LICENSE("/feed/entry/media:license"),
	FEED_ENTRY_MEDIA_SUB_TITLE("/feed/entry/media:subTitle"),
	FEED_ENTRY_MEDIA_PEER_LINK("/feed/entry/media:peerLink"),
	FEED_ENTRY_MEDIA_LOCATION("/feed/entry/media:location"),
	FEED_ENTRY_MEDIA_LOCATION_WHERE_POSITION("/feed/entry/media:location/georss:where"),
	FEED_ENTRY_MEDIA_LOCATION_POINT_POSITION("/feed/entry/media:location/georss:where/gml:Point"),
	FEED_ENTRY_MEDIA_LOCATION_POSITION("/feed/entry/media:location/georss:where/gml:Point/gml:pos"),
	FEED_ENTRY_MEDIA_RESTRICTION("/feed/entry/media:restriction"),
	FEED_ENTRY_MEDIA_SCENES("/feed/entry/media:scenes"),
	FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE("/feed/entry/media:scenes/media:scene"),
	FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_TITLE("/feed/entry/media:scenes/media:scene/sceneTitle"),
	FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_DESCRIPTION("/feed/entry/media:scenes/media:scene/sceneDescription"),
	FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_START_TIME("/feed/entry/media:scenes/media:scene/sceneStartTime"),
	FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_END_TIME("/feed/entry/media:scenes/media:scene/sceneEndTime"),
	FEED_ENTRY_MEDIA_GROUP("/feed/entry/media:group"),
	FEED_ENTRY_MEDIA_GROUP_MEDIA_CREDIT("/feed/entry/media:group/media:credit"),
	FEED_ENTRY_MEDIA_GROUP_MEDIA_CATEGORY("/feed/entry/media:group/media:category"),
	FEED_ENTRY_MEDIA_GROUP_MEDIA_RATING("/feed/entry/media:group/media:rating"),
	FEED_ENTRY_MEDIA_GROUP_MEDIA_CONTENT("/feed/entry/media:group/media:content"),

	UNSUPPORTED_ATOM_ELEMENT("unsupported atom event");

	companion object {

		private const val DEFAULT_ATOM_PATH = "/feed"

		fun from(
			elementName: String,
			previousPath: String,
			depth: Int
		): AtomParserElement {
			if (depth <= 0) return UNSUPPORTED_ATOM_ELEMENT
			val eventPath = getEventPathOfElement(elementName, previousPath, depth)

			return values().find {
				it.element == eventPath
			} ?: UNSUPPORTED_ATOM_ELEMENT
		}

		private fun getEventPathOfElement(
			elementName: String,
			previousPath: String,
			depth: Int
		): String {
			var eventPath = if (previousPath.isNotEmpty()) previousPath else DEFAULT_ATOM_PATH

			val elementStack = eventPath.split("/")
				.filter { element -> element.isNotEmpty() }

			when {
				depth < elementStack.size -> eventPath = addElementToPositionOfDepth(elementName, elementStack, depth)
				depth > elementStack.size -> eventPath += "/$elementName"
				depth == elementStack.size -> if (elementName != elementStack[depth - 1]) eventPath =
					replaceElementOnSameDepth(eventPath, elementName)
			}

			return eventPath
		}

		private fun replaceElementOnSameDepth(
			eventPath: String,
			elementName: String
		) = "${eventPath.substringBeforeLast("/")}/$elementName"

		private fun addElementToPositionOfDepth(
			elementName: String,
			elementStack: List<String>,
			depth: Int
		) = "${elementPathTillDepth(elementStack, depth)}/$elementName"

		private fun elementPathTillDepth(
			elementStack: List<String>,
			depth: Int
		): String {
			var elementPath = String()
			for (i in 0 until depth - 1) {
				elementPath += "/${elementStack[i]}"
			}

			return elementPath
		}
	}
}