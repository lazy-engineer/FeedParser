package io.github.lazyengineer.feedparser.model.channel

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
import io.github.lazyengineer.feedparser.model.rss.RSSChannelCategory
import io.github.lazyengineer.feedparser.model.rss.RSSChannelCloud
import io.github.lazyengineer.feedparser.model.rss.RSSChannelImage
import io.github.lazyengineer.feedparser.model.rss.RSSChannelItem
import io.github.lazyengineer.feedparser.model.rss.RSSChannelSkipDay
import io.github.lazyengineer.feedparser.model.rss.RSSChannelTextInput
import java.util.Date

sealed class FeedChannel

data class RSSChannel(
	var title: String = "No Channel Title",
	var link: String = "No Channel Link",
	var description: String = "No Channel Description",

	var language: String? = null,
	var copyright: String? = null,
	var managingEditor: String? = null,
	var webMaster: String? = null,
	var pubDate: Date? = null,
	var lastBuildDate: Date? = null,
	var categories: MutableList<RSSChannelCategory> = mutableListOf(),
	var generator: String? = null,
	var docs: String? = null,
	var cloud: RSSChannelCloud? = null,
	var rating: String? = null,
	var ttl: Int? = null,
	var image: RSSChannelImage? = null,
	var textInput: RSSChannelTextInput? = null,
	var skipHours: MutableList<Int> = mutableListOf(),
	var skipDays: MutableList<RSSChannelSkipDay> = mutableListOf(),
	var items: MutableList<RSSChannelItem> = mutableListOf(),

	var iTunes: ITunes? = ITunes(),
	var syndicationNamespace: SyndicationNamespace? = SyndicationNamespace(),
	var dublinCoreNamespace: DublinCoreNamespace? = DublinCoreNamespace(),
	var atomNamespace: AtomNamespace? = AtomNamespace()
) : FeedChannel()

data class RDFChannel(
	var title: String = "No Channel Title",
	var link: String = "No Channel Link",
	var description: String = "No Channel Description",

	var itemsResource: String? = null,
	var imageResource: String? = null,
	var textInputResource: String? = null,
	var image: RSSChannelImage? = null,
	var textInput: RSSChannelTextInput? = null,
	var items: MutableList<RSSChannelItem> = mutableListOf(),

	var syndicationNamespace: SyndicationNamespace? = SyndicationNamespace(),
	var dublinCoreNamespace: DublinCoreNamespace? = DublinCoreNamespace(),
	var attributes: Attributes? = null
) : FeedChannel() {
	data class Attributes(var about: String?) {
		constructor(attributes: Map<String, String>) : this(about = attributes["rdf:about"])
	}
}

data class AtomChannel(
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
	var entries: MutableList<AtomEntry> = mutableListOf()
) : FeedChannel()