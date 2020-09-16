package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaCategory(
	val value: String,
	val attributes: Attributes?
) {
	data class Attributes(
		var scheme: String?,
		var label: String?
	) {
		constructor(attributes: Map<String, String>) : this(
				scheme = attributes["scheme"],
				label = attributes["label"]
		)
	}
}
