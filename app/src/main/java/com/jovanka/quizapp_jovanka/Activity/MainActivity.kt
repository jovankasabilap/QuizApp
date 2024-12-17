package com.jovanka.quizapp_jovanka.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.jovanka.quizapp_jovanka.Domain.QuestionModel
import com.jovanka.quizapp_jovanka.R
import com.jovanka.quizapp_jovanka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Membuat aplikasi berjalan dalam mode layar penuh
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        // Mengatur aksi untuk tombol Single Player
        binding.singleBtn.setOnClickListener {
            // Contoh: Beralih ke aktivitas lain untuk permainan single-player
            val intent = Intent(this@MainActivity, QuestionActivity::class.java)
            intent.putParcelableArrayListExtra("list", ArrayList(questionList()))
            startActivity(intent)
        }

        // Mengatur navigasi pada Bottom Navigation Menu
        binding.bottonMenu.setItemSelected(R.id.home) // Set menu awal yang dipilih
        binding.bottonMenu.setOnItemSelectedListener {
            when (it) {
                R.id.Board -> {
                    // Pindah ke aktivitas LeaderActivity saat menu "Board" dipilih
                    startActivity(Intent(this@MainActivity, LeaderActivity::class.java))
                }

                R.id.home -> {
                    // Tetap di MainActivity saat menu "Home" dipilih
                }
                // Tambahkan aksi lainnya jika diperlukan
            }
        }
    }

    // Fungsi untuk membuat daftar pertanyaan
    private fun questionList(): MutableList<QuestionModel> {
        val question: MutableList<QuestionModel> = mutableListOf()
        question.add(
            QuestionModel(
                id = 1,
                question = "Which planet is known as the Red Planet?",
                answer_1 = "Venus",
                answer_2 = "Mars",
                answer_3 = "Jupiter",
                answer_4 = "Saturn",
                correctAnswer = "2",
                score = 5,
                picPath = "q_1",
                clickAnswer = null
            )
        )
        question.add(
            QuestionModel(
                id = 2,
                question = "What is the capital city of Japan?",
                answer_1 = "Seoul",
                answer_2 = "Beijing",
                answer_3 = "Tokyo",
                answer_4 = "Bangkok",
                correctAnswer = "3",
                score = 5,
                picPath = "q_2",
                clickAnswer = null
            )
        )
        question.add(
            QuestionModel(
                id = 3,
                question = "What is the largest ocean on Earth?",
                answer_1 = "Atlantic Ocean",
                answer_2 = "Indian Ocean",
                answer_3 = "Arctic Ocean",
                answer_4 = "Pacific Ocean",
                correctAnswer = "4",
                score = 5,
                picPath = "q_3",
                clickAnswer = null
            )
        )
        question.add(
            QuestionModel(
                id = 4,
                question = "Who wrote the play 'Romeo and Juliet'?",
                answer_1 = "William Shakespeare",
                answer_2 = "Jane Austen",
                answer_3 = "Charles Dickens",
                answer_4 = "Mark Twain",
                correctAnswer = "1",
                score = 5,
                picPath = "q_4",
                clickAnswer = null
            )
        )
        question.add(
            QuestionModel(
                id = 5,
                question = "What is the chemical symbol for water?",
                answer_1 = "H2O",
                answer_2 = "O2",
                answer_3 = "CO2",
                answer_4 = "NaCl",
                correctAnswer = "1",
                score = 5,
                picPath = "q_5",
                clickAnswer = null
            )
        )
        question.add(
            QuestionModel(
                id = 6,
                question = "Which animal is known as the 'King of the Jungle'?",
                answer_1 = "Tiger",
                answer_2 = "Elephant",
                answer_3 = "Lion",
                answer_4 = "Cheetah",
                correctAnswer = "3",
                score = 5,
                picPath = "q_6",
                clickAnswer = null
            )
        )
        question.add(
            QuestionModel(
                id = 7,
                question = "Which gas do plants primarily use for photosynthesis?",
                answer_1 = "Nitrogen",
                answer_2 = "Oxygen",
                answer_3 = "Carbon Dioxide",
                answer_4 = "Hydrogen",
                correctAnswer = "3",
                score = 5,
                picPath = "q_7",
                clickAnswer = null
            )
        )
        question.add(
            QuestionModel(
                id = 8,
                question = "Who painted the 'Mona Lisa'?",
                answer_1 = "Leonardo da Vinci",
                answer_2 = "Pablo Picasso",
                answer_3 = "Vincent van Gogh",
                answer_4 = "Michelangelo",
                correctAnswer = "1",
                score = 5,
                picPath = "q_8",
                clickAnswer = null
            )
        )
        question.add(
            QuestionModel(
                id = 9,
                question = "What is the square root of 64?",
                answer_1 = "6",
                answer_2 = "7",
                answer_3 = "8",
                answer_4 = "9",
                correctAnswer = "3",
                score = 5,
                picPath = "q_9",
                clickAnswer = null
            )
        )
        question.add(
            QuestionModel(
                id = 10,
                question = "What is the smallest unit of life?",
                answer_1 = "Cell",
                answer_2 = "Tissue",
                answer_3 = "Organ",
                answer_4 = "Molecule",
                correctAnswer = "1",
                score = 5,
                picPath = "q_10",
                clickAnswer = null
            )
        )
        return question
    }
}