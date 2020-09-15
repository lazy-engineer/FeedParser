package io.github.lazyengineer.feedparser.model.feed

import io.github.lazyengineer.feedparser.model.atom.AtomEntry
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomAuthor
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomCategory
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomContributor
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomGenerator
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomLink
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomNamespace
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomSubtitle
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomTitle
import io.github.lazyengineer.feedparser.model.namespace.dublincore.DublinCoreNamespace
import io.github.lazyengineer.feedparser.model.namespace.itunes.ITunes
import io.github.lazyengineer.feedparser.model.namespace.syndication.SyndicationNamespace
import io.github.lazyengineer.feedparser.model.rss.RSSChannelSkipDay
import java.util.Date

sealed class Feed

data class RSSFeed(
	val title: String,
	val link: String,
	val description: String,
	val items: List<FeedItem>,
	val categories: List<String>? = emptyList(),
	val cloud: FeedCloud? = null,
	val copyright: String? = null,
	val docs: String? = null,
	val generator: String? = null,
	val image: FeedImage? = null,
	val language: String? = null,
	val lastBuildDate: Date? = null,
	val managingEditor: String? = null,
	val pubDate: Date? = null,
	val rating: String? = null,
	val skipDays: List<RSSChannelSkipDay>? = emptyList(),
	val skipHours: List<Int>? = emptyList(),
	val textInput: FeedTextInput? = null,
	val ttl: Int? = null,
	val webMaster: String? = null,
	val about: String? = null,
	val itemsResource: String? = null,
	val imageResource: String? = null,
	val textInputResource: String? = null,
	val iTunes: ITunes? = null,
	val syndicationNamespace: SyndicationNamespace? = null,
	val dublinCoreNamespace: DublinCoreNamespace? = null,
	val atomNamespace: AtomNamespace? = null
) : Feed()

data class AtomFeed(
	val id: String? = null,
	val authors: MutableList<AtomAuthor> = mutableListOf(),
	val categories: MutableList<AtomCategory> = mutableListOf(),
	val contributors: MutableList<AtomContributor> = mutableListOf(),
	val generator: AtomGenerator? = null,
	val icon: String? = null,
	val logo: String? = null,
	val links: MutableList<AtomLink> = mutableListOf(),
	val rights: String? = null,
	val subtitle: AtomSubtitle? = null,
	val title: AtomTitle? = null,
	val updated: Date? = null,
	val entries: MutableList<AtomEntry> = mutableListOf()
) : Feed()
