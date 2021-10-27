package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.helloworld.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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

    private var lastResult: BigDecimal = BigDecimal.ZERO;
    private var lastOp: OpKind? = null
    private var waitingNextOperand: Boolean = false

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("currentText", binding.txtResult.text.toString())
        outState.putSerializable(::lastResult.name, lastResult)
        outState.putSerializable(::lastOp.name, lastOp)
        outState.putBoolean(::waitingNextOperand.name, waitingNextOperand)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run{
            btn0.setOnClickListener{ appendText("0") }
            btn1.setOnClickListener{ appendText("1") }
            btn2.setOnClickListener{ appendText("2") }
            btn3.setOnClickListener{ appendText("3") }
            btn4.setOnClickListener{ appendText("4") }
            btn5.setOnClickListener{ appendText("5") }
            btn6.setOnClickListener{ appendText("6") }
            btn7.setOnClickListener{ appendText("7") }
            btn8.setOnClickListener{ appendText("8") }
            btn9.setOnClickListener{ appendText("9") }
            btnPoint.setOnClickListener{ appendText(".") }
            btnSign.setOnClickListener{
                val currentText = txtResult.text.toString()
                txtResult.text = when {
                    currentText.startsWith("-") ->
                        currentText.substring(1, currentText.length)
                    currentText != "0" -> "-$currentText"
                    else ->return@setOnClickListener
                }
            }
            btnBackspace.setOnClickListener{
                val currentText = txtResult.text.toString()
                val newText = currentText.substring(0, currentText.length - 1)
                txtResult.text =
                    if (newText.isEmpty() || newText == "-") "0" else newText
            }
            btnClear.setOnClickListener{ clearText() }
            btnPlus.setOnClickListener{ calc(OpKind.ADD) }
            btnMinus.setOnClickListener{ calc(OpKind.SUBTRACT) }
            btnTimes.setOnClickListener{ calc(OpKind.MULTIPLY) }
            btnDivide.setOnClickListener{ calc(OpKind.DIVIDE) }
            btnCalc.setOnClickListener{ calc(null) }
        }

        clearText()

        savedInstanceState?.let {
            binding.txtResult.text = it.getString("currentText")
            lastResult = it.getSerializable(::lastResult.name) as BigDecimal
            lastOp = it.getSerializable(::lastOp.name) as OpKind?
            waitingNextOperand = it.getBoolean(::waitingNextOperand.name)
        }
    }

    private fun clearText() {
        binding.txtResult.text = "0"
    }
    private fun appendText(text: String) {
        if (waitingNextOperand) {
            clearText()
            waitingNextOperand = false
        }
        val currentText = binding.txtResult.text.toString()
        binding.txtResult.text =
            if (currentText == "0") text else currentText + text
    }
    private fun calc(nextOp: OpKind?) {
        if (waitingNextOperand) {
            lastOp = nextOp
            return
        }
        val currentValue = BigDecimal(binding.txtResult.text.toString())
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
        if (lastOp != null) binding.txtResult.text = newValue.toPlainString()

        lastOp = nextOp
        waitingNextOperand = nextOp != null
    }
}