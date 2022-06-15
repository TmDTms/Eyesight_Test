package com.example.eyesight_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eyesight_test.databinding.ActivityCheckColorblindnessBinding

class Check_colorblindness : AppCompatActivity() {
    private var check_answer = 0
    private var image_num = 1
    private var answer_list = arrayListOf<Int>()    //선택한 답 리스트
    private var answer: IntArray? = null//정답 리스트
    //private var resultcheck= arrayListOf<Int>(0,0,0,0)    // 정상, 적, 녹, 청색맹
    private var choice_num: IntArray? = null
    private var mBinding: ActivityCheckColorblindnessBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCheckColorblindnessBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        answer = resources.getIntArray(applicationContext.resources.getIdentifier("correct","array",applicationContext.packageName))
        Set_problem_image(1)
        Set_ChoiceNum(image_num)
        mBinding!!.checkGroup.setOnCheckedChangeListener { group, i ->
            when(i){
                R.id.select1 ->
                {
                   check_answer =  1
                }
                R.id.select2 ->{
                    check_answer= 2
                }
                R.id.select3 ->{
                    check_answer= 3
                }
                R.id.select4 ->{
                    check_answer= 4
                }
            }

        }
        mBinding!!.completeBtn.setOnClickListener(){
            if (image_num<4){
                if (check_answer!=0){
                    image_num++
                    answer_list.add(choice_num!![check_answer-1].toInt())
                    Set_problem_image(image_num)
                    Set_ChoiceNum(image_num)

                }
                else{
                    System.out.println("답을 골라주세요.")
                }
            }
            else{
                answer_list.add(choice_num!![check_answer-1].toInt())
                val intent = Intent(this, Result_colorblindness::class.java)
                intent.putExtra("result",answer_list)
                startActivity(intent)
            }
        }

    }
    fun Set_problem_image(num : Int){
        mBinding!!.questionImage.setBackgroundResource(resources.getIdentifier("image_$num","drawable",packageName))
    }
    fun Set_ChoiceNum(num : Int){
        val resId = applicationContext.resources.getIdentifier("choice_num$num","array",applicationContext.packageName)
        choice_num=resources.getIntArray(resId)
        mBinding!!.select1.text= choice_num!![0].toString()
        mBinding!!.select2.text= choice_num!![1].toString()
        mBinding!!.select3.text= choice_num!![2].toString()
        mBinding!!.select4.text= choice_num!![3].toString()
    }
    fun Iscorrect(i : Int, num : Int){
        if(answer!![i]!=num){
            System.out.println("맞음")
        }
    }
}