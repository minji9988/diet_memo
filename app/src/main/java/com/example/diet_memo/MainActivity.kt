package com.example.diet_memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //pencil icon 클릭 시
        val writeButton = findViewById<ImageView>(R.id.writeBtn)
        writeButton.setOnClickListener {


            //다이얼로그 띄우는 마법의 문장
            // pencil icon 클릭 시  '운동 메모 다이얼로그'라는 팝업창 같은 게뜬다.
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("운동 메모 다이얼로그")

            // mBuilder.show()
            // 위에까지 작성하면 팝업창만 뜨고, "날짜를 선택해주세요" 버튼
            // 클릭 시 이벤트가 없어서 그 event를 만들어 줬다.

            

        }

    }



}