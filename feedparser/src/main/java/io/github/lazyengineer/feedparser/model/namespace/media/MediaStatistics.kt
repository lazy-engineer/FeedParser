package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaStatistics(val attributes: Attributes?) {
	data class Attributes(var favorites: Int?, var views: Int?) {
		constructor(attributes: Map<String, String>) : this(
			favorites = attributes["favorites"]?.toInt(),
			views = attributes["views"]?.toInt()
		)
	}
}