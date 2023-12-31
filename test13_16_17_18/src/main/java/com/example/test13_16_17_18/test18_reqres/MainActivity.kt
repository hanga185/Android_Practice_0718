package com.example.test13_16_17_18.test18_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13_16_17_18.databinding.ActivityMain8Binding
import com.example.test13_16_17_18.test18_reqres.Model.UserListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// xml : main8

// 경로 https://github.com/lsy3709/K230201AndroidLab/tree/master/test18
// 구성 retrofit(2) ,
// 1) 리사이클러 뷰 구성 ( 어댑터, 뷰홀더 ),
// 2) 래트로핏 관련 설정,
// 3) 받아오는 데이터 모델링

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMain8Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //test , MyApplication에서 만든 형으로 다운캐스팅해서, 해당 객체 생성.
        val networkService = (applicationContext as MyApplication).networkService

        // INetworkService에 정의된 추상 함수이고, MyApplication 클래스에 붙였다.
        // 예  doGetUserList(2)
        // 여기에 함수의 형식이 계속 변경이 됩니다.
        val userListCall = networkService.doGetUserList(2)

        // 버튼1을 누르면 해당 test1()의 결과값 받아오기.
        binding.btn1test1.setOnClickListener {
            // 해당 애너테이션 정의 부분 사용해보기 예제2) @GET("users/list?sort=desc")
//            val test1 = networkService.test1()
            // 테스트 중
            val test1 = networkService.doGetUserList(2)
//            fun doGetUserList(@Query("page") page: String): Call<UserListModel>
            // UserModel을 UserListModel로 바꿧음

            test1.enqueue(object : Callback<UserListModel> {
                override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
                    val userModelSample = response.body()
                    Log.d("lsy", "test1()호출예제2이고 값조회: ${userModelSample}")
                }

                override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }


        // 실제 통신을 해서 사용하기. 서버와 통신해서, 데이터를 받는다.
        userListCall.enqueue(
            //Callback : import 함.
            object : Callback<UserListModel> {
                // Callback 인터페이스를 구현한 특정 표현식을 쓸때,
                // 의무적으로 재정의를 해야하는 함수2개.
                // onResponse : 서버로 부터 응답 성공 했을 때, 수행하는 함수.
                // onFailure : 실패 했을 때, 수행하는 함수.
                override fun onResponse(
                    call: Call<UserListModel>,
                    response: Response<UserListModel>
                ) {
                    // 정상적으로 서버로부터 데이터를 받아왔다.
                    // 실제로 데이터를 받고나서, response.body 곳에 data가 있음.
                    val userList = response.body()

                    // 받아온 데이터를 콘솔에 출력.
                    // 실제 작업은 뷰에 바인딩.
                    // 리사이클러 뷰를 작업.
                    Log.d("lsy", "userList(response.body)의 값: ${userList} ")
                }

                override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })


    }
}