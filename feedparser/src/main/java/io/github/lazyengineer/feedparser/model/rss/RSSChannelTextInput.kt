package io.github.lazyengineer.feedparser.model.rss

data class RSSChannelTextInput(
	var title: String? = null,
	var description: String? = null,
	var name: String? = null,
	var link: String? = null,
	var attributes: Attributes? = null
) {
	data class Attributes(var about: String?) {
		constructor(attributes: Map<String, String>) : this(about = attributes["rdf:about"])
	}
}
