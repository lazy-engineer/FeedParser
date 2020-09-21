package io.github.lazyengineer.feedparsersample

import io.github.lazyengineer.feedparser.FeedParser
import io.github.lazyengineer.feedparser.model.feed.Feed
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException

class FeedRepository constructor(private val client: OkHttpClient) {

	fun fetchFeed(url: String): Feed {
		val request: Request = Request.Builder()
				.url(url)
				.build()

		return try {
			client.newCall(request)
					.execute()
					.use { response ->
						if (response.isSuccessful) {
							val factory = XmlPullParserFactory.newInstance()
							val xmlPullParser = factory.newPullParser()

							FeedParser.parseFeed(response.body!!.string(), xmlPullParser)
						} else {
							throw IOException(response.message)
						}
					}
		} catch (e: Exception) {
			throw IOException("Error fetching feed", e)
		}
	}
}