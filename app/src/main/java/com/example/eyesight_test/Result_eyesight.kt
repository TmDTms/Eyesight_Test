package com.example.eyesight_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eyesight_test.databinding.ActivityResultColorblindnessBinding
import com.example.eyesight_test.databinding.ActivityResultEyesightBinding

class Result_eyesight : AppCompatActivity() {
    private var mBinding: ActivityResultEyesightBinding ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityResultEyesightBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        if(intent.hasExtra("eyesight")){
            val eyesight = intent.getDoubleExtra("eyesight", 1.0)
            mBinding!!.eyesightView.text = String.format("%.1f", eyesight)
        }
        mBinding!!.button1.setOnClickListener(){
            finish()  //액티비티 종료
        }
    }
}