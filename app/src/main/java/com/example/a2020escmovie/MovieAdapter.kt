package com.example.a2020escmovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

/*
1. Movie Data Class 만들기
2. 영화 정보를 담은 ArrayList 만들기
3. RecyclerView Adapter 만들기
 */

// 영화 정보를 담고 있는 data class.
data class Movie(
    val title : String, //영화 제목 --> val로 선언하는 이유: Movie 값들은 한 번 선언하면 바꿀 일이 없어서. 선언한 그 값 그대로 리사이클러뷰에서 씀.
    val popularity : Double, //인기도
    val description : String, //설명
    val openDate : String, //개봉일
    val posterUrl : Int //포스터 Url <- Int : drawable에 있는 이미지 파일을 가져다 쓰기 위함. 다음수업에서는 String으로 쓸 거임.
)

//RecyclerView Adapter
class MovieAdapter(val context: Context, val movieList: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        //셀 레이아웃을 불러오는 역할.
        val view = LayoutInflater.from(context).inflate(R.layout.cell, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        //셀 개수를 설정하는 역할. 셀 개수를 반환하는 함수.
        // return 4 <- 이러면 영화 추가하거나 지울 때 숫자를 바꿔줘야 하는 문제 발생
        return movieList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //각 셀에 맞는 정보를 넣는 역할
        holder.bind(movieList[position])
    }

    //셀.
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //셀의 구성요소를 불러오는 역할.
        val iv_poster = itemView.findViewById<ImageView>(R.id.iv_poster)
        val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        val tv_popularity = itemView.findViewById<TextView>(R.id.tv_popularity)
        val tv_description = itemView.findViewById<TextView>(R.id.tv_description)
        val tv_openDate = itemView.findViewById<TextView>(R.id.tv_openDate)
        val container = itemView.findViewById<ConstraintLayout>(R.id.container) //컨테이너가 셀을 둘러싼 부분

        //데이터를 셀에 넣는 역할.
        fun bind(movie: Movie) {
            iv_poster.setImageResource(movie.posterUrl)
            tv_title.text = movie.title
            tv_popularity.text = "인기도: " + movie.popularity
            tv_description.text = "설명: " + movie.description
            tv_openDate.text = "개봉일: " + movie.openDate

            container.setOnClickListener { //셀을 클릭했을 때
                Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show() //영화 제목을 띄움.
            }


        }
    }
}