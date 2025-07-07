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
    private val imagePaths: List<String>,
    private val isListView: Boolean
) : RecyclerView.Adapter<SeenAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleView: TextView? = itemView.findViewById(R.id.titleView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutId = if (isListView) R.layout.item_seen_list else R.layout.item_seen_grid
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int = imagePaths.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val assetManager = context.assets
        val inputStream = assetManager.open(imagePaths[position])
        val bitmap = BitmapFactory.decodeStream(inputStream)

        holder.imageView.setImageBitmap(bitmap)
        inputStream.close()

        // ListView일 때 제목 표시
        if (isListView) {
            holder.titleView?.text = extractTitle(imagePaths[position])
        }
    }

    private fun extractTitle(path: String): String {
        // 파일 이름에서 확장자 제거하고 공백으로 치환
        return path.substringAfterLast("/")
            .substringBeforeLast(".")
            .replace("_", " ")
            .replaceFirstChar { it.uppercaseChar() }
    }
}