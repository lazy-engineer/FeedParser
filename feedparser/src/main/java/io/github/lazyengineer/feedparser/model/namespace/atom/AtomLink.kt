package io.github.lazyengineer.feedparser.model.namespace.atom

data class AtomLink(val attributes: Attributes?) {
	data class Attributes(
		var href: String?,
		var rel: String?,
		var type: String?,
		var hreflang: String?,
		var title: String?,
		var length: Int?
	) {
		constructor(attributes: Map<String, String>) : this(
				href = attributes["href"],
				rel = attributes["rel"],
				type = attributes["type"],
				hreflang = attributes["hreflang"],
				title = attributes["title"],
				length = attributes["length"]?.toInt()
		)
	}
}
