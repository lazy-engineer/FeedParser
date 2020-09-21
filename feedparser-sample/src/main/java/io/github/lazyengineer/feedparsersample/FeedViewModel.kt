package io.github.lazyengineer.feedparsersample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.lazyengineer.feedparser.model.feed.RSSFeed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedViewModel constructor(private val repository: FeedRepository) : ViewModel() {

	private val _feed = MutableLiveData<RSSFeed>()
	val feed: LiveData<RSSFeed>
		get() = _feed

	fun fetchFeed(url: String) {
		viewModelScope.launch {
			fetchFeedFromUrl(url)
		}
	}

	private suspend fun fetchFeedFromUrl(url: String) {
		withContext(Dispatchers.IO) {
			val result = repository.fetchFeed(url)

			_feed.postValue(result as RSSFeed)
		}
	}
}