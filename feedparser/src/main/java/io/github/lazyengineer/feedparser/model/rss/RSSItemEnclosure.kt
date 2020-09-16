package io.github.lazyengineer.feedparser.model.rss

data class RSSItemEnclosure(
	val attributes: Attributes?
) {

	data class Attributes(
		var url: String?,
		var length: Int?,
		var type: String?
	) {

		constructor(attributes: Map<String, String>) : this(
				url = attributes["url"],
				length = attributes["length"]?.toInt(),
				type = attributes["type"]
		)
	}
}
