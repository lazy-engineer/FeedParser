package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaRating(val value: String, val attributes: Attributes?) {
	data class Attributes(var scheme: String?) {
		constructor(attributes: Map<String, String>) : this(scheme = attributes["scheme"])
	}
}