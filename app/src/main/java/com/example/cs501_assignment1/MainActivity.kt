package com.example.SimpleCalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

import com.example.SimpleCalc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val operationBank = listOf(
        Text(R.string.addition),
        Text(R.string.subtraction),
        Text(R.string.multiplication),
        Text(R.string.division),
        Text(R.string.modulo))

    private var currentIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

        binding.addition.setOnClickListener { view: View ->
            currentIndex = 0
            updateOperation()
        }
        binding.subtraction.setOnClickListener { view: View ->
            currentIndex = 1
            updateOperation()
        }
        binding.multiplication.setOnClickListener { view: View ->
            currentIndex = 2
            updateOperation()
        }
        binding.division.setOnClickListener { view: View ->
            currentIndex = 3
            updateOperation()
        }
        binding.modulo.setOnClickListener { view: View ->
            currentIndex = 4
            updateOperation()
        }

        binding.calculate.setOnClickListener { view: View ->
            calculate(view)
        }
    }

    private fun updateOperation() {
        val operationTextResId = operationBank[currentIndex].textResId
        binding.operationTextView.setText(operationTextResId)
    }

    private fun calculate(view: View) {
        var answer = 0.0
        var first = 0.0
        var second = 0.0
        val stageFirst = binding.firstNum.text.toString()
        val stageSecond = binding.secondNum.text.toString()
        if (stageFirst.isNotEmpty()) {
            if (stageFirst.contains(".")) {
                first = stageFirst.toDouble()
            } else {
                first = stageFirst.toInt() * 1.0
            }
        }
        if (stageSecond.isNotEmpty()) {
            if (stageSecond.contains(".")) {
                second = stageSecond.toDouble()
            } else {
                second = stageSecond.toInt() * 1.0
            }
        }
        if (currentIndex == 0) {
            answer = first + second
        } else if (currentIndex == 1) {
            answer = first - second
        } else if (currentIndex == 2) {
            answer = first * second
        } else if (currentIndex == 3) {
            if (second == 0.0) {
                Snackbar.make(view, R.string.zero_divide, Snackbar.LENGTH_SHORT).show();
            } else {
                answer = first / second
            }
        } else if (currentIndex == 4) {
            answer = first % second
        } else {
            Snackbar.make(view, R.string.missing_operation, Snackbar.LENGTH_SHORT).show();
        }
        //var first = stageFirst.toInt() * 1.0
        //var second = stageSecond.toInt() * 1.0
        binding.outputTextView.text = answer.toString()

    }
}