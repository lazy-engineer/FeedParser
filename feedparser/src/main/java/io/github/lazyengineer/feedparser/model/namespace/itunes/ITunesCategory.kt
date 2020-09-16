package io.github.lazyengineer.feedparser.model.namespace.itunes

data class ITunesCategory(
	var subcategory: ITunesSubCategory? = null,
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
