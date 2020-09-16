package io.github.lazyengineer.feedparser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FeedViewModelFactory constructor(private val feedRepository: FeedRepository) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return FeedViewModel(feedRepository) as T
	}
}