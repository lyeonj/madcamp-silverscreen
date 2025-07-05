package com.example.silverscreen.ui.wish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silverscreen.R
import com.example.silverscreen.databinding.FragmentWishBinding
import com.example.silverscreen.ui.map.MapViewModel

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

        val wishViewModel =
            ViewModelProvider(this).get(WishViewModel::class.java)

        _binding = FragmentWishBinding.inflate(inflater, container, false)
        val textView: TextView = binding.textWish
        wishViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val wishItems = mutableListOf(
            WishItem(
                imageRes    = R.drawable.poster_parasite,
                title       = "기생충",
                date        = "2019.05.30",
                description = "제92회 아카데미 시상식 작품상, 감독상, 각본상, 국제영화상 수상작. " +
                        "상류층과 하류층, 두 가족의 만남을 다룬 " +
                        "대한민국의 사회고발물 성향의 블랙 코미디, 가족, 드라마 영화."
            ),
            WishItem(
                imageRes    = R.drawable.poster_lalaland,
                title       = "라라랜드",
                date        = "2016.08.31",
                description = "골든글로브 시상식의 7개 부문 수상작. " +
                        "2016년 개봉한 데이미언 셔젤 감독의 뮤지컬 영화. " +
                        "재즈 피아니스트 '세바스찬'(라이언 고슬링)과 성공을 꿈꾸는 배우 지망생 '미아'(엠마 스톤)의 로맨스 이야기."
            ),
            WishItem(
                imageRes    = R.drawable.poster_getout,
                title       = "겟아웃",
                date        = "2017.02.24",
                description = "제90회 아카데미 시상식 각본상 수상. " +
                        "미국의 공포 영화. 흑인 남자가 백인 여자친구의 집에 초대받으면서 벌어지는 이야기. " +
                        "영화 내에 복선이 많이 있고 미장센이 많아 영화 해석을 좋아하는 사람들에게 추천."
            ),
            WishItem(
                imageRes    = R.drawable.poster_mysterysen,
                title       = "센과 치히로의 행방불명",
                date        = "2001.07.20",
                description = "아카데미 장편 애니메이션상 수상, 2002년 베를린 영화제 황금곰상 수상. " +
                        "스튜디오 지브리 제작, 미야자키 하야오 감독의 극장 애니메이션. " +
                        "이상한 나라의 앨리스와 크라바트를 모티브로 한 영화."
            ),
            WishItem(
                imageRes    = R.drawable.poster_eternal,
                title       = "이터널선샤인",
                date        = "2004.03.19",
                description = "제77회 아카데미 시상식 각본상 수상, 여우주연상 후보작. " +
                        "미셸 공드리 감독의 2004년작 영화로 짐 캐리, 케이트 윈슬렛, 커스틴 던스트, 마크 러팔로, 일라이저 우드 출연. " +
                        "짐 캐리의 진지한 연기를 볼 수 있는 영화."
            ),
            WishItem(
                imageRes    = R.drawable.poster_nogateways,
                title       = "노인을 위한 나라는 없다",
                date        = "2007.05.19",
                description = "제80회 아카데미 시상식 작품상, 감독상, 남우조연상, 각색상 수상. " +
                        "코맥 매카시의 2005년 작 소설과 그 소설을 원작으로 제작한 코엔 형제 감독의 2007년작 미국 영화. " +
                        "1980년 배경으로 우연히 거액의 돈가방을 손에 넣은 남자가 사이코패스 살인마에게 쫒기면서 일어나는 이야기."
            ),
            WishItem(
                imageRes    = R.drawable.poster_walle,
                title       = "월-E",
                date        = "2008.06.27",
                description = "제81회 아카데미 시상식 장편 애니메이션상 수상 " +
                        "앤드루 스탠튼 감독의 픽사의 장편 애니메이션 영화. " +
                        "쓰레기 더미에 파묻힌 지구에 홀로 남겨진 청소 로봇 월-E와 식물을 탐색하기 위해 지구에 온 탐사 로봇 이브의 모험과 사랑 이야기."
            ),
            WishItem(
                imageRes    = R.drawable.poster_whiplash,
                title       = "위플래쉬",
                date        = "2014.01.16",
                description = "제68회 영국 아카데미 영화상 남우조연상, 음향상, 편집상 수상. " +
                        " 데이미언 셔젤 감독의 장편 2번째 작품. 동명의 단편영화 리메이크 작품. " +
                        " 음악학교에서 최고의 스튜디오 밴드에 들어가게 된 신입생 앤드류와 코치의 내면적 갈등 이야기."
            ),
            WishItem(
                imageRes    = R.drawable.poster_interstella,
                title       = "인터스텔라",
                date        = "2014.11.06",
                description = "제87회 아카데미 시상식 (미국)과 제68회 아카데미 시상식 (영국) 모두에서 시각효과상을 수상. " +
                        "점점 황폐해져가는 지구를 대체할 인류의 터전을 찾기 위해 새롭게 발견된 웜홀을 통해 항성 간 우주여행을 떠나는 탐험가들의 모험. "
            ),
            WishItem(
                imageRes    = R.drawable.poster_inception,
                title       = "인셉션",
                date        = "2010.07.16",
                description = "제83회 아카데미 시상식에서 촬영상, 음향상, 음향편집상, 시각효과상 수상. " +
                        "크리스토퍼 놀란의 하이스트 영화. 크리스토퍼 놀란이 10년간 시나리오를 쓰고 다듬은 작품. " +
                        "타인의 꿈에 들어가 생각을 훔치는 특수 보안요원 코브의 꿈과 현실 사이 이야기."
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