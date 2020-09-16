package io.github.lazyengineer.feedparser.model.rss

data class RSSChannelCloud(
	val attributes: Attributes
) {

	data class Attributes(
		var domain: String?,
		var port: Int?,
		var path: String?,
		var registerProcedure: String?,
		var protocol: String?
	) {

		constructor(attributes: Map<String, String>) : this(
				domain = attributes["domain"],
				port = attributes["port"]?.toInt(),
				path = attributes["path"],
				registerProcedure = attributes["registerProcedure"],
				protocol = attributes["protocol"]
		)
	}
}
