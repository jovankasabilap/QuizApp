package com.jovanka.quizapp_jovanka.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.jovanka.quizapp_jovanka.Adapter.QuestionAdapter
import com.jovanka.quizapp_jovanka.Domain.QuestionModel
import com.jovanka.quizapp_jovanka.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity(), QuestionAdapter.Score { // Implementasi Interface Score

    private lateinit var binding: ActivityQuestionBinding
    private var position: Int = 0
    private var receivedList: MutableList<QuestionModel> = mutableListOf()
    private var allscore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Full-screen mode (Edge-to-Edge)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        // Get the list passed through Intent
        receivedList = intent.getParcelableArrayListExtra<QuestionModel>("list") ?: mutableListOf()

        binding.apply {
            backBtn.setOnClickListener { finish() }
            progressBar.progress = 1

            // Display the first question and image
            loadQuestionAndImage()

            rightArrow.setOnClickListener {
                if (progressBar.progress == 10) {
                    val intent = Intent(this@QuestionActivity, ScoreActivity::class.java)
                    intent.putExtra("score", allscore)
                    startActivity(intent)
                    finish()
                } else {
                    position++
                    progressBar.progress += 1
                    questionNumber.text = "Question ${progressBar.progress}/10"
                    loadQuestionAndImage()
                }
            }

            leftArrow.setOnClickListener {
                if (progressBar.progress > 1) {
                    position--
                    progressBar.progress -= 1
                    questionNumber.text = "Question ${progressBar.progress}/10"
                    loadQuestionAndImage()
                }
            }
        }
    }

    private fun loadQuestionAndImage() {
        binding.apply {
            questionTxt.text = receivedList[position].question
            val drawableResourceId = resources.getIdentifier(
                receivedList[position].picPath, "drawable", packageName
            )
            Glide.with(this@QuestionActivity)
                .load(drawableResourceId)
                .centerCrop()
                .apply(RequestOptions().transform(RoundedCorners(60)))
                .into(pic)
            loadAnswers()
        }
    }

    private fun loadAnswers() {
        val users: MutableList<String> = mutableListOf()
        users.add(receivedList[position].answer_1.toString())
        users.add(receivedList[position].answer_2.toString())
        users.add(receivedList[position].answer_3.toString())
        users.add(receivedList[position].answer_4.toString())

        // Tambahkan jawaban yang diklik sebelumnya, jika ada
        receivedList[position].clickAnswer?.let { users.add(it) }

        // Pasang adapter untuk RecyclerView
        val questionAdapter = QuestionAdapter(
            receivedList[position].correctAnswer.toString(),
            users,
            this // Menggunakan this sebagai implementasi dari Score
        )
        questionAdapter.differ.submitList(users)

        binding.questionList.apply {
            layoutManager = LinearLayoutManager(this@QuestionActivity)
            adapter = questionAdapter
        }
    }

    // Implementasi dari Interface Score
    override fun amount(number: Int, clickedAnswer: String) {
        updateScoreAndAnswer(number, clickedAnswer)
    }

    private fun updateScoreAndAnswer(number: Int, clickAnswer: String) {
        allscore += number // Menambahkan nilai ke skor total
        receivedList[position].clickAnswer = clickAnswer // Menyimpan jawaban yang diklik
    }
}
