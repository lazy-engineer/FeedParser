package io.github.lazyengineer.feedparsersample

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.lazyengineer.feedparser.model.feed.FeedItem
import io.github.lazyengineer.feedparsersample.databinding.FeedItemBinding

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
		return ViewHolder(parent.context, binding)
	}

	class ViewHolder(
		private val context: Context,
		private val binding: FeedItemBinding
	) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: FeedItem) {
			binding.itemTitle.text = item.title
			item.mediaNamespace?.thumbnails?.map {
				Picasso.with(context)
						.load(it.attributes?.url)
						.into(binding.itemImage)
			}
		}
	}
}