package io.github.lazyengineer.feedparser.atom

import io.github.lazyengineer.feedparser.atom.AtomParserElement.*
import io.github.lazyengineer.feedparser.model.DateUtil
import io.github.lazyengineer.feedparser.model.atom.AtomEntry
import io.github.lazyengineer.feedparser.model.channel.AtomChannel
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomAuthor
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomCategory
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomContent
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomContributor
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomGenerator
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomLink
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomSource
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomSubtitle
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomSubtitle.Attributes
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomSummary
import io.github.lazyengineer.feedparser.model.namespace.atom.AtomTitle
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

fun AtomChannel.mapEvent(
	eventType: AtomParserElement,
	attributes: Map<String, String> = emptyMap(),
	value: String = String()
) {
	when (eventType) {
		FEED -> {}
		FEED_TITLE -> title = AtomTitle(value= value, attributes = AtomTitle.Attributes(attributes))
		FEED_SUBTITLE -> subtitle = AtomSubtitle(value= value, attributes = Attributes(attributes))
		FEED_LINK -> links.add(AtomLink(attributes = AtomLink.Attributes(attributes)))
		FEED_UPDATED -> updated = DateUtil.parseDateString(value)
		FEED_CATEGORY -> categories.add(AtomCategory(attributes = AtomCategory.Attributes(attributes)))
		FEED_AUTHOR -> authors.add(AtomAuthor())
		FEED_AUTHOR_NAME -> authors.last().name = value
		FEED_AUTHOR_EMAIL -> authors.last().email = value
		FEED_AUTHOR_URI -> authors.last().uri = value
		FEED_CONTRIBUTOR -> contributors.add(AtomContributor())
		FEED_CONTRIBUTOR_NAME -> contributors.last().name = value
		FEED_CONTRIBUTOR_EMAIL -> contributors.last().email = value
		FEED_CONTRIBUTOR_URI -> contributors.last().uri = value
		FEED_ID -> id = value
		FEED_GENERATOR -> generator = AtomGenerator(value = value, attributes = AtomGenerator.Attributes(attributes))
		FEED_ICON -> icon = value
		FEED_LOGO -> logo = value
		FEED_RIGHTS -> rights = value

		FEED_ENTRY -> entries.add(AtomEntry())
		ENTRY_TITLE -> entries.last().title = AtomTitle(value = value, attributes = AtomTitle.Attributes(attributes))
		ENTRY_SUMMARY -> entries.last().summary = AtomSummary(value = value, attributes = AtomSummary.Attributes(attributes))
		ENTRY_LINK -> entries.last().links.add(AtomLink(attributes = AtomLink.Attributes(attributes)))
		ENTRY_UPDATED -> entries.last().updated = DateUtil.parseDateString(value)
		ENTRY_CATEGORY -> entries.last().categories.add(AtomCategory(attributes = AtomCategory.Attributes(attributes)))
		ENTRY_ID -> entries.last().id = value
		ENTRY_CONTENT -> entries.last().content = AtomContent(value = value, attributes = AtomContent.Attributes(attributes))
		ENTRY_PUBLISHED -> entries.last().published = DateUtil.parseDateString(value)
		ENTRY_SOURCE -> entries.last().source = AtomSource()
		ENTRY_SOURCE_ID -> entries.last().source?.id = value
		ENTRY_SOURCE_TITLE -> entries.last().source?.title = value
		ENTRY_SOURCE_UPDATED -> entries.last().source?.updated = DateUtil.parseDateString(value)
		ENTRY_RIGHTS -> entries.last().rights = value
		ENTRY_AUTHOR -> entries.last().authors.add(AtomAuthor())
		ENTRY_AUTHOR_NAME -> entries.last().authors.last().name = value
		ENTRY_AUTHOR_EMAIL -> entries.last().authors.last().email = value
		ENTRY_AUTHOR_URI -> entries.last().authors.last().uri = value
		ENTRY_CONTRIBUTOR -> entries.last().contributors.add(AtomContributor())
		ENTRY_CONTRIBUTOR_NAME -> entries.last().contributors.last().name = value
		ENTRY_CONTRIBUTOR_EMAIL -> entries.last().contributors.last().email = value
		ENTRY_CONTRIBUTOR_URI -> entries.last().contributors.last().uri = value

		FEED_ENTRY_MEDIA_THUMBNAIL -> entries.last().mediaNamespace?.thumbnails?.add(MediaThumbnail(attributes = MediaThumbnail.Attributes(attributes)))
		FEED_ENTRY_MEDIA_CONTENT -> entries.last().mediaNamespace?.contents?.add(MediaContent(attributes = MediaContent.Attributes(attributes)))
		FEED_ENTRY_MEDIA_RATING -> entries.last().mediaNamespace?.rating = MediaRating(
			value = value,
			attributes = MediaRating.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_TITLE -> entries.last().mediaNamespace?.title = MediaTitle(value = value, attributes = MediaTitle.Attributes(attributes))
		FEED_ENTRY_MEDIA_DESCRIPTION -> entries.last().mediaNamespace?.description = MediaDescription(
			value = value,
			attributes = MediaDescription.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_KEYWORDS -> entries.last().mediaNamespace?.keywords = value.split(", ".toRegex()).toMutableList()
		FEED_ENTRY_MEDIA_CATEGORY -> entries.last().mediaNamespace?.category = MediaCategory(
			value = value,
			attributes = MediaCategory.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_CREDIT -> entries.last().mediaNamespace?.credits?.add(
			MediaCredit(
				value = value,
				attributes = MediaCredit.Attributes(attributes)
			)
		)
		FEED_ENTRY_MEDIA_COPYRIGHT -> entries.last().mediaNamespace?.copyright = MediaCopyright(
			value = value,
			attributes = MediaCopyright.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_TEXT -> entries.last().mediaNamespace?.text = MediaText(value = value, attributes = MediaText.Attributes(attributes))
		FEED_ENTRY_MEDIA_RIGHTS -> entries.last().mediaNamespace?.rights = MediaRights(attributes = MediaRights.Attributes(attributes))
		FEED_ENTRY_MEDIA_CONTENT_TITLE -> entries.last().mediaNamespace?.contents?.last()?.title = MediaTitle(
			value = value,
			attributes = MediaTitle.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_CONTENT_DESCRIPTION -> entries.last().mediaNamespace?.contents?.last()?.description = MediaDescription(
			value = value,
			attributes = MediaDescription.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_CONTENT_PLAYER -> entries.last().mediaNamespace?.player = MediaPlayer(attributes = MediaPlayer.Attributes(attributes))
		FEED_ENTRY_MEDIA_CONTENT_HASH -> entries.last().mediaNamespace?.hash = MediaHash(value = value, attributes = MediaHash.Attributes(attributes))
		FEED_ENTRY_MEDIA_CONTENT_CREDIT -> entries.last().mediaNamespace?.contents?.last()?.credits?.add(
			MediaCredit(
				value = value,
				attributes = MediaCredit.Attributes(attributes)
			)
		)
		FEED_ENTRY_MEDIA_CONTENT_THUMBNAIL -> entries.last().mediaNamespace?.contents?.last()?.thumbnails?.add(
			MediaThumbnail(attributes = MediaThumbnail.Attributes(attributes))
		)
		FEED_ENTRY_MEDIA_CONTENT_KEYWORDS -> entries.last().mediaNamespace?.contents?.last()?.keywords = value.split(", ".toRegex()).toMutableList()
		FEED_ENTRY_MEDIA_CONTENT_CATEGORY -> entries.last().mediaNamespace?.contents?.last()?.category = MediaCategory(
			value = value, attributes = MediaCategory.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_CONTENT_TEXT -> entries.last().mediaNamespace?.text = MediaText(value = value, attributes = MediaText.Attributes(attributes))
		FEED_ENTRY_MEDIA_CONTENT_RATING -> entries.last().mediaNamespace?.contents?.last()?.rating = MediaRating(
			value = value,
			attributes = MediaRating.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_COMMUNITY -> entries.last().mediaNamespace?.community = MediaCommunity()
		FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_STAR_RATING -> entries.last().mediaNamespace?.community?.starRating = MediaStarRating(
			attributes = MediaStarRating.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_STATISTICS -> entries.last().mediaNamespace?.community?.statistics = MediaStatistics(
			attributes = MediaStatistics.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_COMMUNITY_MEDIA_TAGS -> entries.last().mediaNamespace?.community?.tags = MediaTags(value = value)
		FEED_ENTRY_MEDIA_COMMENTS -> { // Just a Container
		}
		FEED_ENTRY_MEDIA_COMMENTS_MEDIA_COMMENT -> entries.last().mediaNamespace?.comments?.add(value)
		FEED_ENTRY_MEDIA_EMBED -> entries.last().mediaNamespace?.embed = MediaEmbed(attributes = MediaEmbed.Attributes(attributes))
		FEED_ENTRY_MEDIA_EMBED_MEDIA_PARAM -> entries.last().mediaNamespace?.embed?.params?.add(
			MediaEmbedParam(value = value, attributes = MediaEmbedParam.Attributes(attributes))
		)
		FEED_ENTRY_MEDIA_RESPONSES -> { // Just a Container
		}
		FEED_ENTRY_MEDIA_RESPONSES_MEDIA_RESPONSE -> entries.last().mediaNamespace?.responses?.add(value)
		FEED_ENTRY_MEDIA_BACK_LINKS -> { // Just a Container
		}
		FEED_ENTRY_MEDIA_BACK_LINKS_BACK_LINK -> entries.last().mediaNamespace?.backLinks?.add(value)
		FEED_ENTRY_MEDIA_STATUS -> entries.last().mediaNamespace?.status = MediaStatus(attributes = MediaStatus.Attributes(attributes))
		FEED_ENTRY_MEDIA_PRICE -> entries.last().mediaNamespace?.price?.add(MediaPrice(attributes = MediaPrice.Attributes(attributes)))
		FEED_ENTRY_MEDIA_LICENSE -> entries.last().mediaNamespace?.license = MediaLicense(
			value = value,
			attributes = MediaLicense.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_SUB_TITLE -> entries.last().mediaNamespace?.subTitle = MediaSubTitle(
			attributes = MediaSubTitle.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_PEER_LINK -> entries.last().mediaNamespace?.peerLink = MediaPeerLink(
			attributes = MediaPeerLink.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_LOCATION -> entries.last().mediaNamespace?.location = MediaLocation(
			attributes = MediaLocation.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_LOCATION_WHERE_POSITION -> { // Just a Container
		}
		FEED_ENTRY_MEDIA_LOCATION_POINT_POSITION -> { // Just a Container
		}
		FEED_ENTRY_MEDIA_LOCATION_POSITION -> entries.last().mediaNamespace?.location?.position = value
		FEED_ENTRY_MEDIA_RESTRICTION -> entries.last().mediaNamespace?.restriction = MediaRestriction(
			value = value,
			attributes = MediaRestriction.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_SCENES -> { // Just a Container
		}
		FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE -> entries.last().mediaNamespace?.scenes?.add(MediaScene())
		FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_TITLE -> entries.last().mediaNamespace?.scenes?.last()?.sceneTitle = value
		FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_DESCRIPTION -> entries.last().mediaNamespace?.scenes?.last()?.sceneDescription = value
		FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_START_TIME -> entries.last().mediaNamespace?.scenes?.last()?.sceneStartTime =
			DateUtil.parseTimeString(value)
		FEED_ENTRY_MEDIA_SCENES_MEDIA_SCENE_SCENE_END_TIME -> entries.last().mediaNamespace?.scenes?.last()?.sceneEndTime =
			DateUtil.parseTimeString(value)
		FEED_ENTRY_MEDIA_GROUP -> entries.last().mediaNamespace?.group = MediaGroup()
		FEED_ENTRY_MEDIA_GROUP_MEDIA_CREDIT -> entries.last().mediaNamespace?.group?.credits?.add(
			MediaCredit(
				value = value,
				attributes = MediaCredit.Attributes(attributes)
			)
		)
		FEED_ENTRY_MEDIA_GROUP_MEDIA_CATEGORY -> entries.last().mediaNamespace?.group?.category = MediaCategory(
			value = value,
			attributes = MediaCategory.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_GROUP_MEDIA_RATING -> entries.last().mediaNamespace?.group?.rating = MediaRating(
			value = value,
			attributes = MediaRating.Attributes(attributes)
		)
		FEED_ENTRY_MEDIA_GROUP_MEDIA_CONTENT -> entries.last().mediaNamespace?.group?.contents?.add(
			MediaContent(
				attributes = MediaContent.Attributes(
					attributes
				)
			)
		)

		UNSUPPORTED_ATOM_ELEMENT -> {}
	}
}