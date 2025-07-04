package com.example.silverscreen.ui.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.silverscreen.databinding.ItemWishBinding

class WishAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<WishAdapter.WishViewHolder>() {

    inner class WishViewHolder(private val binding: ItemWishBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String) {
            binding.tvWish.text = text
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
