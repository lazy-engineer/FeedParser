package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaEmbed(
	var params: MutableList<MediaEmbedParam> = mutableListOf(),
	val attributes: Attributes?
) {
	data class Attributes(
		var url: String?,
		var height: Int?,
		var width: Int?
	) {
		constructor(attributes: Map<String, String>) : this(
				url = attributes["url"],
				height = attributes["height"]?.toInt(),
				width = attributes["width"]?.toInt()
		)
	}
}