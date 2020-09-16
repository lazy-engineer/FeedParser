package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaContent(
	var title: MediaTitle? = null,
	var description: MediaDescription? = null,
	var credits: MutableList<MediaCredit> = mutableListOf(),
	var category: MediaCategory? = null,
	var rating: MediaRating? = null,
	var keywords: MutableList<String> = mutableListOf(),
	var thumbnails: MutableList<MediaThumbnail> = mutableListOf(),
	val attributes: Attributes?
) {

	data class Attributes(
		var url: String?,
		var fileSize: Int?,
		var type: String?,
		var medium: String?,
		var isDefault: Boolean?,
		var expression: String?,
		var bitrate: Int?,
		var framerate: Double?,
		var samplingrate: Double?,
		var channels: Int?,
		var duration: Int?,
		var height: Int?,
		var width: Int?,
		var lang: String?

	) {

		constructor(attributes: Map<String, String>) : this(
				url = attributes["url"],
				fileSize = attributes["fileSize"]?.toInt(),
				type = attributes["type"],
				medium = attributes["medium"],
				isDefault = attributes["isDefault"]?.toBoolean(),
				expression = attributes["expression"],
				bitrate = attributes["bitrate"]?.toInt(),
				framerate = attributes["framerate"]?.toDouble(),
				samplingrate = attributes["samplingrate"]?.toDouble(),
				channels = attributes["channels"]?.toInt(),
				duration = attributes["duration"]?.toInt(),
				height = attributes["height"]?.toInt(),
				width = attributes["width"]?.toInt(),
				lang = attributes["lang"]
		)
	}
}
