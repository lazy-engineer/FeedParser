package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaRights(val attributes: Attributes?) {
	data class Attributes(var status: String?) {
		constructor(attributes: Map<String, String>) : this(
			status = attributes["status"]
		)
	}
}