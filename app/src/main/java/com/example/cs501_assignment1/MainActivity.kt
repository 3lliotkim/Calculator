package com.example.cs501_assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

import com.example.cs501_assignment1.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_mideast, false))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(view,true)
        }
        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(view,false)
        }

        binding.nextButton.setOnClickListener { view: View ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(view: View, userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        val messageResIdy = if (userAnswer == correctAnswer) {
            R.string.correct_snack
        } else {
            R.string.incorrect_snack
        }
        //Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        Snackbar.make(view, messageResIdy, Snackbar.LENGTH_SHORT).show();
    }
}