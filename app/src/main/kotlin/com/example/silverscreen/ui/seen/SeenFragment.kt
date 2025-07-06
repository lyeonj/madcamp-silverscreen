package com.example.silverscreen.ui.seen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.silverscreen.databinding.FragmentSeenBinding

class SeenFragment : Fragment() {

    private var _binding: FragmentSeenBinding? = null
    private val binding get() = _binding!!

    private lateinit var seenViewModel: SeenViewModel
    private lateinit var adapter: SeenAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeenBinding.inflate(inflater, container, false)

        seenViewModel = ViewModelProvider(this).get(SeenViewModel::class.java)

        seenViewModel.text.observe(viewLifecycleOwner) { value ->
            binding.textSeen.text = value
        }
        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = SeenAdapter(requireContext(), seenViewModel.getImageFileNames())
        binding.recyclerSeen.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerSeen.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}