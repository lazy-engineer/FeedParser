package io.github.lazyengineer.feedparser.model.rss

import io.github.lazyengineer.feedparser.model.namespace.atom.AtomNamespace
import io.github.lazyengineer.feedparser.model.namespace.content.ContentNamespace
import io.github.lazyengineer.feedparser.model.namespace.dublincore.DublinCoreNamespace
import io.github.lazyengineer.feedparser.model.namespace.itunes.ITunes
import io.github.lazyengineer.feedparser.model.namespace.media.MediaNamespace
import java.util.Date

data class RSSChannelItem(
	var title: String = "No Item Title",
	var link: String = "No Item Link",
	var description: String = "No Item Description",
	var author: String? = null,
	var categories: MutableList<RSSItemCategory> = mutableListOf(),
	var comments: String? = null,
	var enclosure: RSSItemEnclosure? = null,
	var guid: RSSItemGUID? = null,
	var pubDate: Date? = null,
	var source: RSSItemSource? = null,
	var iTunes: ITunes? = ITunes(),
	var mediaNamespace: MediaNamespace? = MediaNamespace(),
	var contentNamespace: ContentNamespace? = ContentNamespace(),
	var dublinCoreNamespace: DublinCoreNamespace? = DublinCoreNamespace(),
	var atomNamespace: AtomNamespace? = AtomNamespace(),
	var attributes: Attributes? = null
) {
	data class Attributes(var about: String?) {
		constructor(attributes: Map<String, String>) : this(about = attributes["rdf:about"])
	}
}
