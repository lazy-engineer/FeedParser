package io.github.lazyengineer.feedparser.model.namespace.media

import io.github.lazyengineer.feedparser.model.DateUtil

data class MediaText(val value: String, val attributes: Attributes?) {
	data class Attributes(var type: String?, var lang: String?, var start: Long?, var end: Long?) {
		constructor(attributes: Map<String, String>) : this(
			type = attributes["type"],
			lang = attributes["lang"],
			start = attributes["start"]?.let { DateUtil.parseTimeString(it) },
			end = attributes["end"]?.let { DateUtil.parseTimeString(it) }
		)
	}
}