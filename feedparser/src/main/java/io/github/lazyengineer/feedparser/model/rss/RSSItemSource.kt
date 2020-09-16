package io.github.lazyengineer.feedparser.model.rss

data class RSSItemSource(
	val value: String?,
	val attributes: Attributes?
) {

	data class Attributes(
		var url: String?
	) {

		constructor(attributes: Map<String, String>) : this(
				url = attributes["url"]
		)
	}
}
