package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaTitle(
	val value: String,
	val attributes: Attributes?
) {
	data class Attributes(var type: String?) {
		constructor(attributes: Map<String, String>) : this(type = attributes["type"])
	}
}
