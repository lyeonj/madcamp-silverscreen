package com.example.silverscreen.ui.wish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silverscreen.databinding.FragmentWishBinding

class WishFragment : Fragment() {

    private var _binding: FragmentWishBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val wishViewModel =
            ViewModelProvider(this).get(WishViewModel::class.java)

        _binding = FragmentWishBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textWish
//        wishViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        return root
        val wishItems = List(20) { index -> "Wish Item ${index + 1}" }
        binding.recyclerWish.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WishAdapter(wishItems)
            setHasFixedSize(true) // 높이가 일정하면 성능 향상
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}