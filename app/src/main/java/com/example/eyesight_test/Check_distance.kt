package com.example.eyesight_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Check_distance : AppCompatActivity() {
    var result: Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_distance)

    }

    fun btn_Click(view: View?) {
        val radioGroup = findViewById<View>(R.id.radio_group) as RadioGroup
        val editText = findViewById<View>(R.id.editText) as EditText
        if(IsCorrect(editText.text.toString()) && editText.text.toString().length == 3){
            when (radioGroup.checkedRadioButtonId) {
                R.id.rg_btn1 -> result = ((editText.text.toString().toInt() - 40) / 2)
                R.id.rg_btn2 -> result = ((editText.text.toString().toInt() - 35) / 2)
            }
            val intent = Intent(this, Check_eyesight::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this@Check_distance, "정보를 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    fun IsCorrect(S: String): Boolean {
        val regex = Regex("[0-9]+[0-9]+[0-9]")
        val correct = S.matches(regex)
        return correct
    }
}