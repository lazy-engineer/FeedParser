package io.github.lazyengineer.feedparser.model.namespace.dublincore

import java.util.Date

data class DublinCoreNamespace(
	var title: String? = null,
	var creator: String? = null,
	var subject: String? = null,
	var description: String? = null,
	var publisher: String? = null,
	var contributor: String? = null,
	var date: Date? = null,
	var type: String? = null,
	var format: String? = null,
	var identifier: String? = null,
	var source: String? = null,
	var language: String? = null,
	var relation: String? = null,
	var coverage: String? = null,
	var rights: String? = null
)