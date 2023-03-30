package com.example.diet_memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        auth = Firebase.auth

        //현재 user의 UID 값을 가져와라란 코드
        //Log.d("SPLASH", auth.currentUser!!.uid)
        //위에 한 줄만 적게 될 경우 app이 죽게 된다.
        // 그래서 try catch 문을 작성했다.

        try {
            // 현재 user의 UID 값을 가져와라(에러가 발생할 수 있는 코드)
            // splash 화면 띄운 뒤에 현재 로그인 정보가 있는지 확인한다.
            Log.d("SPLASH", auth.currentUser!!.uid)
            //로그인 정보가 있으면 원래 비회원이란 toast가 뜬다.
            Toast.makeText(baseContext, "원래 비회원 로그인이 되어있는 사람입니다.", Toast.LENGTH_SHORT).show()

            //splash 끝나고, MainAcitiviy 화면으로 전환
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 3000)

        } catch(e : Exception) {
            Log.d("SPLASH", "로그인이 필요합니다.")

            //에러 시 수행할 코드
            // 회원가입을 시켜주는 코드다
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) { //UID 값이 없으면 로그인 성공?(auth.currentUser!!.uid) 값
                        // Sign in success, update UI with the signed-in user's information
                        // 비회원으로 로그인하기 성공
                        Toast.makeText(baseContext, "Non-member login successful.", Toast.LENGTH_SHORT).show()

                        Handler().postDelayed({
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }, 3000)
                    } else {
                        // If sign in fails, display a message to the user.
                        // 비회원 로그인 실패
                        Toast.makeText(baseContext, "Non-members login failed", Toast.LENGTH_SHORT).show()
                    }
                }

}
            Log.d("SPLASH", "Need to register")
        }


}