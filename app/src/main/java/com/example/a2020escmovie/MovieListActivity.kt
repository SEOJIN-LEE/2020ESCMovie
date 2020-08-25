package com.example.a2020escmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_movie_list.*
import org.json.JSONException

/*
1. Movie Data Class 만들기 (O)
2. 영화 정보를 담은 ArrayList 만들기 (O)
3. RecyclerView Adapter 만들기
 */


/*
0. Manifest에 Internet 퍼미션 적용 + Volley, GSON, Glide 라이브러리를 프로젝트에 추가.
1. Volley 라이브러리를 이용해서 TMDB으로부터 현재 상영 중인 영화 정보를 받아오기.
2. GSON 라이브러리를 이용해서 JSON -> Data Class로 변환.
3. Glide 라이브러리를 이용해서 포스터 URL로부터 이미지를 받아서 ImageView에 적용.
 */

class MovieListActivity : AppCompatActivity() {

    // 영화 정보를 담고 있는 ArrayList 만들기.
    val movieList : ArrayList<Movie> = arrayListOf(
        Movie("짱구는 못말려:신혼여행 허리케인", 10.976, "짱구 THE Movie...", "2020-08-07", R.drawable.m1),
        Movie("테넷", 22.433, "시간의 흐름은...", "2020-08-26", R.drawable.m2),
        Movie("소년 시절의 너", 17.321, "넌 세상을 지켜,...", "2020-07-09", R.drawable.m4),
        Movie("덩케르크 이스케이프", 15.842, "역사에 기록되지 않은...", "2020-08-03", R.drawable.m3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        //Volley의 RequestQueue 선언.
        var requestQueue: RequestQueue = Volley.newRequestQueue(this)

        //API 주소 선언.
        val url = "https://api.themoviedb.org/3/movie/now_playing?" + "api_key=7ccb6d356c33957eb928c761ae8b3b02" + "&language=ko-KR" + "&region=KR"

        //API를 호출함.
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { //데이터가 정상적으로 호출됐을 때 처리하는 부분.
            response -> try { //response(영화 JSON 데이터)가 정상적으로 넘어온 경우.
                //받아온 json 데이터를 Toast message로 출력.
                Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show()
            } catch (e: JSONException) { //response가 정상적으로 넘어오지 않은 경우.
                //오류 내용을 Toast message로 출력.
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }, Response.ErrorListener { //데이터가 정상적으로 호출되지 않았을 때 처리하는 부분
                //오류 내용을 Toast message로 출력.
                error -> Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            })

        requestQueue.add(request)

        val adapter = MovieAdapter(this, movieList) //Adapter 선언
        movieRecycler.adapter = adapter //RecyclerView에 우리가 만든 MovieAdapter 세팅

        val lm = LinearLayoutManager(this) //LinearLayoutManager 선언
        movieRecycler.layoutManager = lm //RecyclerView에 LinearLayoutManager 세팅
    }
}