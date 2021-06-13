package com.rashmiranjan.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class QuizQuestionsActivity : AppCompatActivity() {
    lateinit var tv_question: TextView
    lateinit var iv_image: ImageView
    lateinit var llProgressDetails: LinearLayout
    lateinit var progressBar: ProgressBar
    lateinit var tv_option_one: TextView
    lateinit var tvProgress: TextView
    lateinit var tv_option_two: TextView
    lateinit var tv_option_three: TextView
    lateinit var tv_option_four: TextView
    lateinit var btnSubmit: Button

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Questions>? = null
    private var mSelectedOption: Int = 0
    private var mCorrectAnswer: Int = 0

    private var mUserName: String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_qustions)

        initView()

        mUserName = intent.getStringExtra(Constants.USER_NAME)


        mQuestionsList = Constants.getQuestions()
        setQuestions()

        tv_option_one.setOnClickListener {
            selectedOptionView(tv_option_one, 1)
        }
        tv_option_two.setOnClickListener {
            selectedOptionView(tv_option_two, 2)
        }
        tv_option_three.setOnClickListener {
            selectedOptionView(tv_option_three, 3)
        }
        tv_option_four.setOnClickListener {
            selectedOptionView(tv_option_four, 4)

        }
        btnSubmit.setOnClickListener {

            if (mSelectedOption == 0) {
                mCurrentPosition++

                when {
                    mCurrentPosition <= mQuestionsList!!.size -> {
                        setQuestions()
                    }
                    else -> {
                        val intent = Intent(this,ResultActivity::class.java)
                        intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswer)
                        intent.putExtra(Constants.USER_NAME,mUserName)
                        intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList!!.size)
                        startActivity(intent)
                        finish()
                    }
                }

            } else {
                val question = mQuestionsList?.get(mCurrentPosition - 1)

                if (question!!.correctAnswer != mSelectedOption) {
                    answerView(mSelectedOption, R.drawable.wrong_option_border_bg)
                }else{
                    mCorrectAnswer++
                }
                answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                if (mCurrentPosition == mQuestionsList!!.size) {
                    btnSubmit.text = "FINISH"

                } else {
                    btnSubmit.text = "GO TO THE NEXT QUESTIONS"
                }
                mSelectedOption = 0
            }
        }


    }

    private fun initView() {
        tv_question = findViewById(R.id.tv_question)
        iv_image = findViewById(R.id.iv_image)
        llProgressDetails = findViewById(R.id.llProgressDetails)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        tv_option_one = findViewById(R.id.tv_option_one)
        tv_option_two = findViewById(R.id.tv_option_two)
        tv_option_three = findViewById(R.id.tv_option_three)
        tv_option_four = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btnSubmit)
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
            }

            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
            }

        }
    }

    private fun setQuestions() {

        val question = mQuestionsList?.get(mCurrentPosition - 1)

        defaultOptionView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            btnSubmit.text = "FINISH"
        } else {
            btnSubmit.text = "SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        tvProgress.text = "$mCurrentPosition" + "/" + progressBar.max

        if (question != null) {
            iv_image.setImageResource(question.image)
            tv_question.text = question.question
            tv_option_one.text = question.optionOne
            tv_option_two.text = question.optionTwo
            tv_option_three.text = question.optionThree
            tv_option_four.text = question.optionFour
        }
    }


    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (i in options) {

            i.setTextColor(Color.parseColor("#7A8089"))
            i.typeface = Typeface.DEFAULT
            i.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)

        }
    }


    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionView()
        mSelectedOption = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)


    }
}