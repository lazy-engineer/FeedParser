package io.github.lazyengineer.feedparser.atom

import io.github.lazyengineer.feedparser.model.channel.AtomChannel
import io.github.lazyengineer.feedparser.model.feed.AtomFeed

fun AtomChannel.toFeed(): AtomFeed {
	return AtomFeed(
		id = id,
		authors = authors,
		categories = categories,
		contributors = contributors,
		generator = generator,
		icon = icon,
		logo = logo,
		links = links,
		rights = rights,
		subtitle = subtitle,
		title = title,
		updated = updated,
		entries = entries
	)
}