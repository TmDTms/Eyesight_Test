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
    private var resultcheck= arrayListOf<Int>(0,0,0)    // 정상, 적녹색맹, 전색맹
    private var choice_num: IntArray? = null        //객관식 리스트
    private var mBinding: ActivityCheckColorblindnessBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCheckColorblindnessBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
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
            if (image_num<8){
                if (check_answer!=0){
                    answer_list.add(choice_num!![check_answer-1].toInt())
                    Iscorrect(image_num,choice_num!![check_answer-1].toInt())
                    image_num++
                    Set_problem_image(image_num)
                    Set_ChoiceNum(image_num)
                }
                else{
                    System.out.println("답을 골라주세요.")
                }
            }
            else{
                answer_list.add(choice_num!![check_answer-1].toInt())
                Iscorrect(image_num,choice_num!![check_answer-1].toInt())
                val intent = Intent(this, Result_colorblindness::class.java)
                intent.putExtra("result",resultcheck)
                startActivity(intent)
                this.finish()
            }
        }

    }
    fun Set_problem_image(i : Int){
        mBinding!!.questionImage.setBackgroundResource(resources.getIdentifier("image_$i","drawable",packageName))
    }
    fun Set_ChoiceNum(i : Int){
        val resId = applicationContext.resources.getIdentifier("choice_num$i","array",applicationContext.packageName)
        choice_num=resources.getIntArray(resId)
        mBinding!!.select1.text= choice_num!![0].toString()
        mBinding!!.select2.text= choice_num!![1].toString()
        mBinding!!.select3.text= choice_num!![2].toString()
        mBinding!!.select4.text= choice_num!![3].toString()
    }
    fun Iscorrect(i : Int, num : Int){
        answer = resources.getIntArray(applicationContext.resources.getIdentifier("correct_$i","array",applicationContext.packageName))
        if(answer!![0]==num){
            System.out.println("맞음")
            resultcheck[0]+=1
        }
        else if (answer!![1]==num){
            resultcheck[1]+=1
        }
        else{
            resultcheck[2]+=1
        }
    }
}