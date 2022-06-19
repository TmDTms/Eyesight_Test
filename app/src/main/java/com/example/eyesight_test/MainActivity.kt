package com.example.eyesight_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.eyesight_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var waitTime = 0L
    private var mBinding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        mBinding!!.button1.setOnClickListener(){
            val intent = Intent(this, Check_distance::class.java)
            startActivity(intent)
        }
        mBinding!!.button2.setOnClickListener(){
            val intent = Intent(this, Check_colorblindness::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        if(System.currentTimeMillis() - waitTime >= 1500){
            waitTime = System.currentTimeMillis()
            Toast.makeText(this,"뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            finish()
        }
    }
}