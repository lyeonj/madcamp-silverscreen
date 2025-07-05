package com.example.silverscreen.ui.wish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silverscreen.R
import com.example.silverscreen.databinding.FragmentWishBinding

class WishFragment : Fragment() {

    private var _binding: FragmentWishBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private lateinit var wishAdapter : WishAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWishBinding.inflate(inflater, container, false)

        val wishItems = mutableListOf(
            WishItem(
                imageRes    = R.drawable.poster_parasite,
                title       = "기생충",
                date        = "2019.05.30",
                description = "제92회 아카데미 시상식 작품상, 감독상, 각본상, 국제영화상 수상작. " +
                        "상류층과 하류층, 두 가족의 만남을 다룬 " +
                        "대한민국의 사회고발물 성향의 블랙 코미디, 가족, 드라마 영화"
            ),
            WishItem(
                imageRes    = R.drawable.poster_parasite,
                title       = "기생충",
                date        = "2019.05.30",
                description = "제92회 아카데미 시상식 작품상, 감독상, 각본상, 국제영화상 수상작. " +
                        "상류층과 하류층, 두 가족의 만남을 다룬 " +
                        "대한민국의 사회고발물 성향의 블랙 코미디, 가족, 드라마 영화"
            ),
            WishItem(
                imageRes    = R.drawable.poster_parasite,
                title       = "기생충",
                date        = "2019.05.30",
                description = "제92회 아카데미 시상식 작품상, 감독상, 각본상, 국제영화상 수상작. " +
                        "상류층과 하류층, 두 가족의 만남을 다룬 " +
                        "대한민국의 사회고발물 성향의 블랙 코미디, 가족, 드라마 영화"
            ),
            WishItem(
                imageRes    = R.drawable.poster_parasite,
                title       = "기생충",
                date        = "2019.05.30",
                description = "제92회 아카데미 시상식 작품상, 감독상, 각본상, 국제영화상 수상작. " +
                        "상류층과 하류층, 두 가족의 만남을 다룬 " +
                        "대한민국의 사회고발물 성향의 블랙 코미디, 가족, 드라마 영화"
            ),
            WishItem(
                imageRes    = R.drawable.poster_parasite,
                title       = "기생충",
                date        = "2019.05.30",
                description = "제92회 아카데미 시상식 작품상, 감독상, 각본상, 국제영화상 수상작. " +
                        "상류층과 하류층, 두 가족의 만남을 다룬 " +
                        "대한민국의 사회고발물 성향의 블랙 코미디, 가족, 드라마 영화"
            ),
            WishItem(
                imageRes    = R.drawable.poster_parasite,
                title       = "기생충",
                date        = "2019.05.30",
                description = "제92회 아카데미 시상식 작품상, 감독상, 각본상, 국제영화상 수상작. " +
                        "상류층과 하류층, 두 가족의 만남을 다룬 " +
                        "대한민국의 사회고발물 성향의 블랙 코미디, 가족, 드라마 영화"
            )
        )

        wishAdapter  = WishAdapter(wishItems) { pos, liked ->
            // 1) 아이템 리스트 순서 변경
            val item = wishItems.removeAt(pos)
            if (liked) {
                wishItems.add(0, item)
                wishAdapter.notifyItemMoved(pos, 0)
                binding.recyclerWish.scrollToPosition(0)
            }
            else {
                val likedCount = wishItems.count { it.liked }
                wishItems.add(likedCount, item)
                wishAdapter.notifyItemMoved(pos, likedCount)
            }
        }


        binding.recyclerWish.apply {
            layoutManager = LinearLayoutManager(context)
            adapter      = wishAdapter
            setHasFixedSize(true) // 높이가 일정하면 성능 향상
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}