package com.example.afinal.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.afinal.Domain.QuestionModel
import com.example.afinal.R
import com.example.afinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )

        binding.apply {
            singleBtn.setOnClickListener{
                val intent = Intent(this@MainActivity, QuestionActivity:: class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(questionList()))
                startActivity(intent)
            }

            bottomMenu.setItemSelected(R.id.home)
            bottomMenu.setOnItemSelectedListener {
                if(it == R.id.Board){
                    startActivity(Intent(this@MainActivity,LeaderActivity::class.java))
                }
            }
        }


    }
    private fun questionList(): MutableList<QuestionModel> {
        var question: MutableList<QuestionModel> = mutableListOf()
        question.add(
            QuestionModel(1, "Which planet is the largest planet in the solar system", "Earth", "Mars", "Neptune", "Jupiter","d", 5, "q_1", null),

        )
        question.add(
            QuestionModel(2, "What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome","c", 5, "q_2", null),

            )
        question.add(
            QuestionModel(3, "Who wrote 'Romeo and Juliet'?", "Mark Twain", "William Shakespeare", "Charles Dickens", "J.K. Rowling","b", 5, "q_3", null),

            )
        question.add(
            QuestionModel(4, "What is the largest planet in our solar system?", "Earth", "Mars", "Neptune", "Jupiter","d", 5, "q_4", null),

            )
        question.add(
            QuestionModel(5, "What is the boiling point of water?", "50°C", "100°C", "150°C", "200°C","b", 5, "q_5", null),

            )
        question.add(
            QuestionModel(6, " Who is known as the 'Father of Computers'?", "Albert Einstein", "Isaac Newton", "Charles Babbage", "Nikola Tesla","c", 5, "q_6", null),

            )
        question.add(
            QuestionModel(7, "What is the largest ocean on Earth?", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean","d", 5, "q_7", null),

            )
        question.add(
            QuestionModel(8, "What is the chemical formula for water?", "CO2", "H2O", "NaCl", "O2","b", 5, "q_8", null),

            )
        question.add(
            QuestionModel(9, "Who painted the Mona Lisa?", "Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo","c", 5, "q_9", null),

            )
        question.add(
            QuestionModel(10, "What is the smallest unit of life?", "Atom", "Molecule", "Cell", "Organ","c", 5, "q_10", null),

            )


        return question
    }
}

