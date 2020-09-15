package io.github.lazyengineer.feedparser.model.rss

data class RSSItemCategory(val value: String, val attributes: Attributes) {
	data class Attributes(var domain: String?) {
		constructor(attributes: Map<String, String>) : this(
			domain = attributes["domain"]
		)
	}
}
