package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaSubTitle(val attributes: Attributes?) {
	data class Attributes(var href: String?, var lang: String?, var type: String?) {
		constructor(attributes: Map<String, String>) : this(
			href = attributes["href"],
			lang = attributes["lang"],
			type = attributes["type"]
		)
	}
}