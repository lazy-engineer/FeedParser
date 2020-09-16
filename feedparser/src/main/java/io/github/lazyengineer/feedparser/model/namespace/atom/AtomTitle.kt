package io.github.lazyengineer.feedparser.model.namespace.atom

data class AtomTitle(
	val value: String,
	val attributes: Attributes?
) {
	data class Attributes(var type: String?) {
		constructor(attributes: Map<String, String>) : this(
				type = attributes["type"]
		)
	}
}
