package com.example.eyesight_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eyesight_test.databinding.ActivityCheckColorblindnessBinding

class Check_colorblindness : AppCompatActivity() {
    private var check_answer = 0        //선택한 객관식 번호
    private var image_num = 1           //이미지 번호
    private var resultcheck= arrayListOf<Int>(0,0,0)    // 정상, 적녹색맹, 전색맹정답 체크 개수
    private var choice_num: IntArray? = null        //객관식 보기 리스트
    private var mBinding: ActivityCheckColorblindnessBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCheckColorblindnessBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        Set_problem_image(1)    //이미지 설정
        Set_ChoiceNum(image_num)    //객관식 보기 설정
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
            if (image_num<8){   //이미지번호가 8보다 작을때 까지
                if (check_answer!=0){   //0이면 선택한 객관식 번호가 없다는 것을 의미
                    Iscorrect(image_num,choice_num!![check_answer-1].toInt())   //선택한 답이 어떤 답인지 판단
                    image_num++     //이미지번호 증가
                    Set_problem_image(image_num)    //이미지 변경
                    Set_ChoiceNum(image_num)        //보기 변경
                }
                else{
                    System.out.println("답을 골라주세요.") //toast같은 걸로 변경예정
                }
            }
            else{
                Iscorrect(image_num,choice_num!![check_answer-1].toInt())   //선택한 답이 어떤 답인지 판단
                val intent = Intent(this, Result_colorblindness::class.java)
                intent.putExtra("result",resultcheck)   //result라는 키로 resultcheck를 intent로 전달
                startActivity(intent)
                this.finish()   //해당 액티비티 종료
            }
        }

    }
    fun Set_problem_image(i : Int){
        mBinding!!.questionImage.setBackgroundResource(resources.getIdentifier("image_$i","drawable",packageName))
        //drawable에서 image_$i에 해당하는 이미지로 questionImage 변경
    }
    fun Set_ChoiceNum(i : Int){
        val resId = applicationContext.resources.getIdentifier("choice_num$i","array",applicationContext.packageName)
        //array.xml에서 choice_num$i에 해당하는 id값 가져옴
        choice_num=resources.getIntArray(resId) //해당 id값에 대한 intarray가져옴
        mBinding!!.select1.text= choice_num!![0].toString() //1번보기 변경
        mBinding!!.select2.text= choice_num!![1].toString() //2번보기 변경
        mBinding!!.select3.text= choice_num!![2].toString() //3번보기 변경
        mBinding!!.select4.text= choice_num!![3].toString() //4번보기 변경
    }
    fun Iscorrect(i : Int, num : Int){//문제 번호, 선택한 답
        val answer = resources.getIntArray(applicationContext.resources.getIdentifier("correct_$i","array",applicationContext.packageName))
        //정답 리스트  array.xml에서 correct_$i에 해당하는 intarray answer에 저장
        if(answer!![0]==num){   //선택한 답이 정상인 답
            System.out.println("맞음")
            resultcheck[0]+=1
        }
        else if (answer!![1]==num){ //선택한 답이 적녹색맹 답
            resultcheck[1]+=1
        }
        else{       //나머지는 전색맹
            resultcheck[2]+=1
        }
    }
}