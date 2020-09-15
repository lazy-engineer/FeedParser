package io.github.lazyengineer.feedparser.model.rss

enum class RSSChannelSkipDay(val day: String) {
	MONDAY("monday"),
	TUESDAY("tuesday"),
	WEDNESDAY("wednesday"),
	THURSDAY("thursday"),
	FRIDAY("friday"),
	SATURDAY("saturday"),
	SUNDAY("sunday");

	companion object {
		fun from(day: String) = values().associateBy { it.day }[day] ?: throw IllegalArgumentException()
	}
}
