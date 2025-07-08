package com.example.silverscreen.ui.seen

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.silverscreen.R

class SeenAdapter(
    private val context: Context,
    private val items: MutableList<MovieItem>,
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

        try {
            if (item.imagePath.startsWith("seen_scene")) {
                val assetManager = context.assets
                assetManager.open(item.imagePath).use { inputStream ->
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    holder.imageView.setImageBitmap(bitmap)
                }
            } else if (item.imagePath == "default.png") {
                val assetManager = context.assets
                assetManager.open("default.png").use { inputStream ->
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    holder.imageView.setImageBitmap(bitmap)
                }
            } else {
                val imageUri = Uri.parse(item.imagePath)
                Glide.with(context)
                    .load(Uri.parse(item.imagePath))
                    .into(holder.imageView)
            }
        } catch (e: Exception) {
            e.printStackTrace()

            // 예외 발생 시 default.png 출력
            try {
                val assetManager = context.assets
                assetManager.open("default.png").use { inputStream ->
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    holder.imageView.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (isListView) {
            holder.titleView?.text = item.title
            holder.ratingView?.text = "★".repeat(item.rating)
            holder.reviewView?.text = "${item.review}"
            holder.datePlaceView?.text = "${item.date}  |  ${item.place}"
        }
    }

    fun updateItems(newItems: List<MovieItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}