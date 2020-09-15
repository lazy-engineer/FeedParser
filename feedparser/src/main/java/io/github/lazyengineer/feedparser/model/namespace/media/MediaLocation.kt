package io.github.lazyengineer.feedparser.model.namespace.media

import io.github.lazyengineer.feedparser.model.DateUtil

data class MediaLocation(
	var position: String? = null,
	val attributes: Attributes?
) {
	data class Attributes(var description: String?, var start: Long?, var end: Long?) {
		constructor(attributes: Map<String, String>) : this(
			description = attributes["description"],
			start = attributes["start"]?.let { DateUtil.parseTimeString(it) },
			end = attributes["end"]?.let { DateUtil.parseTimeString(it) }
		)
	}
}
