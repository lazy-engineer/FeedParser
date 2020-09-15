package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaRestriction(val value: String, val attributes: Attributes?) {
	data class Attributes(var type: String?, var relationship: String?) {
		constructor(attributes: Map<String, String>) : this(
			type = attributes["type"],
			relationship = attributes["relationship"]
		)
	}
}
