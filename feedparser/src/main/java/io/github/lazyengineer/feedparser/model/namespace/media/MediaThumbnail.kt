package io.github.lazyengineer.feedparser.model.namespace.media

import io.github.lazyengineer.feedparser.model.DateUtil

data class MediaThumbnail(val attributes: Attributes?) {
	data class Attributes(var url: String?, var height: Int?, var width: Int?, var time: Long?) {
		constructor(attributes: Map<String, String>) : this(
			url = attributes["url"],
			height = attributes["height"]?.toInt(),
			width = attributes["width"]?.toInt(),
			time = attributes["time"]?.let { DateUtil.parseTimeString(it) }
		)
	}
}