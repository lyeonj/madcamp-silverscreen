package com.example.silverscreen.ui.seen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.silverscreen.R
import com.example.silverscreen.databinding.FragmentSeenBinding

class SeenFragment : Fragment() {

    private var _binding: FragmentSeenBinding? = null
    private val binding get() = _binding!!

    private lateinit var seenViewModel: SeenViewModel
    private lateinit var adapter: SeenAdapter

    private var isListView = false

    private val IMAGE_PICK_CODE = 1001
    private var selectedImageView: ImageView? = null
    private var selectedImageUri: Uri? = null

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

        seenViewModel.movieList.observe(viewLifecycleOwner) { items ->
            if (::adapter.isInitialized) {
                adapter.updateItems(items)
            } else {
                val layoutManager = if (isListView) {
                    LinearLayoutManager(requireContext())
                } else {
                    GridLayoutManager(requireContext(), 3)
                }
                adapter = SeenAdapter(requireContext(), items.toMutableList(), isListView)
                binding.recyclerSeen.layoutManager = layoutManager
                binding.recyclerSeen.adapter = adapter
            }
        }

        return binding.root
    }

    private fun setupAddButton() {
        binding.btnAdd.setOnClickListener {
            val popupView = layoutInflater.inflate(R.layout.popup_add_ticket, null)

            val displayMetrics = resources.displayMetrics
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels

            val popupWidth = (screenWidth * 0.75).toInt()
            val popupHeight = (screenHeight * 0.65).toInt()

            val popupWindow = PopupWindow(popupView, popupWidth, popupHeight, true).apply {
                // background + radius
                setBackgroundDrawable(requireContext().getDrawable(R.drawable.popup_radius_black))
                // 팝업창 외부 클릭 시 닫힘
                isOutsideTouchable = true
                isFocusable = true
            }

            // 뒷배경 어둡게
            val parent = activity?.window?.decorView?.rootView
            val dim = View(activity)
            dim.setBackgroundColor(Color.parseColor("#CC000000"))
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

            // 이미지 업로드
            val imageUpload = popupView.findViewById<FrameLayout>(R.id.imageUpload)
            val plusIcon = popupView.findViewById<ImageView>(R.id.plusIcon)

            imageUpload.setOnClickListener {
                selectedImageView = plusIcon
                checkPermissionAndOpenGallery()
            }

            val saveBtn = popupView.findViewById<Button>(R.id.saveBtn)
            saveBtn.setOnClickListener {
                val title = popupView.findViewById<EditText>(R.id.inputTitle).text.toString()
                val rating = popupView.findViewById<RatingBar>(R.id.ratingBar).rating.toInt()
                val review = popupView.findViewById<EditText>(R.id.inputReview).text.toString()
                val date = popupView.findViewById<EditText>(R.id.inputDate).text.toString()
                val place = popupView.findViewById<EditText>(R.id.inputPlace).text.toString()

                val imagePath = selectedImageUri?.toString() ?: "default.png"
                val newItem = MovieItem(imagePath, title, rating, review, date, place)
                seenViewModel.addMovie(newItem)
                selectedImageUri = null
                popupWindow.dismiss()
            }

            // 팝업창 화면 중앙 정렬
            popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
        }
    }

    private fun checkPermissionAndOpenGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_MEDIA_IMAGES
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(Manifest.permission.READ_MEDIA_IMAGES), 2001)
            } else {
                openGallery()
            }
        } else {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 2001)
            } else {
                openGallery()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            selectedImageUri = imageUri

            val plusIcon = selectedImageView
            val parent = plusIcon?.parent as? FrameLayout ?: return

            if (imageUri != null && plusIcon != null) {
                // 기존 이미지 제거 (plusIcon 제외)
                for (i in 0 until parent.childCount) {
                    val child = parent.getChildAt(i)
                    if (child is ImageView && child != plusIcon) {
                        parent.removeView(child)
                        break
                    }
                }

                // 새 이미지뷰 생성
                val imageView = ImageView(requireContext()).apply {
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }

                // Glide로 이미지 로드
                Glide.with(requireContext())
                    .load(imageUri)
                    .into(imageView)

                // plus 아이콘 숨기고 이미지뷰 추가
                plusIcon.visibility = View.GONE
                parent.addView(imageView)
            }
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
        seenViewModel.movieList.value?.let {
            adapter = SeenAdapter(requireContext(), it.toMutableList(), isListView)
            val layoutManager = if (isListView) {
                LinearLayoutManager(requireContext())
            } else {
                GridLayoutManager(requireContext(), 3)
            }
            binding.recyclerSeen.layoutManager = layoutManager
            binding.recyclerSeen.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}