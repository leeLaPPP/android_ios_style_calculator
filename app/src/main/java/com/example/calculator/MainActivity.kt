package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var textView: EditText
    private var firstNumber = 0.0
    private var operation = ""
    private var isNewNumber = true
    private val decimalFormat = DecimalFormat("#,###.##########")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        setupNumberButtons()
        setupOperationButtons()
        setupFunctionButtons()
    }

    private fun setupNumberButtons() {
        val numberButtons = arrayOf(
            R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four,
            R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine
        )

        numberButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                onNumberClick((it as Button).text.toString())
            }
        }

        // 소수점 버튼
        findViewById<Button>(R.id.dot).setOnClickListener {
            onDotClick()
        }
    }

    private fun setupOperationButtons() {
        findViewById<Button>(R.id.plus).setOnClickListener { onOperationClick("+") }
        findViewById<Button>(R.id.minus).setOnClickListener { onOperationClick("−") }
        findViewById<Button>(R.id.multiple).setOnClickListener { onOperationClick("×") }
        findViewById<Button>(R.id.division).setOnClickListener { onOperationClick("÷") }
        findViewById<Button>(R.id.result).setOnClickListener { onEqualsClick() }
    }

    private fun setupFunctionButtons() {
        findViewById<Button>(R.id.reset).setOnClickListener { onResetClick() }
        findViewById<Button>(R.id.plma).setOnClickListener { onPlusMinusClick() }
        findViewById<Button>(R.id.persent).setOnClickListener { onPercentClick() }
    }

    private fun onNumberClick(number: String) {
        if (isNewNumber) {
            textView.setText(number)
            isNewNumber = false
        } else {
            val currentText = textView.text.toString()
            if (currentText.length < 12) {  // 최대 12자리 제한
                textView.append(number)
            }
        }
    }

    private fun onDotClick() {
        val currentText = textView.text.toString()
        if (!currentText.contains(".")) {
            if (isNewNumber) {
                textView.setText("0.")
                isNewNumber = false
            } else {
                textView.append(".")
            }
        }
    }

    private fun onOperationClick(op: String) {
        firstNumber = textView.text.toString().toDouble()
        operation = op
        isNewNumber = true
    }

    private fun onEqualsClick() {
        if (operation.isEmpty()) return

        val secondNumber = textView.text.toString().toDouble()
        val result = when (operation) {
            "+" -> firstNumber + secondNumber
            "−" -> firstNumber - secondNumber
            "×" -> firstNumber * secondNumber
            "÷" -> if (secondNumber != 0.0) firstNumber / secondNumber else "Error"
            else -> return
        }

        displayResult(result)
        operation = ""
        isNewNumber = true
    }

    private fun onResetClick() {
        textView.setText("0")
        firstNumber = 0.0
        operation = ""
        isNewNumber = true
    }

    private fun onPlusMinusClick() {
        val currentText = textView.text.toString()
        if (currentText != "0") {
            if (currentText.startsWith("-")) {
                textView.setText(currentText.substring(1))
            } else {
                textView.setText("-$currentText")
            }
        }
    }

    private fun onPercentClick() {
        val currentNumber = textView.text.toString().toDouble()
        val result = currentNumber / 100
        displayResult(result)
    }

    private fun displayResult(result: Any) {
        textView.setText(
            when (result) {
                is Double -> decimalFormat.format(result)
                else -> result.toString()
            }
        )
    }
} 