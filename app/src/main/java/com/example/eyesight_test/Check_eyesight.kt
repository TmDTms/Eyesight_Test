package com.example.eyesight_test

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.eyesight_test.databinding.ActivityCheckEyesightBinding
import java.util.*


class Check_eyesight : AppCompatActivity() {
    private var random = Random()
    private var check_answer = 0        //선택한 객관식 번호
    private var sight_num = rand(1, 8)           //이미지 번호
    private var count = 0;
    private var resultEyesight = 1.0    // 정상, 적녹색맹, 전색맹정답 체크 개수
    private var choice_num: IntArray? = null        //객관식 보기 리스트
    private var mBinding: ActivityCheckEyesightBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCheckEyesightBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        Set_problem_image(sight_num)    //이미지 설정
        Set_ChoiceNum(sight_num)    //객관식 보기 설정
        mBinding!!.sightCheckGroup.setOnCheckedChangeListener { group, i ->
            when(i){
                R.id.choice1 ->
                {
                    check_answer =  1
                }
                R.id.choice2 ->{
                    check_answer= 2
                }
                R.id.choice3 ->{
                    check_answer= 3
                }
                R.id.choice4 ->{
                    check_answer= 4
                }
            }

        }
        mBinding!!.submitBtn.setOnClickListener(){
            if (count < 6){   //이미지번호가 8보다 작을때 까지
                if (check_answer != 0){   //0이면 선택한 객관식 번호가 없다는 것을 의미
                    Iscorrect(sight_num,choice_num!![check_answer-1].toInt(), count)   //선택한 답이 어떤 답인지 판단
                    sight_num = rand(1, 8)
                    count++     //이미지번호 증가
                    Set_problem_image(sight_num)    //이미지 변경
                    Set_ChoiceNum(sight_num)        //보기 변경
                }
                else{
                    val toast = Toast.makeText(this, "답을 골라주세요.", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER,0,0)
                    toast.show()
                }
            }
            else{
                Iscorrect(sight_num,choice_num!![check_answer-1].toInt(), count)   //선택한 답이 어떤 답인지 판단
                val intent = Intent(this, Result_eyesight::class.java)
                intent.putExtra("eyesight",resultEyesight)   //result라는 키로 resultcheck를 intent로 전달
                startActivity(intent)
                finish()   //해당 액티비티 종료
            }
        }

    }
    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }

    fun Set_problem_image(i : Int){
        mBinding!!.calcQuestionImage.setBackgroundResource(resources.getIdentifier("sight_$i","drawable",packageName))
        //drawable에서 image_$i에 해당하는 이미지로 questionImage 변경
        if(intent.hasExtra("user_height")){
            val user_height = intent.getIntExtra("user_height", 65)
        }
    }
    fun Set_ChoiceNum(i : Int){
        val resId = applicationContext.resources.getIdentifier("choice_int$i","array",applicationContext.packageName)
        //array.xml에서 choice_num$i에 해당하는 id값 가져옴
        choice_num=resources.getIntArray(resId) //해당 id값에 대한 intarray가져옴
        mBinding!!.choice1.text= choice_num!![0].toString() //1번보기 변경
        mBinding!!.choice2.text= choice_num!![1].toString() //2번보기 변경
        mBinding!!.choice3.text= choice_num!![2].toString() //3번보기 변경
        mBinding!!.choice4.text= choice_num!![3].toString() //4번보기 변경
    }

    fun Iscorrect(i : Int, num : Int, count : Int){//문제 번호, 선택한 답, 지금까지 문제 개수
        val answer = resources.getIntArray(applicationContext.resources.getIdentifier("correct_int$i","array",applicationContext.packageName))
        //정답 리스트  array.xml에서 correct_$i에 해당하는 intarray answer에 저장

        val error_num = when(count){
            0 -> 0.5
            1 -> 0.2
            else -> 0.1
        }

        if (answer!![0] == num) {   //선택한 답이 정상인 답
            System.out.println("맞음")
            resultEyesight += error_num
        } else {       //나머지는 전색맹
            resultEyesight -= error_num
        }
        mBinding!!.textView5.text = String.format("%.1f", resultEyesight)
    }
    fun changesize(width : Int, height : Int){  //이미지 사이즈 변경
        val img: ImageView = mBinding!!.calcQuestionImage   //imageView id 가져옴
        val params: ConstraintLayout.LayoutParams = img.getLayoutParams() as ConstraintLayout.LayoutParams  //파라미터가져와서
        params.width = width        //width설정
        params.height = height      //height설정
        img.setLayoutParams(params) //설정한 params값으로 변경
    }
    fun changepx(dp : Float): Float {  // 동적으로 바꿀때 px값으로 들어가는데 xml에서 dp로 하는거처럼 넣고 싶으면 변환해야 한대서 인터넷에서 코드 가져옴
        //테스트는 안해봄
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi as Float / DisplayMetrics.DENSITY_DEFAULT)
    }
}