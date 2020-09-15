package io.github.lazyengineer.feedparser.rss.rdf

import io.github.lazyengineer.feedparser.model.channel.RDFChannel
import io.github.lazyengineer.feedparser.model.feed.FeedImage
import io.github.lazyengineer.feedparser.model.feed.FeedItem
import io.github.lazyengineer.feedparser.model.feed.FeedMedia
import io.github.lazyengineer.feedparser.model.feed.FeedTextInput
import io.github.lazyengineer.feedparser.model.feed.ItemCategory
import io.github.lazyengineer.feedparser.model.feed.ItemGUID
import io.github.lazyengineer.feedparser.model.feed.ItemSource
import io.github.lazyengineer.feedparser.model.feed.RSSFeed

fun RDFChannel.toFeed(): RSSFeed {
	return RSSFeed(
		title = title,
		link = link,
		description = description,
		about = attributes?.about,
		itemsResource = itemsResource,
		imageResource = imageResource,
		textInputResource = textInputResource,
		items = items.map {
			FeedItem(
				title = it.title,
				link = it.link,
				description = it.description,
				media = it.enclosure?.let { media ->
					FeedMedia(
						url = media.attributes?.url,
						type = media.attributes?.type,
						size = media.attributes?.length
					)
				},
				author = it.author,
				source = it.source?.let { source -> ItemSource(url = source.attributes?.url, value = source.value) },
				categories = it.categories.map { category -> ItemCategory(value = category.value, domain = category.attributes.domain) },
				comments = it.comments,
				guid = it.guid?.let { guid -> ItemGUID(value = guid.value, isPermaLink = guid.attributes?.isPermaLink) },
				pubDate = it.pubDate,
				about = it.attributes?.about,
				iTunes = it.iTunes,
				mediaNamespace = it.mediaNamespace,
				contentNamespace = it.contentNamespace,
				dublinCoreNamespace = it.dublinCoreNamespace,
				atomNamespace = it.atomNamespace
			)
		},
		image = image?.let {
			FeedImage(
				url = it.url,
				title = it.title,
				link = it.link,
				width = it.width,
				height = it.height,
				description = it.description,
				about = it.attributes?.about
			)
		},
		textInput = textInput?.let { FeedTextInput(title = it.title, description = it.description, name = it.name, link = it.link, about = it.attributes?.about) },
		syndicationNamespace = syndicationNamespace,
		dublinCoreNamespace = dublinCoreNamespace
	)
}