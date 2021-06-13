package com.rashmiranjan.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    lateinit var tv_userName: TextView
    lateinit var tv_score: TextView
    lateinit var btn_finish: Button

    private var mUsername: String? = null
//    private var mTotalQuestions:String? = null
//    private var mTotalCorrectAnswer:String? = null


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tv_userName = findViewById(R.id.tv_name)
        tv_score = findViewById(R.id.tv_score)
        btn_finish = findViewById(R.id.btn_finish)

        mUsername = intent.getStringExtra(Constants.USER_NAME)
        val mTotalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val mTotalCorrectAnswer = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tv_userName.text = mUsername
        tv_score.text = "Your Score is $mTotalCorrectAnswer out of $mTotalQuestions"

        btn_finish.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}