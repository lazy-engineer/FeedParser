package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaEmbedParam(
	val value: String,
	val attributes: Attributes?
) {
	data class Attributes(var name: String?) {
		constructor(attributes: Map<String, String>) : this(
				name = attributes["name"]
		)
	}
}