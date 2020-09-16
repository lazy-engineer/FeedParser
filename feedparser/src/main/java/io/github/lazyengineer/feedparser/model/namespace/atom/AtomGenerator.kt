package io.github.lazyengineer.feedparser.model.namespace.atom

data class AtomGenerator(
	val value: String,
	val attributes: Attributes?
) {
	data class Attributes(
		var uri: String?,
		var version: String?
	) {
		constructor(attributes: Map<String, String>) : this(
				uri = attributes["uri"],
				version = attributes["version"]
		)
	}
}
