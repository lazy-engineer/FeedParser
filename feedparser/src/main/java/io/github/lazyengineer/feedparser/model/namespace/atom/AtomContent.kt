package io.github.lazyengineer.feedparser.model.namespace.atom

data class AtomContent(val value: String, val attributes: Attributes?) {
	data class Attributes(var type: String?, var src: String?, var lang: String?, var base: String?) {
		constructor(attributes: Map<String, String>) : this(
			type = attributes["type"],
			src = attributes["src"],
			lang = attributes["xml:lang"],
			base = attributes["xml:base"]
		)
	}
}
