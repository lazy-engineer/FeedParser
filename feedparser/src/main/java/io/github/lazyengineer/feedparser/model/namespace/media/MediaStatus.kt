package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaStatus(val attributes: Attributes?) {
	data class Attributes(
		var state: String?,
		var reason: String?
	) {
		constructor(attributes: Map<String, String>) : this(
				state = attributes["state"],
				reason = attributes["reason"]
		)
	}
}