package com.example.test6_dum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        
        // 매니페스트 파일, 앱의 설정 파일인데, 시스템 클래스 등
        // 기본 메인 액티비티 화면이 시작시 항상 보여주었음
        // 그래서 우리가 만든 인트로 화면을 메인 대신 보여주고
        // 핸들러 특정 시간 3000ms(3초) 후 원래 메인 화면으로 이동

        // 자바 코드 버젼
       // Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },3000);

//        val handler: Handler = Handler();

        Handler().postDelayed({
            val intent = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }

}