// 학습내용

// 1. 비회원 인증
// splash 화면 3초 동안 뜨고
// 비회원이면 이미 비회원입니다가 뜨면서 MainActivity로 전환
// 비회원이 아니라면 그 사람한테 UID 줘서 비회원으로 만들어주기
// 만약 비회원으로 만드는 데 실패했다면 Failed가 뜨도록 해주기

// 2. 다이얼로그
// 아이콘 클릭 시 다이얼로그 뜨도록 하기
// 다이얼로그에 있는 버튼 클릭 시 캘린더 뜨도록 하기
// 캘린더에서 날짜 선택 시 년, 월, 일 text가
// 버튼 text에 나타나도록 하기

// 3. DB
// 메모를 입력하면 db에 저장되고, db에 저장된 걸 다시 불러와보자.

package com.example.diet_memo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.Calendar
import java.util.GregorianCalendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //pencil icon 클릭 시
        val writeButton = findViewById<ImageView>(R.id.writeBtn)
        writeButton.setOnClickListener {


            //다이얼로그(팝업창같은 거) 띄우는 마법의 문장
            // pencil icon 클릭 시  '운동 메모 다이얼로그'가 뜬다.
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("운동 메모 다이얼로그")

            // mBuilder.show()

            // 위에까지 작성하면 팝업창만 뜨고, "날짜를 선택해주세요" 버튼
            // 클릭 시 이벤트가 없어서 그 event를 만들어 줬다.
            //일단 mBuilder.show()를 mAlertDialog에 넣어줌

            val mAlertDialog = mBuilder.show()

            val DateSelectBtn = mAlertDialog.findViewById<Button>(R.id.dateSelectBtn)

            DateSelectBtn?.setOnClickListener {

                val today = GregorianCalendar()
                val year: Int = today.get(Calendar.YEAR)
                val month: Int = today.get(Calendar.MONTH)
                val date: Int = today.get(Calendar.DATE)


                val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(
                        view: DatePicker?,
                        year: Int,
                        month: Int,
                        dayOfMonth: Int
                    ) {
                        //month 뒤에는 +1 해줘야 한다.
                        Log.d("MAIN", "${year}, ${month + 1}, ${dayOfMonth}")

                        // 사용자가 날짜를 선택했을 시
                        // 버튼에 있는 text인 "날짜를 선택해주세요"를
                        // 연, 월, 일로 변경해라
                        DateSelectBtn.setText("${year}, ${month + 1}, ${dayOfMonth}")

                    }

                }, year, month, date)
                dlg.show()

                // 메모를 입력해서 DB에 저장하고, 불러오자.

            }


            // 저장하기 버튼을 찾아오라라
           val saveBtn = mAlertDialog.findViewById<Button>(R.id.saveBtn)

            // 저장하기 버튼이 클릭되면 db에 저장되도록 해야한다.
            saveBtn?.setOnClickListener {

                // Write a message to the database
                val database = Firebase.database
                val myRef = database.getReference("message")

                //firebase db에 hello world라는 데이터를 넣어준 것이다.
                myRef.setValue("Hello, World!")


            }


            }

        }

    }



