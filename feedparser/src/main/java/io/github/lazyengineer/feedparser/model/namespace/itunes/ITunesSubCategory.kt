package io.github.lazyengineer.feedparser.model.namespace.itunes

data class ITunesSubCategory(
	val attributes: Attributes?
) {

	data class Attributes(
		var text: String?
	) {

		constructor(attributes: Map<String, String>) : this(
			text = attributes["text"]
		)
	}
}