package io.github.lazyengineer.feedparser.model.namespace.syndication

enum class SyndicationUpdatePeriod(val period: String) {
	HOURLY("hourly"),
	DAILY("daily"),
	WEEKLY("weekly"),
	MONTHLY("monthly"),
	YEARLY("yearly");

	companion object {
		fun from(period: String) = values().associateBy { it.period }[period] ?: throw IllegalArgumentException()
	}
}
