package io.github.lazyengineer.feedparser.rss

import io.github.lazyengineer.feedparser.model.DateUtil
import io.github.lazyengineer.feedparser.model.channel.RSSChannel
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomAuthor
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomCategory
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomContent
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomContributor
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomGenerator
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomLink
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomSource
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomSubtitle
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomSummary
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomTitle
import io.github.lazyengineer.feedparser.model.namespace.itunes.ITunesCategory
import io.github.lazyengineer.feedparser.model.namespace.itunes.ITunesImage
import io.github.lazyengineer.feedparser.model.namespace.itunes.ITunesOwner
import io.github.lazyengineer.feedparser.model.namespace.itunes.ITunesSubCategory
import io.github.lazyengineer.feedparser.model.namespace.media.MediaCategory
import io.github.lazyengineer.feedparser.model.namespace.media.MediaCommunity
import io.github.lazyengineer.feedparser.model.namespace.media.MediaContent
import io.github.lazyengineer.feedparser.model.namespace.media.MediaCopyright
import io.github.lazyengineer.feedparser.model.namespace.media.MediaCredit
import io.github.lazyengineer.feedparser.model.namespace.media.MediaDescription
import io.github.lazyengineer.feedparser.model.namespace.media.MediaEmbed
import io.github.lazyengineer.feedparser.model.namespace.media.MediaEmbedParam
import io.github.lazyengineer.feedparser.model.namespace.media.MediaGroup
import io.github.lazyengineer.feedparser.model.namespace.media.MediaHash
import io.github.lazyengineer.feedparser.model.namespace.media.MediaLicense
import io.github.lazyengineer.feedparser.model.namespace.media.MediaLocation
import io.github.lazyengineer.feedparser.model.namespace.media.MediaPeerLink
import io.github.lazyengineer.feedparser.model.namespace.media.MediaPlayer
import io.github.lazyengineer.feedparser.model.namespace.media.MediaPrice
import io.github.lazyengineer.feedparser.model.namespace.media.MediaRating
import io.github.lazyengineer.feedparser.model.namespace.media.MediaRestriction
import io.github.lazyengineer.feedparser.model.namespace.media.MediaRights
import io.github.lazyengineer.feedparser.model.namespace.media.MediaScene
import io.github.lazyengineer.feedparser.model.namespace.media.MediaStarRating
import io.github.lazyengineer.feedparser.model.namespace.media.MediaStatistics
import io.github.lazyengineer.feedparser.model.namespace.media.MediaStatus
import io.github.lazyengineer.feedparser.model.namespace.media.MediaSubTitle
import io.github.lazyengineer.feedparser.model.namespace.media.MediaTags
import io.github.lazyengineer.feedparser.model.namespace.media.MediaText
import io.github.lazyengineer.feedparser.model.namespace.media.MediaThumbnail
import io.github.lazyengineer.feedparser.model.namespace.media.MediaTitle
import io.github.lazyengineer.feedparser.model.namespace.syndication.SyndicationUpdatePeriod
import io.github.lazyengineer.feedparser.model.rss.RSSChannelCategory
import io.github.lazyengineer.feedparser.model.rss.RSSChannelCategory.Attributes
import io.github.lazyengineer.feedparser.model.rss.RSSChannelCloud
import io.github.lazyengineer.feedparser.model.rss.RSSChannelImage
import io.github.lazyengineer.feedparser.model.rss.RSSChannelItem
import io.github.lazyengineer.feedparser.model.rss.RSSChannelSkipDay
import io.github.lazyengineer.feedparser.model.rss.RSSChannelTextInput
import io.github.lazyengineer.feedparser.model.rss.RSSItemCategory
import io.github.lazyengineer.feedparser.model.rss.RSSItemEnclosure
import io.github.lazyengineer.feedparser.model.rss.RSSItemGUID
import io.github.lazyengineer.feedparser.model.rss.RSSItemSource
import io.github.lazyengineer.feedparser.rss.RSSParserElement.*
import java.util.Locale

fun RSSChannel.mapEvent(
	eventType: RSSParserElement,
	attributes: Map<String, String> = emptyMap(),
	value: String = String()
) {
	when (eventType) {
		CHANNEL_TITLE -> title = value
		CHANNEL_LINK -> link = value
		CHANNEL_DESCRIPTION -> description = value
		CHANNEL_LANGUAGE -> language = value
		CHANNEL_MANAGING_EDITOR -> managingEditor = value
		CHANNEL_LAST_BUILD_DATE -> lastBuildDate = DateUtil.parseDateString(value)
		CHANNEL_GENERATOR -> generator = value
		CHANNEL_CATEGORY -> categories.add(RSSChannelCategory(value = value, attributes = Attributes(attributes)))
		CHANNEL_CLOUD -> cloud = RSSChannelCloud(attributes = RSSChannelCloud.Attributes(attributes))
		CHANNEL_COPYRIGHT -> copyright = value
		CHANNEL_DOCS -> docs = value
		CHANNEL_PUB_DATE -> pubDate = DateUtil.parseDateString(value)
		CHANNEL_RATING -> rating = value
		CHANNEL_SKIP_DAY -> skipDays.add(RSSChannelSkipDay.from(value.toLowerCase(Locale.ROOT)))
		CHANNEL_SKIP_DAYS -> { // Just a Container
		}
		CHANNEL_SKIP_HOUR -> skipHours.add(value.toInt())
		CHANNEL_SKIP_HOURS -> { // Just a Container
		}
		CHANNEL_TTL -> ttl = value.toInt()
		CHANNEL_WEB_MASTER -> webMaster = value
		CHANNEL_IMAGE -> image = RSSChannelImage()
		CHANNEL_TEXT_INPUT -> textInput = RSSChannelTextInput()
		CHANNEL_ITEM -> items.add(RSSChannelItem())

		ITEM_TITLE -> items.last().title = value
		ITEM_LINK -> items.last().link = value
		ITEM_DESCRIPTION -> items.last().description = value
		ITEM_ENCLOSURE -> items.last().enclosure = RSSItemEnclosure(attributes = RSSItemEnclosure.Attributes(attributes))
		ITEM_AUTHOR -> items.last().author = value
		ITEM_SOURCE -> items.last().source = RSSItemSource(value = value, attributes = RSSItemSource.Attributes(attributes))
		ITEM_CATEGORY -> items.last().categories.add(RSSItemCategory(value = value, attributes = RSSItemCategory.Attributes(attributes)))
		ITEM_COMMENTS -> items.last().comments = value
		ITEM_GUID -> items.last().guid = RSSItemGUID(value = value, attributes = RSSItemGUID.Attributes(attributes))
		ITEM_PUB_DATE -> items.last().pubDate = DateUtil.parseDateString(value)

		IMAGE_URL -> image?.url = value
		IMAGE_TITLE -> image?.title = value
		IMAGE_LINK -> image?.link = value
		IMAGE_WIDTH -> image?.width = value.toInt()
		IMAGE_HEIGHT -> image?.height = value.toInt()
		IMAGE_DESCRIPTION -> image?.description = value

		TEXT_INPUT_TITLE -> textInput?.title = value
		TEXT_INPUT_DESCRIPTION -> textInput?.description = value
		TEXT_INPUT_NAME -> textInput?.name = value
		TEXT_INPUT_LINK -> textInput?.link = value

		CHANNEL_ITUNES_AUTHOR -> iTunes?.author = value
		CHANNEL_ITUNES_BLOCK -> iTunes?.block = value
		CHANNEL_ITUNES_IMAGE -> iTunes?.image = ITunesImage(attributes = ITunesImage.Attributes(attributes))
		CHANNEL_ITUNES_EXPLICIT -> iTunes?.explicit = value
		CHANNEL_ITUNES_COMPLETE -> iTunes?.complete = value
		CHANNEL_ITUNES_NEW_FEED_URL -> iTunes?.newFeedURL = value
		CHANNEL_ITUNES_OWNER_EMAIL -> iTunes?.owner?.email = value
		CHANNEL_ITUNES_OWNER_NAME -> iTunes?.owner?.name = value
		CHANNEL_ITUNES_TITLE -> iTunes?.title = value
		CHANNEL_ITUNES_SUBTITLE -> iTunes?.subtitle = value
		CHANNEL_ITUNES_SUMMARY -> iTunes?.summary = value
		CHANNEL_ITUNES_KEYWORDS -> iTunes?.keywords = value
		CHANNEL_ITUNES_TYPE -> iTunes?.type = value
		CHANNEL_ITUNES_CATEGORY -> iTunes?.categories?.add(ITunesCategory(attributes = ITunesCategory.Attributes(attributes)))
		CHANNEL_ITUNES_SUBCATEGORY -> iTunes?.categories?.last()?.subcategory = ITunesSubCategory(attributes = ITunesSubCategory.Attributes(attributes))
		CHANNEL_ITUNES_OWNER -> iTunes?.owner = ITunesOwner()

		CHANNEL_ITEM_ITUNES_AUTHOR -> items.last().iTunes?.author = value
		CHANNEL_ITEM_ITUNES_BLOCK -> items.last().iTunes?.block = value
		CHANNEL_ITEM_ITUNES_IMAGE -> items.last().iTunes?.image = ITunesImage(attributes = ITunesImage.Attributes(attributes))
		CHANNEL_ITEM_ITUNES_DURATION -> items.last().iTunes?.duration = DateUtil.parseTimeString(value)
		CHANNEL_ITEM_ITUNES_EXPLICIT -> items.last().iTunes?.explicit = value
		CHANNEL_ITEM_ITUNES_IS_CLOSED_CAPTIONED -> items.last().iTunes?.isClosedCaptioned = value
		CHANNEL_ITEM_ITUNES_ORDER -> items.last().iTunes?.order = value.toInt()
		CHANNEL_ITEM_ITUNES_TITLE -> items.last().iTunes?.title = value
		CHANNEL_ITEM_ITUNES_SUBTITLE -> items.last().iTunes?.subtitle = value
		CHANNEL_ITEM_ITUNES_SUMMARY -> items.last().iTunes?.summary = value
		CHANNEL_ITEM_ITUNES_KEYWORDS -> items.last().iTunes?.keywords = value
		CHANNEL_ITEM_ITUNES_EPISODE_TYPE -> items.last().iTunes?.episodeType = value
		CHANNEL_ITEM_ITUNES_SEASON -> items.last().iTunes?.season = value.toInt()
		CHANNEL_ITEM_ITUNES_EPISODE -> items.last().iTunes?.episode = value.toInt()

		CHANNEL_ITEM_MEDIA_THUMBNAIL -> items.last().mediaNamespace?.thumbnails?.add(MediaThumbnail(attributes = MediaThumbnail.Attributes(attributes)))
		CHANNEL_ITEM_MEDIA_CONTENT -> items.last().mediaNamespace?.contents?.add(MediaContent(attributes = MediaContent.Attributes(attributes)))
		CHANNEL_ITEM_MEDIA_RATING -> items.last().mediaNamespace?.rating = MediaRating(
			value = value,
			attributes = MediaRating.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_TITLE -> items.last().mediaNamespace?.title = MediaTitle(value = value, attributes = MediaTitle.Attributes(attributes))
		CHANNEL_ITEM_MEDIA_DESCRIPTION -> items.last().mediaNamespace?.description = MediaDescription(
			value = value,
			attributes = MediaDescription.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_KEYWORDS -> items.last().mediaNamespace?.keywords = value.split(", ".toRegex()).toMutableList()
		CHANNEL_ITEM_MEDIA_CATEGORY -> items.last().mediaNamespace?.category = MediaCategory(
			value = value,
			attributes = MediaCategory.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_CREDIT -> items.last().mediaNamespace?.credits?.add(
			MediaCredit(
				value = value,
				attributes = MediaCredit.Attributes(attributes)
			)
		)
		CHANNEL_ITEM_MEDIA_COPYRIGHT -> items.last().mediaNamespace?.copyright = MediaCopyright(
			value = value,
			attributes = MediaCopyright.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_TEXT -> items.last().mediaNamespace?.text = MediaText(value = value, attributes = MediaText.Attributes(attributes))
		CHANNEL_ITEM_MEDIA_RIGHTS -> items.last().mediaNamespace?.rights = MediaRights(attributes = MediaRights.Attributes(attributes))
		CHANNEL_ITEM_MEDIA_CONTENT_TITLE -> items.last().mediaNamespace?.contents?.last()?.title = MediaTitle(
			value = value,
			attributes = MediaTitle.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_CONTENT_DESCRIPTION -> items.last().mediaNamespace?.contents?.last()?.description = MediaDescription(
			value = value,
			attributes = MediaDescription.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_CONTENT_PLAYER -> items.last().mediaNamespace?.player = MediaPlayer(attributes = MediaPlayer.Attributes(attributes))
		CHANNEL_ITEM_MEDIA_CONTENT_HASH -> items.last().mediaNamespace?.hash = MediaHash(value = value, attributes = MediaHash.Attributes(attributes))
		CHANNEL_ITEM_MEDIA_CONTENT_CREDIT -> items.last().mediaNamespace?.contents?.last()?.credits?.add(
			MediaCredit(
				value = value,
				attributes = MediaCredit.Attributes(attributes)
			)
		)
		CHANNEL_ITEM_MEDIA_CONTENT_THUMBNAIL -> items.last().mediaNamespace?.contents?.last()?.thumbnails?.add(
			MediaThumbnail(attributes = MediaThumbnail.Attributes(attributes))
		)
		CHANNEL_ITEM_MEDIA_CONTENT_KEYWORDS -> items.last().mediaNamespace?.contents?.last()?.keywords = value.split(", ".toRegex()).toMutableList()
		CHANNEL_ITEM_MEDIA_CONTENT_CATEGORY -> items.last().mediaNamespace?.contents?.last()?.category = MediaCategory(
			value = value, attributes = MediaCategory.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_CONTENT_TEXT -> items.last().mediaNamespace?.text = MediaText(value = value, attributes = MediaText.Attributes(attributes))
		CHANNEL_ITEM_MEDIA_CONTENT_RATING -> items.last().mediaNamespace?.contents?.last()?.rating = MediaRating(
			value = value,
			attributes = MediaRating.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_COMMUNITY -> items.last().mediaNamespace?.community = MediaCommunity()
		CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_STAR_RATING -> items.last().mediaNamespace?.community?.starRating = MediaStarRating(
			attributes = MediaStarRating.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_STATISTICS -> items.last().mediaNamespace?.community?.statistics = MediaStatistics(
			attributes = MediaStatistics.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_COMMUNITY_MEDIA_TAGS -> items.last().mediaNamespace?.community?.tags = MediaTags(value = value)
		CHANNEL_ITEM_MEDIA_COMMENTS -> { // Just a Container
		}
		CHANNEL_ITEM_MEDIA_COMMENTS_MEDIA_COMMENT -> items.last().mediaNamespace?.comments?.add(value)
		CHANNEL_ITEM_MEDIA_EMBED -> items.last().mediaNamespace?.embed = MediaEmbed(attributes = MediaEmbed.Attributes(attributes))
		CHANNEL_ITEM_MEDIA_EMBED_MEDIA_PARAM -> items.last().mediaNamespace?.embed?.params?.add(
			MediaEmbedParam(value = value, attributes = MediaEmbedParam.Attributes(attributes))
		)
		CHANNEL_ITEM_MEDIA_RESPONSES -> { // Just a Container
		}
		CHANNEL_ITEM_MEDIA_RESPONSES_MEDIA_RESPONSE -> items.last().mediaNamespace?.responses?.add(value)
		CHANNEL_ITEM_MEDIA_BACK_LINKS -> { // Just a Container
		}
		CHANNEL_ITEM_MEDIA_BACK_LINKS_BACK_LINK -> items.last().mediaNamespace?.backLinks?.add(value)
		CHANNEL_ITEM_MEDIA_STATUS -> items.last().mediaNamespace?.status = MediaStatus(attributes = MediaStatus.Attributes(attributes))
		CHANNEL_ITEM_MEDIA_PRICE -> items.last().mediaNamespace?.price?.add(MediaPrice(attributes = MediaPrice.Attributes(attributes)))
		CHANNEL_ITEM_MEDIA_LICENSE -> items.last().mediaNamespace?.license = MediaLicense(
			value = value,
			attributes = MediaLicense.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_SUB_TITLE -> items.last().mediaNamespace?.subTitle = MediaSubTitle(
			attributes = MediaSubTitle.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_PEER_LINK -> items.last().mediaNamespace?.peerLink = MediaPeerLink(
			attributes = MediaPeerLink.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_LOCATION -> items.last().mediaNamespace?.location = MediaLocation(
			attributes = MediaLocation.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_LOCATION_WHERE_POSITION -> { // Just a Container
		}
		CHANNEL_ITEM_MEDIA_LOCATION_POINT_POSITION -> { // Just a Container
		}
		CHANNEL_ITEM_MEDIA_LOCATION_POSITION -> items.last().mediaNamespace?.location?.position = value
		CHANNEL_ITEM_MEDIA_RESTRICTION -> items.last().mediaNamespace?.restriction = MediaRestriction(
			value = value,
			attributes = MediaRestriction.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_SCENES -> { // Just a Container
		}
		CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE -> items.last().mediaNamespace?.scenes?.add(MediaScene())
		CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_TITLE -> items.last().mediaNamespace?.scenes?.last()?.sceneTitle = value
		CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_DESCRIPTION -> items.last().mediaNamespace?.scenes?.last()?.sceneDescription = value
		CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_START_TIME -> items.last().mediaNamespace?.scenes?.last()?.sceneStartTime =
			DateUtil.parseTimeString(value)
		CHANNEL_ITEM_MEDIA_SCENES_MEDIA_SCENE_SCENE_END_TIME -> items.last().mediaNamespace?.scenes?.last()?.sceneEndTime =
			DateUtil.parseTimeString(value)
		CHANNEL_ITEM_MEDIA_GROUP -> items.last().mediaNamespace?.group = MediaGroup()
		CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CREDIT -> items.last().mediaNamespace?.group?.credits?.add(
			MediaCredit(
				value = value,
				attributes = MediaCredit.Attributes(attributes)
			)
		)
		CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CATEGORY -> items.last().mediaNamespace?.group?.category = MediaCategory(
			value = value,
			attributes = MediaCategory.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_GROUP_MEDIA_RATING -> items.last().mediaNamespace?.group?.rating = MediaRating(
			value = value,
			attributes = MediaRating.Attributes(attributes)
		)
		CHANNEL_ITEM_MEDIA_GROUP_MEDIA_CONTENT -> items.last().mediaNamespace?.group?.contents?.add(
			MediaContent(
				attributes = MediaContent.Attributes(
					attributes
				)
			)
		)

		CHANNEL_ITEM_CONTENT_ENCODED -> items.last().contentNamespace?.encoded = value

		CHANNEL_SYNDICATION_UPDATE_PERIOD -> syndicationNamespace?.updatePeriod = SyndicationUpdatePeriod.from(value.toLowerCase(Locale.ROOT))
		CHANNEL_SYNDICATION_UPDATE_FREQUENCY -> syndicationNamespace?.updateFrequency = value.toInt()
		CHANNEL_SYNDICATION_UPDATE_BASE -> syndicationNamespace?.updateBase = DateUtil.parseDateString(value)

		CHANNEL_DUBLIN_CORE_TITLE -> dublinCoreNamespace?.title = value
		CHANNEL_DUBLIN_CORE_CREATOR -> dublinCoreNamespace?.creator = value
		CHANNEL_DUBLIN_CORE_SUBJECT -> dublinCoreNamespace?.subject = value
		CHANNEL_DUBLIN_CORE_DESCRIPTION -> dublinCoreNamespace?.description = value
		CHANNEL_DUBLIN_CORE_PUBLISHER -> dublinCoreNamespace?.publisher = value
		CHANNEL_DUBLIN_CORE_CONTRIBUTOR -> dublinCoreNamespace?.contributor = value
		CHANNEL_DUBLIN_CORE_DATE -> dublinCoreNamespace?.date = DateUtil.parseDateString(value)
		CHANNEL_DUBLIN_CORE_TYPE -> dublinCoreNamespace?.type = value
		CHANNEL_DUBLIN_CORE_FORMAT -> dublinCoreNamespace?.format = value
		CHANNEL_DUBLIN_CORE_IDENTIFIER -> dublinCoreNamespace?.identifier = value
		CHANNEL_DUBLIN_CORE_SOURCE -> dublinCoreNamespace?.source = value
		CHANNEL_DUBLIN_CORE_LANGUAGE -> dublinCoreNamespace?.language = value
		CHANNEL_DUBLIN_CORE_RELATION -> dublinCoreNamespace?.relation = value
		CHANNEL_DUBLIN_CORE_COVERAGE -> dublinCoreNamespace?.coverage = value
		CHANNEL_DUBLIN_CORE_RIGHTS -> dublinCoreNamespace?.rights = value
		CHANNEL_ITEM_DUBLIN_CORE_TITLE -> items.last().dublinCoreNamespace?.title = value
		CHANNEL_ITEM_DUBLIN_CORE_CREATOR -> items.last().dublinCoreNamespace?.creator = value
		CHANNEL_ITEM_DUBLIN_CORE_SUBJECT -> items.last().dublinCoreNamespace?.subject = value
		CHANNEL_ITEM_DUBLIN_CORE_DESCRIPTION -> items.last().dublinCoreNamespace?.description = value
		CHANNEL_ITEM_DUBLIN_CORE_PUBLISHER -> items.last().dublinCoreNamespace?.publisher = value
		CHANNEL_ITEM_DUBLIN_CORE_CONTRIBUTOR -> items.last().dublinCoreNamespace?.contributor = value
		CHANNEL_ITEM_DUBLIN_CORE_DATE -> items.last().dublinCoreNamespace?.date = DateUtil.parseDateString(value)
		CHANNEL_ITEM_DUBLIN_CORE_TYPE -> items.last().dublinCoreNamespace?.type = value
		CHANNEL_ITEM_DUBLIN_CORE_FORMAT -> items.last().dublinCoreNamespace?.format = value
		CHANNEL_ITEM_DUBLIN_CORE_IDENTIFIER -> items.last().dublinCoreNamespace?.identifier = value
		CHANNEL_ITEM_DUBLIN_CORE_SOURCE -> items.last().dublinCoreNamespace?.source = value
		CHANNEL_ITEM_DUBLIN_CORE_LANGUAGE -> items.last().dublinCoreNamespace?.language = value
		CHANNEL_ITEM_DUBLIN_CORE_RELATION -> items.last().dublinCoreNamespace?.relation = value
		CHANNEL_ITEM_DUBLIN_CORE_COVERAGE -> items.last().dublinCoreNamespace?.coverage = value
		CHANNEL_ITEM_DUBLIN_CORE_RIGHTS -> items.last().dublinCoreNamespace?.rights = value

		CHANNEL_ATOM_TITLE -> atomNamespace?.title = AtomTitle(value = value, attributes = AtomTitle.Attributes(attributes))
		CHANNEL_ATOM_SUBTITLE -> atomNamespace?.subtitle = AtomSubtitle(value = value, attributes = AtomSubtitle.Attributes(attributes))
		CHANNEL_ATOM_LINK -> atomNamespace?.links?.add(AtomLink(attributes = AtomLink.Attributes(attributes)))
		CHANNEL_ATOM_UPDATED -> atomNamespace?.updated = DateUtil.parseDateString(value)
		CHANNEL_ATOM_CATEGORY -> atomNamespace?.categories?.add(AtomCategory(attributes = AtomCategory.Attributes(attributes)))
		CHANNEL_ATOM_AUTHOR -> atomNamespace?.authors?.add(AtomAuthor())
		CHANNEL_ATOM_AUTHOR_NAME -> atomNamespace?.authors?.last()?.name = value
		CHANNEL_ATOM_AUTHOR_EMAIL -> atomNamespace?.authors?.last()?.email = value
		CHANNEL_ATOM_AUTHOR_URI -> atomNamespace?.authors?.last()?.uri = value
		CHANNEL_ATOM_CONTRIBUTOR -> atomNamespace?.contributors?.add(AtomContributor())
		CHANNEL_ATOM_CONTRIBUTOR_NAME -> atomNamespace?.contributors?.last()?.name = value
		CHANNEL_ATOM_CONTRIBUTOR_EMAIL -> atomNamespace?.contributors?.last()?.email = value
		CHANNEL_ATOM_CONTRIBUTOR_URI -> atomNamespace?.contributors?.last()?.uri = value
		CHANNEL_ATOM_ID -> atomNamespace?.id = value
		CHANNEL_ATOM_GENERATOR -> atomNamespace?.generator = AtomGenerator(value = value, attributes = AtomGenerator.Attributes(attributes))
		CHANNEL_ATOM_ICON -> atomNamespace?.icon = value
		CHANNEL_ATOM_LOGO -> atomNamespace?.logo = value
		CHANNEL_ATOM_RIGHTS -> atomNamespace?.rights = value
		CHANNEL_ITEM_ATOM_TITLE -> items.last().atomNamespace?.title = AtomTitle(value = value, attributes = AtomTitle.Attributes(attributes))
		CHANNEL_ITEM_ATOM_SUMMARY -> items.last().atomNamespace?.summary = AtomSummary(value = value, attributes = AtomSummary.Attributes(attributes))
		CHANNEL_ITEM_ATOM_LINK -> items.last().atomNamespace?.links?.add(AtomLink(attributes = AtomLink.Attributes(attributes)))
		CHANNEL_ITEM_ATOM_UPDATED -> items.last().atomNamespace?.updated = DateUtil.parseDateString(value)
		CHANNEL_ITEM_ATOM_CATEGORY -> items.last().atomNamespace?.categories?.add(AtomCategory(attributes = AtomCategory.Attributes(attributes)))
		CHANNEL_ITEM_ATOM_ID -> items.last().atomNamespace?.id = value
		CHANNEL_ITEM_ATOM_CONTENT -> items.last().atomNamespace?.content = AtomContent(value = value, attributes = AtomContent.Attributes(attributes))
		CHANNEL_ITEM_ATOM_PUBLISHED -> items.last().atomNamespace?.published = DateUtil.parseDateString(value)
		CHANNEL_ITEM_ATOM_SOURCE -> items.last().atomNamespace?.source = AtomSource()
		CHANNEL_ITEM_ATOM_SOURCE_ID -> items.last().atomNamespace?.source?.id = value
		CHANNEL_ITEM_ATOM_SOURCE_TITLE -> items.last().atomNamespace?.source?.title = value
		CHANNEL_ITEM_ATOM_SOURCE_UPDATED -> items.last().atomNamespace?.source?.updated = DateUtil.parseDateString(value)
		CHANNEL_ITEM_ATOM_RIGHTS -> items.last().atomNamespace?.rights = value
		CHANNEL_ITEM_ATOM_AUTHOR -> items.last().atomNamespace?.authors?.add(AtomAuthor())
		CHANNEL_ITEM_ATOM_AUTHOR_NAME -> items.last().atomNamespace?.authors?.last()?.name = value
		CHANNEL_ITEM_ATOM_AUTHOR_EMAIL -> items.last().atomNamespace?.authors?.last()?.email = value
		CHANNEL_ITEM_ATOM_AUTHOR_URI -> items.last().atomNamespace?.authors?.last()?.uri = value
		CHANNEL_ITEM_ATOM_CONTRIBUTOR -> items.last().atomNamespace?.contributors?.add(AtomContributor())
		CHANNEL_ITEM_ATOM_CONTRIBUTOR_NAME -> items.last().atomNamespace?.contributors?.last()?.name = value
		CHANNEL_ITEM_ATOM_CONTRIBUTOR_EMAIL -> items.last().atomNamespace?.contributors?.last()?.email = value
		CHANNEL_ITEM_ATOM_CONTRIBUTOR_URI -> items.last().atomNamespace?.contributors?.last()?.uri = value

		UNSUPPORTED_RSS_ELEMENT -> {
		}
	}
}
