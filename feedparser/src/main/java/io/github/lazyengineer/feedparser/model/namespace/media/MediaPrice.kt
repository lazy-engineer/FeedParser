package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaPrice(val attributes: Attributes?) {
	data class Attributes(
		var type: String?,
		var info: String?,
		var price: Double?,
		var currency: String?
	) {
		constructor(attributes: Map<String, String>) : this(
				type = attributes["type"],
				info = attributes["info"],
				price = attributes["price"]?.toDouble(),
				currency = attributes["currency"]
		)
	}
}