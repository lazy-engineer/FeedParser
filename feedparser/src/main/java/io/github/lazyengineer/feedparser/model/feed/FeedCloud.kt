package io.github.lazyengineer.feedparser.model.feed

data class FeedCloud(
	val domain: String?,
	val port: Int?,
	val path: String?,
	val registerProcedure: String?,
	val protocol: String?
)
