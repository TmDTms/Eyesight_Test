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
        if (intent.hasExtra("result")){ //result라는 key값이 있는지
            val answer_list = intent.getIntegerArrayListExtra("result") //result키 값에서 int arraylist 받아옴
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
                mBinding!!.resultView.text="정상입니다."
            }
        }
        mBinding!!.button1.setOnClickListener(){
            finish()  //액티비티 종료
        }
    }
}