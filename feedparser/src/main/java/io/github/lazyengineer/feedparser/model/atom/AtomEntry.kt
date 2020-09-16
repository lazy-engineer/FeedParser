package io.github.lazyengineer.feedparser.model.atom

import io.github.lazyengineer.feedparser.model.namespace.atom.AtomAuthor
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomCategory
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomContent
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomContributor
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomLink
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomSource
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomSummary
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomTitle
import io.github.lazyengineer.feedparser.model.namespace.media.MediaNamespace
import java.util.Date

data class AtomEntry(
	var id: String? = null,
	var authors: MutableList<AtomAuthor> = mutableListOf(),
	var categories: MutableList<AtomCategory> = mutableListOf(),
	var contributors: MutableList<AtomContributor> = mutableListOf(),
	var links: MutableList<AtomLink> = mutableListOf(),
	var rights: String? = null,
	var title: AtomTitle? = null,
	var updated: Date? = null,
	var content: AtomContent? = null,
	var published: Date? = null,
	var source: AtomSource? = null,
	var summary: AtomSummary? = null,
	val mediaNamespace: MediaNamespace? = MediaNamespace()
)