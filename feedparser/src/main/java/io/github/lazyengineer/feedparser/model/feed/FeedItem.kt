package io.github.lazyengineer.feedparser.model.feed

import io.github.lazyengineer.feedparser.model.namespace.atom.AtomNamespace
import io.github.lazyengineer.feedparser.model.namespace.content.ContentNamespace
import io.github.lazyengineer.feedparser.model.namespace.dublincore.DublinCoreNamespace
import io.github.lazyengineer.feedparser.model.namespace.itunes.ITunes
import io.github.lazyengineer.feedparser.model.namespace.media.MediaNamespace
import java.util.Date

data class FeedItem(
	val title: String,
	val link: String,
	val description: String,
	val media: FeedMedia? = null,
	val author: String? = null,
	val source: ItemSource? = null,
	val categories: List<ItemCategory>? = emptyList(),
	val comments: String? = null,
	val guid: ItemGUID? = null,
	val pubDate: Date? = null,
	val about: String? = null,
	val iTunes: ITunes? = null,
	val mediaNamespace: MediaNamespace? = null,
	val contentNamespace: ContentNamespace? = null,
	val dublinCoreNamespace: DublinCoreNamespace? = null,
	val atomNamespace: AtomNamespace? = null
)
