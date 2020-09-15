package io.github.lazyengineer.feedparser.model.namespace.syndication

import java.util.Date

data class SyndicationNamespace(
	var updatePeriod: SyndicationUpdatePeriod? = null,
	var updateFrequency: Int? = null,
	var updateBase: Date? = null
)
