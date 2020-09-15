package io.github.lazyengineer.feedparser.model.namespace.itunes

data class ITunesImage(
	val attributes: Attributes?
) {

	data class Attributes(
		var href: String?
	) {

		constructor(attributes: Map<String, String>) : this(
			href = attributes["href"]
		)
	}
}
