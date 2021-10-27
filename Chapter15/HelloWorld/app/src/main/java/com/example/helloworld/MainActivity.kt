package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    enum class OpKind {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    companion object {
        fun OpKind.compute(a: BigDecimal, b: BigDecimal) = when (this) {
            OpKind.ADD -> a + b
            OpKind.SUBTRACT -> a - b
            OpKind.MULTIPLY -> a * b
            OpKind.DIVIDE ->a.divide(b, 10, RoundingMode.HALF_EVEN)
        }
    }

    private val txtResult by lazy<TextView> { findViewById(R.id.txtResult) }
    private var lastResult: BigDecimal = BigDecimal.ZERO;
    private var lastOp: OpKind? = null
    private var waitingNextOperand: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn0).setOnClickListener{ appendText("0") }
        findViewById<Button>(R.id.btn1).setOnClickListener{ appendText("1") }
        findViewById<Button>(R.id.btn2).setOnClickListener{ appendText("2") }
        findViewById<Button>(R.id.btn3).setOnClickListener{ appendText("3") }
        findViewById<Button>(R.id.btn4).setOnClickListener{ appendText("4") }
        findViewById<Button>(R.id.btn5).setOnClickListener{ appendText("5") }
        findViewById<Button>(R.id.btn6).setOnClickListener{ appendText("6") }
        findViewById<Button>(R.id.btn7).setOnClickListener{ appendText("7") }
        findViewById<Button>(R.id.btn8).setOnClickListener{ appendText("8") }
        findViewById<Button>(R.id.btn9).setOnClickListener{ appendText("9") }
        findViewById<Button>(R.id.btnPoint).setOnClickListener{ appendText(".") }
        findViewById<Button>(R.id.btnSign).setOnClickListener{
            val currentText = txtResult.text.toString()
            txtResult.text = when {
                currentText.startsWith("-") ->
                    currentText.substring(1, currentText.length)
                currentText != "0" -> "-$currentText"
                else ->return@setOnClickListener
            }
        }
        findViewById<Button>(R.id.btnBackspace).setOnClickListener{
            val currentText = txtResult.text.toString()
            val newText = currentText.substring(0, currentText.length - 1)
            txtResult.text =
                if (newText.isEmpty() || newText == "-") "0" else newText
        }
        findViewById<Button>(R.id.btnClear).setOnClickListener{ clearText() }
        findViewById<Button>(R.id.btnPlus).setOnClickListener{ calc(OpKind.ADD) }
        findViewById<Button>(R.id.btnMinus).setOnClickListener{ calc(OpKind.SUBTRACT) }
        findViewById<Button>(R.id.btnTimes).setOnClickListener{ calc(OpKind.MULTIPLY) }
        findViewById<Button>(R.id.btnDivide).setOnClickListener{ calc(OpKind.DIVIDE) }
        findViewById<Button>(R.id.btnCalc).setOnClickListener{ calc(null) }

        clearText()
    }

    private fun clearText() {
        txtResult.text = "0"
    }
    private fun appendText(text: String) {
        if (waitingNextOperand) {
            clearText()
            waitingNextOperand = false
        }
        val currentText = txtResult.text.toString()
        txtResult.text =
            if (currentText == "0") text else currentText + text
    }
    private fun calc(nextOp: OpKind?) {
        if (waitingNextOperand) {
            lastOp = nextOp
            return
        }
        val currentValue = BigDecimal(txtResult.text.toString())
        val newValue = try {
            lastOp?.compute(lastResult, currentValue) ?: currentValue
        } catch (e: ArithmeticException) {
            lastOp = null
            waitingNextOperand = true
            Toast.makeText(
                applicationContext,
                "Invalid operation!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (nextOp != null) lastResult = newValue
        if (lastOp != null) txtResult.text = newValue.toPlainString()

        lastOp = nextOp
        waitingNextOperand = nextOp != null
    }
}