package com.example.eyesight_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eyesight_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
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
}