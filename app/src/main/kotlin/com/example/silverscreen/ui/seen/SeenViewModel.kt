package com.example.silverscreen.ui.seen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeenViewModel : ViewModel() {

    private val _text = MutableLiveData("My Ticket")
    val text: LiveData<String> = _text

    private val _movieList = MutableLiveData<MutableList<MovieItem>>(mutableListOf(
            MovieItem("seen_poster/poster_1.png","Interstellar",5, "“Murph, I love you, forever.”", "2025.07.09.", "CGV 용산아이파크몰"),
            MovieItem("seen_poster/poster_2.png","The Truman Show",5, "“Just in case, Good Afternoon, Good Evening, and Good Night.”", "2025.07.07.", "롯데시네마 월드타워점"),
            MovieItem("seen_poster/poster_3.png","Spider Man",4, "힘에는 책임이 따른다", "2025.07.06.", "CGV 건대입구"),
            MovieItem("seen_poster/poster_4.png","기생충",5, "“너는 계획이 다 있구나.”", "2025.07.03.", "메가박스 스타필드코엑스몰"),
            MovieItem("seen_poster/poster_5.png","Wicked",3, "뮤지컬의 장르적 한계를 영화의 CG 기술로 넘어서다.", "2025.07.01.", "롯데시네마 월드타워점"),
            MovieItem("seen_poster/poster_6.png","The Martian",4, "화성에서 감자 키우기..", "2025.06.30.", "CGV 용산아이파크몰"),
            MovieItem("seen_poster/poster_7.png","Toy Story",4, "You’ve got a friend in me~♪", "2025.06.27.", "메가박스 스타필드코엑스몰"),
            MovieItem("seen_poster/poster_8.png","Cruella",2, "잔인하다기보단 애달픈 ‘가족’영화.", "2025.06.24.", "CGV 용산아이파크몰"),
            MovieItem("seen_poster/poster_9.png","La La Land",3, "꿈꾸는 모든 이들을 위한 영화", "2025.06.20.", "롯데시네마 월드타워점"),
            MovieItem("seen_poster/poster_10.png","Joker",2, "웃긴데 안 웃긴 이야기", "2025.06.18.", "CGV 건대입구"),
            MovieItem("seen_poster/poster_11.png","47 Meters Down",4,"“We're going to die here!”", "2025.06.17.", "CGV 용산아이파크몰"),
            MovieItem("seen_poster/poster_12.png","Avengers",3, "Avengers, assemble!", "2025.06.15.", "메가박스 스타필드코엑스몰"),
            MovieItem("seen_poster/poster_13.png","Star Wars",2, "“May the Force be with you.”", "2025.06.12.", "롯데시네마 월드타워점"),
            MovieItem("seen_poster/poster_14.png","Barbie",2, "“She's everything. Ken is just Ken.”", "2025.06.07.", "CGV 건대입구"),
            MovieItem("seen_poster/poster_15.png","Get Out",3, "“Get out!”", "2025.06.01.", "메가박스 스타필드코엑스몰"),
            MovieItem("seen_poster/poster_16.png","부산행",4, "한국에서 볼 수 없었던 영화", "2025.05.29.", "롯데시네마 월드타워점"),
            MovieItem("seen_poster/poster_17.png","23 아이덴티티",3, "정체성의 파편이 만든 괴물", "2025.05.13.","CGV 용산아이파크몰"),
            MovieItem("seen_poster/poster_18.png","Minecraft",2, "상상이 곧 세계가 되는 곳", "2025.05.05.","CGV 건대입구"),
            MovieItem("seen_poster/poster_19.png","Bohemian Rhapsody",3,"“We are the champions!”","2025.04.27.","메가박스 스타필드코엑스몰"),
            MovieItem("seen_poster/poster_20.png","범죄도시",4,"한 대 맞고 시작하는 영화","2025.03.20.","롯데시네마 월드타워점")
    ))

    val movieList: LiveData<MutableList<MovieItem>> = _movieList

    fun addMovie(item: MovieItem) {
        _movieList.value?.add(0, item)
        _movieList.value = _movieList.value
    }
}