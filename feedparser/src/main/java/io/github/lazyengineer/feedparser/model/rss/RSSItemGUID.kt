package io.github.lazyengineer.feedparser.model.rss

data class RSSItemGUID(
	val value: String,
	val attributes: Attributes?
) {

	data class Attributes(
		var isPermaLink: Boolean? = true
	) {

		constructor(attributes: Map<String, String>) : this(
			isPermaLink = attributes["isPermaLink"]?.toBoolean() ?: true
		)
	}
}
