package io.github.lazyengineer.feedparser.model.rss

data class RSSChannelImage(
	var url: String? = null,
	var title: String? = null,
	var link: String? = null,
	var width: Int? = null,
	var height: Int? = null,
	var description: String? = null,
	var attributes: Attributes? = null
) {
	data class Attributes(var about: String?) {
		constructor(attributes: Map<String, String>) : this(about = attributes["rdf:about"])
	}
}
