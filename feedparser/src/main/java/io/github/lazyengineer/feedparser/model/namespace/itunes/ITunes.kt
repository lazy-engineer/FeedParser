package io.github.lazyengineer.feedparser.model.namespace.itunes

/*
* https://help.apple.com/itc/podcasts_connect/#/itcb54353390
 */
data class ITunes(
	var image: ITunesImage? = null,
	var categories: MutableList<ITunesCategory> = mutableListOf(),
	var explicit: String? = null,
	var author: String? = null,
	var title: String? = null,
	var type: String? = null,
	var newFeedURL: String? = null,
	var owner: ITunesOwner? = null,
	var block: String? = null,
	var complete: String? = null,
	var duration: Long? = null,
	var episode: Int? = null,
	var season: Int? = null,
	var episodeType: String? = null,
	var isClosedCaptioned: String? = null,

	var order: Int? = null,
	var subtitle: String? = null,
	var summary: String? = null,
	var keywords: String? = null
)
