package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaHash(val value: String?, val attributes: Attributes?) {
	data class Attributes(var algo: String?) {
		constructor(attributes: Map<String, String>) : this(
			algo = attributes["algo"]
		)
	}
}
