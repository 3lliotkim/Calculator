package com.example.SimpleCalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

import com.example.SimpleCalc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val operationBank = listOf(
        Text(R.string.zero),
        Text(R.string.one),
        Text(R.string.two),
        Text(R.string.three),
        Text(R.string.four),
        Text(R.string.five),
        Text(R.string.six),
        Text(R.string.seven),
        Text(R.string.eight),
        Text(R.string.nine),
        Text(R.string.addition),
        Text(R.string.subtraction),
        Text(R.string.multiplication),
        Text(R.string.division),
        Text(R.string.sqrt),
        Text(R.string.period))

    private var currentIndex = -1
    private var operation = -1
    private var flag = true
    private var leftNum = ""
    private var rightNum = ""
    private var left = 0.0
    private var right = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

        binding.zero.setOnClickListener { view: View ->
            currentIndex = 0
            updateOperation("0")
        }
        binding.one.setOnClickListener { view: View ->
            currentIndex = 1
            updateOperation("1")
        }
        binding.two.setOnClickListener { view: View ->
            currentIndex = 2
            updateOperation("2")
        }
        binding.three.setOnClickListener { view: View ->
            currentIndex = 3
            updateOperation("3")
        }
        binding.four.setOnClickListener { view: View ->
            currentIndex = 4
            updateOperation("4")
        }
        binding.five.setOnClickListener { view: View ->
            currentIndex = 5
            updateOperation("5")
        }
        binding.six.setOnClickListener { view: View ->
            currentIndex = 6
            updateOperation("6")
        }
        binding.seven.setOnClickListener { view: View ->
            currentIndex = 7
            updateOperation("7")
        }
        binding.eight.setOnClickListener { view: View ->
            currentIndex = 8
            updateOperation("8")
        }
        binding.nine.setOnClickListener { view: View ->
            currentIndex = 9
            updateOperation("9")
        }
        binding.period.setOnClickListener { view: View ->
            currentIndex = 10
            updateOperation(".")
        }
        binding.addition.setOnClickListener { view: View ->
            currentIndex = 11
            updateOperation("+")
        }
        binding.subtraction.setOnClickListener { view: View ->
            currentIndex = 12
            updateOperation("-")
        }
        binding.multiplication.setOnClickListener { view: View ->
            currentIndex = 13
            updateOperation("*")
        }
        binding.division.setOnClickListener { view: View ->
            currentIndex = 14
            updateOperation("/")
        }
        binding.sqrt.setOnClickListener { view: View ->
            operation = 15
            calculate(view)
        }

        binding.calculate.setOnClickListener { view: View ->
            calculate(view)
        }
    }

    private fun updateOperation(inputy: String) {
        var stageEditText = binding.firstNum.text.toString()
        val operationTextResId = operationBank[currentIndex].textResId
        if (flag && currentIndex < 11) {
            stageEditText += inputy
            binding.firstNum.setText(stageEditText)
        } else if (currentIndex > 10) {
            leftNum = stageEditText
            if (leftNum.contains(".")) {
                left = leftNum.toDouble()
            } else {
                left = leftNum.toInt() * 1.0
            }
            flag = false
            operation = currentIndex
            stageEditText += inputy
            binding.firstNum.setText(stageEditText)
        } else {
            stageEditText += inputy
            rightNum += inputy
            binding.firstNum.setText(stageEditText)
        }
    }

    private fun calculate(view: View) {
        if (rightNum.isNotEmpty()) {
            if (rightNum.contains(".")) {
                right = rightNum.toDouble()
            } else {
                right = rightNum.toInt() * 1.0
            }
        }
        var answer = 0.0
        var first = left
        var second = right

        if (operation == 11) {
            answer = first + second
        } else if (operation == 12) {
            answer = first - second
        } else if (operation == 13) {
            answer = first * second
        } else if (operation == 14) {
            if (second == 0.0) {
                Snackbar.make(view, R.string.zero_divide, Snackbar.LENGTH_SHORT).show();
            } else {
                answer = first / second
            }
        } else if (operation == 15) {
            var stageFirst = binding.firstNum.text.toString()
            if (stageFirst.contains(".")) {
                first = stageFirst.toDouble()
            } else {
                first = stageFirst.toInt() * 1.0
            }
            answer = sqrt(first)
        } else {
            Snackbar.make(view, R.string.missing_operation, Snackbar.LENGTH_SHORT).show();
        }
        currentIndex = -1
        operation = -1
        flag = true
        leftNum = answer.toString()
        rightNum = ""
        left = answer
        right = 0.0
        binding.firstNum.setText(answer.toString())

    }
}