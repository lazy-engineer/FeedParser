package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaStarRating(val attributes: Attributes?) {
	data class Attributes(
		var average: Double?,
		var count: Int?,
		var max: Int?,
		var min: Int?
	) {
		constructor(attributes: Map<String, String>) : this(
				average = attributes["average"]?.toDouble(),
				count = attributes["count"]?.toInt(),
				max = attributes["max"]?.toInt(),
				min = attributes["min"]?.toInt()
		)
	}
}