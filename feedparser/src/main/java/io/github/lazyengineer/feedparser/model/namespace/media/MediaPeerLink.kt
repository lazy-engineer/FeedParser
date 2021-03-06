package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaPeerLink(val attributes: Attributes?) {
	data class Attributes(
		var href: String?,
		var type: String?
	) {
		constructor(attributes: Map<String, String>) : this(
				href = attributes["href"],
				type = attributes["type"]
		)
	}
}
