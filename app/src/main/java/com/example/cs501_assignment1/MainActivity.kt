package com.example.cs501_assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.cs501_assignment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val textBank = listOf(Text(R.string.first),
        Text(R.string.hello_world))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

        binding.sayHello.setOnClickListener {
            currentIndex = (currentIndex + 1) % textBank.size
            updateText()
        }

        updateText()
    }
    private fun updateText() {
        val textResId = textBank[currentIndex].textResId
        binding.textView.setText(textResId)
    }
}