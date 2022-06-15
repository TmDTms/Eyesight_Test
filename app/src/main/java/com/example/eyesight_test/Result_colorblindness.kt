package com.example.eyesight_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eyesight_test.databinding.ActivityResultColorblindnessBinding

class Result_colorblindness : AppCompatActivity() {
    private var mBinding: ActivityResultColorblindnessBinding?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityResultColorblindnessBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        if (intent.hasExtra("result")){
            val answer_list = intent.getIntegerArrayListExtra("result")
            System.out.println("결과 창으로")
            System.out.println(answer_list)
            for(i in answer_list!!){
                System.out.println(i)
            }
        }

    }
}