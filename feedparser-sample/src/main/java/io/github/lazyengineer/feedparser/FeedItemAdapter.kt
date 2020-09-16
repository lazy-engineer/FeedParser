package io.github.lazyengineer.feedparser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.lazyengineer.feedparser.databinding.FeedItemBinding
import io.github.lazyengineer.feedparser.model.feed.FeedItem

class FeedItemAdapter(private val items: List<FeedItem>) :
		RecyclerView.Adapter<FeedItemAdapter.ViewHolder>() {

	override fun getItemCount(): Int = items.size

	override fun onBindViewHolder(
		holder: ViewHolder,
		position: Int
	) = holder.bind(items[position])

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = FeedItemBinding.inflate(inflater, parent, false)
		return ViewHolder(binding)
	}

	class ViewHolder(private val binding: FeedItemBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: FeedItem) {
			binding.itemTitle.text = item.title
		}
	}
}