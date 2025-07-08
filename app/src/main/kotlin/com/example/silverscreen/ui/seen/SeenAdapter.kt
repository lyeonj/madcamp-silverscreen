package com.example.silverscreen.ui.seen

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.silverscreen.R

class SeenAdapter(
    private val context: Context,
    private val items: List<MovieItem>,
    private val isListView: Boolean
) : RecyclerView.Adapter<SeenAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleView: TextView? = itemView.findViewById(R.id.titleView)
        val ratingView: TextView? = itemView.findViewById(R.id.ratingView)
        val reviewView: TextView? = itemView.findViewById(R.id.reviewView)
        val datePlaceView: TextView? = itemView.findViewById(R.id.datePlaceView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutId = if (isListView) R.layout.item_seen_list else R.layout.item_seen_grid
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = items[position]
        val assetManager = context.assets
        val inputStream = assetManager.open(item.imagePath)
        val bitmap = BitmapFactory.decodeStream(inputStream)

        holder.imageView.setImageBitmap(bitmap)
        inputStream.close()

        if (isListView) {
            holder.titleView?.text = item.title
            holder.ratingView?.text = "★".repeat(item.rating)
            holder.reviewView?.text = "${item.review}"
            holder.datePlaceView?.text = "${item.date}  |  ${item.place}"
        }
    }
}