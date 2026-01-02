package com.example.afinal.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.afinal.Adapter.QuestionAdapter
import com.example.afinal.Domain.QuestionModel
import com.example.afinal.R
import com.example.afinal.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding
    private var position: Int = 0
    private var receivedList: MutableList<QuestionModel> = mutableListOf()
    private var allscore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        receivedList = intent.getParcelableArrayListExtra<QuestionModel>("list")?.toMutableList() ?: mutableListOf()

        binding.apply {
            backBtn.setOnClickListener { finish() }
            progressBar.progress = 1

            questionTxt.text = receivedList[position].question
            val drawableResourceId: Int = binding.root.resources.getIdentifier(
                receivedList[position].picPath, "drawable", binding.root.context.packageName
            )

            Glide.with(this@QuestionActivity)
                .load(drawableResourceId)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(60))).into(pic)

            loadAnswers()

            rightArrow.setOnClickListener {
                if (progressBar.progress == 10) {
                    val intent = Intent(this@QuestionActivity, ScoreActivity::class.java)
                    intent.putExtra("Score", allscore)
                    startActivity(intent)
                    finish()
                    return@setOnClickListener
                }
                position++
                progressBar.progress = progressBar.progress + 1
                questionNumber.text = "Question ${progressBar.progress}/10"
                questionTxt.text = receivedList[position].question

                val newDrawableResourceId: Int = binding.root.resources.getIdentifier(
                    receivedList[position].picPath, "drawable", binding.root.context.packageName
                )

                Glide.with(this@QuestionActivity)
                    .load(newDrawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60))).into(pic)

                loadAnswers()
            }

            leftArrow.setOnClickListener {
                if (progressBar.progress == 1) {
                    return@setOnClickListener
                }
                position--
                progressBar.progress = progressBar.progress - 1
                questionNumber.text = "Question ${progressBar.progress}/10"
                questionTxt.text = receivedList[position].question

                val newDrawableResourceId: Int = binding.root.resources.getIdentifier(
                    receivedList[position].picPath, "drawable", binding.root.context.packageName
                )

                Glide.with(this@QuestionActivity)
                    .load(newDrawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60))).into(pic)

                loadAnswers()
            }
        }
    }

    private fun loadAnswers() {
        val answers: MutableList<String> = mutableListOf(
            receivedList[position].answer_1.toString(),
            receivedList[position].answer_2.toString(),
            receivedList[position].answer_3.toString(),
            receivedList[position].answer_4.toString()
        )

        if (receivedList[position].clickedAnswer != null) {
            answers.add(receivedList[position].clickedAnswer.toString())
        }

        val questionAdapter = QuestionAdapter(receivedList[position].correctAnswer.toString(), answers, this)
        questionAdapter.differ.submitList(answers)

        binding.questionlist.apply {
            layoutManager = LinearLayoutManager(this@QuestionActivity)
            adapter = questionAdapter
        }
    }

    fun amount(number: Int, clickedAnswer: String) {
        allscore += number
        receivedList[position].clickedAnswer = clickedAnswer
    }
}
