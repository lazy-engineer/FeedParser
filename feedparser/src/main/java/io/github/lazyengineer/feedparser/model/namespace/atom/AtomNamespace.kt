package io.github.lazyengineer.feedparser.model.namespace.atom

import java.util.Date

data class AtomNamespace(
	var id: String? = null,
	var authors: MutableList<AtomAuthor> = mutableListOf(),
	var categories: MutableList<AtomCategory> = mutableListOf(),
	var contributors: MutableList<AtomContributor> = mutableListOf(),
	var generator: AtomGenerator? = null,
	var icon: String? = null,
	var logo: String? = null,
	var links: MutableList<AtomLink> = mutableListOf(),
	var rights: String? = null,
	var subtitle: AtomSubtitle? = null,
	var title: AtomTitle? = null,
	var updated: Date? = null,
	var content: AtomContent? = null,
	var published: Date? = null,
	var source: AtomSource? = null,
	var summary: AtomSummary? = null
)
