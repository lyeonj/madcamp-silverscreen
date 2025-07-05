package com.example.silverscreen.ui.seen

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.silverscreen.R

class SeenAdapter(
    private val context: Context,
    private val imagePaths: List<String>
) : RecyclerView.Adapter<SeenAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_seen, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int = imagePaths.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val assetManager = context.assets
        val inputStream = assetManager.open(imagePaths[position])
        val bitmap = BitmapFactory.decodeStream(inputStream)

        holder.imageView.setImageBitmap(bitmap)
        inputStream.close()
    }
}