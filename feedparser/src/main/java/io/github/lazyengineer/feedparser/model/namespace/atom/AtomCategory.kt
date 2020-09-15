package io.github.lazyengineer.feedparser.model.namespace.atom

data class AtomCategory(val attributes: Attributes?) {
	data class Attributes(var term: String?, var scheme: String?, var label: String?) {
		constructor(attributes: Map<String, String>) : this(
			term = attributes["term"],
			scheme = attributes["scheme"],
			label = attributes["label"]
		)
	}
}
