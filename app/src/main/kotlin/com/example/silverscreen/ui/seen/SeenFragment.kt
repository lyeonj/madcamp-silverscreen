package com.example.silverscreen.ui.seen

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silverscreen.databinding.FragmentSeenBinding

class SeenFragment : Fragment() {

    private var _binding: FragmentSeenBinding? = null
    private val binding get() = _binding!!

    private lateinit var seenViewModel: SeenViewModel
    private lateinit var adapter: SeenAdapter

    private var isListView = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeenBinding.inflate(inflater, container, false)
        seenViewModel = ViewModelProvider(this)[SeenViewModel::class.java]

        seenViewModel.text.observe(viewLifecycleOwner) { value ->
            binding.textSeen.text = value
        }

        setupViewModeButton()
        setupRecyclerView()

//        binding.btnList.setOnClickListener {
//            isListView = true
//            setupRecyclerView()
//        }
//
//        binding.btnGrid.setOnClickListener {
//            isListView = false
//            setupRecyclerView()
//        }

        return binding.root
    }

    private fun setupViewModeButton() {
        binding.btnGrid.setOnClickListener {
            isListView = false
            updateIconColors()
            setupRecyclerView()
        }

        binding.btnList.setOnClickListener {
            isListView = true
            updateIconColors()
            setupRecyclerView()
        }

        updateIconColors()
    }

    // View Mode 선택 시 해당 아이콘 색상 WHITE로 변경
    private fun updateIconColors() {
        val gridColor = if (!isListView) Color.WHITE else Color.parseColor("#505050")
        val listColor = if (isListView) Color.WHITE else Color.parseColor("#505050")

        binding.btnGrid.setColorFilter(gridColor)
        binding.btnList.setColorFilter(listColor)
    }

    private fun setupRecyclerView() {
        val layoutManager = if (isListView) {
            LinearLayoutManager(requireContext())
        } else {
            GridLayoutManager(requireContext(), 3)
        }

        adapter = SeenAdapter(requireContext(), seenViewModel.getImageFileNames(), isListView)
        binding.recyclerSeen.layoutManager = layoutManager
        binding.recyclerSeen.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}