package com.example.silverscreen.ui.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.silverscreen.databinding.ItemWishBinding
import com.example.silverscreen.R
import android.graphics.Color

class WishAdapter(
    private val items: MutableList<WishItem>,
    private val onHeartClick: (position: Int, liked: Boolean) -> Unit
) : RecyclerView.Adapter<WishAdapter.WishViewHolder>() {

    inner class WishViewHolder(private val binding: ItemWishBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WishItem) {

            binding.ivPoster.setImageResource(item.imageRes)

            binding.tvTitle.text       = item.title
            binding.tvDate.text        = item.date
            binding.tvDescription.text = item.description

            if (item.liked) {
                binding.btnHeart.setImageResource(R.drawable.ic_heart_filled)
                binding.btnHeart.setColorFilter(Color.parseColor("#F96D2B"))
            } else {
                binding.btnHeart.setImageResource(R.drawable.ic_heart)
                binding.btnHeart.setColorFilter(Color.parseColor("#D0D0D0"))
            }

            binding.btnHeart.setOnClickListener {
                item.liked = !item.liked
                if (item.liked) {
                    binding.btnHeart.setImageResource(R.drawable.ic_heart_filled)
                    binding.btnHeart.setColorFilter(Color.parseColor("#F96D2B"))
                } else {
                    binding.btnHeart.setImageResource(R.drawable.ic_heart)
                    binding.btnHeart.setColorFilter(Color.parseColor("#D0D0D0"))
                }

                val pos = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                onHeartClick(pos, item.liked)
                }
            }


        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishViewHolder {
        val binding = ItemWishBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return WishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
