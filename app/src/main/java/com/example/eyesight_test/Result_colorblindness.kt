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
            if (answer_list!![1]>=3){
                if (answer_list!![2]>=3) {
                    mBinding!!.resultView.text="적녹색 혹은 전색약(맹)이 있습니다."
                }
                else{
                    mBinding!!.resultView.text="적녹색약(맹)이 있습니다."

                }
            }
            else if (answer_list!![2]>=3){
                mBinding!!.resultView.text="전색약(맹)이 있습니다."
            }
            else{
                "정상입니다."
            }
        }
        mBinding!!.button1.setOnClickListener(){
            finish()
        }
    }
}