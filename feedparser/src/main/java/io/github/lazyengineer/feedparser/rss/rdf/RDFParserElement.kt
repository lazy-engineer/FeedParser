package io.github.lazyengineer.feedparser.rss.rdf

enum class RDFParserElement(val element: String) {
	RDF("/rdf:RDF"),
	RDF_CHANNEL("/rdf:RDF/channel"),
	RDF_CHANNEL_TITLE("/rdf:RDF/channel/title"),
	RDF_CHANNEL_LINK("/rdf:RDF/channel/link"),
	RDF_CHANNEL_DESCRIPTION("/rdf:RDF/channel/description"),
	RDF_CHANNEL_IMAGE("/rdf:RDF/channel/image"),
	RDF_CHANNEL_ITEMS("/rdf:RDF/channel/items"),
	RDF_CHANNEL_TEXT_INPUT("/rdf:RDF/channel/textinput"),
	RDF_CHANNEL_ITEMS_RDF_SEQ("/rdf:RDF/channel/items/rdf:Seq"),
	RDF_CHANNEL_ITEMS_RDF_SEQ_RDF_LI("/rdf:RDF/channel/items/rdf:Seq/rdf:li"),
	RDF_IMAGE("/rdf:RDF/image"),
	RDF_IMAGE_TITLE("/rdf:RDF/image/title"),
	RDF_IMAGE_URL("/rdf:RDF/image/url"),
	RDF_IMAGE_LINK("/rdf:RDF/image/link"),
	RDF_TEXT_INPUT("/rdf:RDF/textinput"),
	RDF_TEXT_INPUT_TITLE("/rdf:RDF/textinput/title"),
	RDF_TEXT_INPUT_DESCRIPTION("/rdf:RDF/textinput/description"),
	RDF_TEXT_INPUT_NAME("/rdf:RDF/textinput/name"),
	RDF_TEXT_INPUT_LINK("/rdf:RDF/textinput/link"),

	// RDF <item> Element
	RDF_ITEM("/rdf:RDF/item"),
	RDF_ITEM_TITLE("/rdf:RDF/item/title"),
	RDF_ITEM_LINK("/rdf:RDF/item/link"),
	RDF_ITEM_DESCRIPTION("/rdf:RDF/item/description"),

	// RDF Syndication Namespace
	RDF_CHANNEL_SYNDICATION_UPDATE_PERIOD("/rdf:RDF/channel/sy:updatePeriod"),
	RDF_CHANNEL_SYNDICATION_UPDATE_FREQUENCY("/rdf:RDF/channel/sy:updateFrequency"),
	RDF_CHANNEL_SYNDICATION_UPDATE_BASE("/rdf:RDF/channel/sy:updateBase"),

	// RDF Dublin Core Namespace
	RDF_CHANNEL_DUBLIN_CORE_TITLE("/rdf:RDF/channel/dc:title"),
	RDF_CHANNEL_DUBLIN_CORE_CREATOR("/rdf:RDF/channel/dc:creator"),
	RDF_CHANNEL_DUBLIN_CORE_SUBJECT("/rdf:RDF/channel/dc:subject"),
	RDF_CHANNEL_DUBLIN_CORE_DESCRIPTION("/rdf:RDF/channel/dc:description"),
	RDF_CHANNEL_DUBLIN_CORE_PUBLISHER("/rdf:RDF/channel/dc:publisher"),
	RDF_CHANNEL_DUBLIN_CORE_CONTRIBUTOR("/rdf:RDF/channel/dc:contributor"),
	RDF_CHANNEL_DUBLIN_CORE_DATE("/rdf:RDF/channel/dc:date"),
	RDF_CHANNEL_DUBLIN_CORE_TYPE("/rdf:RDF/channel/dc:type"),
	RDF_CHANNEL_DUBLIN_CORE_FORMAT("/rdf:RDF/channel/dc:format"),
	RDF_CHANNEL_DUBLIN_CORE_IDENTIFIER("/rdf:RDF/channel/dc:identifier"),
	RDF_CHANNEL_DUBLIN_CORE_SOURCE("/rdf:RDF/channel/dc:source"),
	RDF_CHANNEL_DUBLIN_CORE_LANGUAGE("/rdf:RDF/channel/dc:language"),
	RDF_CHANNEL_DUBLIN_CORE_RELATION("/rdf:RDF/channel/dc:relation"),
	RDF_CHANNEL_DUBLIN_CORE_COVERAGE("/rdf:RDF/channel/dc:coverage"),
	RDF_CHANNEL_DUBLIN_CORE_RIGHTS("/rdf:RDF/channel/dc:rights"),
	RDF_ITEM_DUBLIN_CORE_TITLE("/rdf:RDF/item/dc:title"),
	RDF_ITEM_DUBLIN_CORE_CREATOR("/rdf:RDF/item/dc:creator"),
	RDF_ITEM_DUBLIN_CORE_SUBJECT("/rdf:RDF/item/dc:subject"),
	RDF_ITEM_DUBLIN_CORE_DESCRIPTION("/rdf:RDF/item/dc:description"),
	RDF_ITEM_DUBLIN_CORE_PUBLISHER("/rdf:RDF/item/dc:publisher"),
	RDF_ITEM_DUBLIN_CORE_CONTRIBUTOR("/rdf:RDF/item/dc:contributor"),
	RDF_ITEM_DUBLIN_CORE_DATE("/rdf:RDF/item/dc:date"),
	RDF_ITEM_DUBLIN_CORE_TYPE("/rdf:RDF/item/dc:type"),
	RDF_ITEM_DUBLIN_CORE_FORMAT("/rdf:RDF/item/dc:format"),
	RDF_ITEM_DUBLIN_CORE_IDENTIFIER("/rdf:RDF/item/dc:identifier"),
	RDF_ITEM_DUBLIN_CORE_SOURCE("/rdf:RDF/item/dc:source"),
	RDF_ITEM_DUBLIN_CORE_LANGUAGE("/rdf:RDF/item/dc:language"),
	RDF_ITEM_DUBLIN_CORE_RELATION("/rdf:RDF/item/dc:relation"),
	RDF_ITEM_DUBLIN_CORE_COVERAGE("/rdf:RDF/item/dc:coverage"),
	RDF_ITEM_DUBLIN_CORE_RIGHTS("/rdf:RDF/item/dc:rights"),

	// RDF Content Namespace
	RDF_ITEM_CONTENT_ENCODED("/rdf:RDF/item/content:encoded"),

	UNSUPPORTED_RDF_ELEMENT("unsupported rdf event");

	companion object {

		private const val DEFAULT_RDF_PATH = "/rdf:RDF"

		fun from(
			elementName: String,
			previousPath: String,
			depth: Int
		): RDFParserElement {
			if (depth <= 0) return UNSUPPORTED_RDF_ELEMENT
			val eventPath = getEventPathOfElement(elementName, previousPath, depth)

			return values().find {
				it.element == eventPath
			} ?: UNSUPPORTED_RDF_ELEMENT
		}

		private fun getEventPathOfElement(
			elementName: String,
			previousPath: String,
			depth: Int
		): String {
			var eventPath = if (previousPath.isNotEmpty()) previousPath else DEFAULT_RDF_PATH

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