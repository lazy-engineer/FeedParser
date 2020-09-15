package io.github.lazyengineer.feedparser.model.namespace.media

data class MediaGroup(
	var contents: MutableList<MediaContent> = mutableListOf(),
	var credits: MutableList<MediaCredit> = mutableListOf(),
	var category: MediaCategory? = null,
	var rating: MediaRating? = null
)