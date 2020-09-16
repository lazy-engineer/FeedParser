package io.github.lazyengineer.feedparser

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.lazyengineer.feedparser.databinding.ActivityMainBinding
import io.github.lazyengineer.feedparser.model.feed.FeedItem
import io.github.lazyengineer.feedparser.model.feed.RSSFeed
import okhttp3.OkHttpClient

class FeedActivity : AppCompatActivity() {

	private lateinit var viewModel: FeedViewModel
	private val okHttpClient: OkHttpClient by lazy { OkHttpClient() }
	private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		viewModel = ViewModelProvider(this, FeedViewModelFactory(FeedRepository(okHttpClient))).get(FeedViewModel::class.java)

		viewModel.feed.observe(this, Observer { feed ->
			hideButton()
			showFeed(feed)
		})

		binding.fetchFeedBtn.setOnClickListener {
			viewModel.fetchFeed("https://feeds.feedburner.com/blogspot/androiddevelopersbackstage")
		}
	}

	private fun hideButton() {
		binding.fetchFeedBtn.visibility = View.GONE
	}

	private fun showFeed(feed: RSSFeed) {
		binding.feedList.visibility = View.VISIBLE
		showList(feed.items)
	}

	private fun showList(items: List<FeedItem>) {
		binding.feedList.layoutManager = LinearLayoutManager(applicationContext)
		binding.feedList.adapter = FeedItemAdapter(items)
	}
}