package com.example.eyesight_test

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
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
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.activity_dialog)
            dialog.setTitle("Custom Dialog")
            val text: TextView = dialog.findViewById(R.id.text) as TextView
            text.setText("위 사진과 같이 의자에 등을 붙이고\n팔을 쭉 뻗어 측정을 시도해주세요.")
            val iv: ImageView = dialog.findViewById(R.id.image) as ImageView
            iv.setImageResource(R.drawable.person)
            dialog.show()
        } else {
            Toast.makeText(this@Check_distance, "정보를 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    fun IsCorrect(S: String): Boolean {
        val regex = Regex("[0-9]+[0-9]+[0-9]")
        val correct = S.matches(regex)
        return correct
    }

    fun NextBtn(view: View?){
        val intent = Intent(this, Check_eyesight::class.java)
        startActivity(intent)
        finish()
    }
}