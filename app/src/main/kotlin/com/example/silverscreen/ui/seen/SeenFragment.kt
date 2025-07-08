package com.example.silverscreen.ui.seen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silverscreen.R
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

        setupAddButton()
        setupViewModeButton()
        setupRecyclerView()

        return binding.root
    }

    private fun setupAddButton() {
        binding.btnAdd.setOnClickListener {
            val popupView = layoutInflater.inflate(R.layout.popup, null)

            val displayMetrics = resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels

            val popupWidth = (screenWidth * 0.75).toInt()
            val popupHeight = (screenHeight * 0.6).toInt()

            val popupWindow = PopupWindow(popupView, popupWidth, popupHeight, true)

            // background + radius
            popupWindow.setBackgroundDrawable(
                requireContext().getDrawable(R.drawable.popup_radius_black)
            )

            // 팝업창 외부 클릭 시 닫힘
            popupWindow.isOutsideTouchable = true
            popupWindow.isFocusable = true

            // 뒷배경 어둡게
            val parent = activity?.window?.decorView?.rootView
            val dim = View(activity)
            dim.setBackgroundColor(Color.parseColor("#B3000000"))
            val parentGroup = parent as? ViewGroup
            parentGroup?.addView(dim, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            popupWindow.setOnDismissListener {
                parentGroup?.removeView(dim)
            }

            // 입력 필드 포커스 제거
            val inputTitle = popupView.findViewById<View>(R.id.inputTitle)
            inputTitle.clearFocus()

            val scroll = popupView.findViewById<View>(R.id.popupScrollView)
            scroll?.requestFocus()

            // 팝업창 화면 중앙 정렬
            popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
        }
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

        val movieItems = seenViewModel.getMovieList()
        adapter = SeenAdapter(requireContext(), movieItems, isListView)

        binding.recyclerSeen.layoutManager = layoutManager
        binding.recyclerSeen.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}