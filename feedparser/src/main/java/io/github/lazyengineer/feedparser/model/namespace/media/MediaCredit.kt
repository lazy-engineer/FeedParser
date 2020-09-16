package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaCredit(
	val value: String,
	val attributes: Attributes?
) {
	data class Attributes(
		var scheme: String?,
		var role: String?
	) {
		constructor(attributes: Map<String, String>) : this(
				scheme = attributes["scheme"],
				role = attributes["role"]
		)
	}
}