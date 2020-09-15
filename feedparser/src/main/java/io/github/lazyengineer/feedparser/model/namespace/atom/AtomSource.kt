package io.github.lazyengineer.feedparser.model.namespace.atom

import java.util.Date

data class AtomSource(
	var id: String? = null,
	var title: String? = null,
	var updated: Date? = null
)
