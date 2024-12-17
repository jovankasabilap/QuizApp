package com.jovanka.quizapp_jovanka.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jovanka.quizapp_jovanka.R
import com.jovanka.quizapp_jovanka.databinding.ViewholderQuestionBinding

class QuestionAdapter(
    val correctAnswer: String,
    val users: MutableList<String> = mutableListOf(),
    var returnScore: Score
) : RecyclerView.Adapter<QuestionAdapter.Viewholder>() {

    interface Score {
        fun amount(number: Int, clickedAnswer: String)
    }

    private lateinit var binding: ViewholderQuestionBinding

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ViewholderQuestionBinding.inflate(inflater, parent, false)
        return Viewholder(binding.root)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val currentAnswer = differ.currentList[position]
        binding.answerTxt.text = currentAnswer

        val correctPos = when (correctAnswer) {
            "a" -> 0
            "b" -> 1
            "c" -> 2
            "d" -> 3
            else -> -1
        }

        holder.itemView.setOnClickListener {
            val selectedAnswer = when (position) {
                0 -> "a"
                1 -> "b"
                2 -> "c"
                3 -> "d"
                else -> ""
            }
            users.add(4, selectedAnswer)
            notifyDataSetChanged()

            if (correctPos == position) {
                binding.answerTxt.setBackgroundResource(R.drawable.green_bg)
                binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, ContextCompat.getDrawable(binding.root.context, R.drawable.tick), null
                )
                returnScore.amount(5, selectedAnswer)
            } else {
                binding.answerTxt.setBackgroundResource(R.drawable.red_bg)
                binding.answerTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null, null, ContextCompat.getDrawable(binding.root.context, R.drawable.thieves), null
                )
                returnScore.amount(0, selectedAnswer)
            }
        }
    }

    override fun getItemCount() = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)
}
