package com.example.composecalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecalculator.ui.theme.ComposeCalculatorTheme
import java.math.BigDecimal
import java.math.RoundingMode
import androidx.compose.material.TextField as TextField1

private enum class OpKind {
    ADD, SUBTRACT, MULTIPLY, DIVIDE
}

private fun OpKind.compute(a: BigDecimal, b: BigDecimal) = when (this) {
    OpKind.ADD -> a + b
    OpKind.SUBTRACT -> a - b
    OpKind.MULTIPLY -> a * b
    OpKind.DIVIDE -> a.divide(b, 10, RoundingMode.HALF_EVEN)
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainActivityLayout("0")
                }
            }
        }
    }
}

@Composable
fun MainActivityLayout(last: String) {
    var text by remember { mutableStateOf("") }
    var lastResult: BigDecimal by remember { mutableStateOf(BigDecimal.ZERO) }
    var lastOp: OpKind? by remember { mutableStateOf(null) }
    var waitingNextOperand: Boolean by remember { mutableStateOf(false) }

    fun appendText(v:String) {
        if (waitingNextOperand) {
            text=""
            waitingNextOperand = false
        }
        if(v=="0") {
            if(text!="0") text+=v
        } else {
            text += v
        }
    }

    Column(Modifier.fillMaxHeight()) {
        val context = LocalContext.current
        TextField1(
            value = text,
            onValueChange = {},
            label = {},
            textStyle = TextStyle(fontSize = 36.sp)
        )
        Row {
            Column(Modifier.weight(3f)) {
                Row {
                    calcButton("7") { appendText("7") }
                    calcButton("8") { appendText("8") }
                    calcButton("9") { appendText("9") }
                }
                Row {
                    calcButton("4") { appendText("4") }
                    calcButton("5") { appendText("5") }
                    calcButton("6") { appendText("6") }
                }
                Row {
                    calcButton("1") { appendText("1") }
                    calcButton("2") { appendText("2") }
                    calcButton("3") { appendText("3") }
                }
                Row {
                    calcButton("0") { appendText("0") }
                    calcButton(".") { appendText(".") }
                    calcButton("+/-") {
                        text = when {
                            text.startsWith("-") ->
                                text.substring(1, text.length)
                            text != "0" -> "-$text"
                            else -> text
                        }
                    }
                }
                Row {
                    calcButton("<-") {
                        text = text.dropLast(1).let {
                            if (it == "-") "" else it
                        }
                    }
                    calcButton("C") { text = ""; waitingNextOperand=false; lastOp=null; }
                }
            }
            Column(Modifier.weight(1f)) {
            }
            Column(Modifier.weight(1f)) {
                fun calc(nextOp: OpKind?) {
                    if (waitingNextOperand) {
                        lastOp = nextOp
                        return
                    }
                    val currentValue = BigDecimal(text)
                    val newValue = try {
                        lastOp?.compute(lastResult, currentValue) ?: currentValue
                    } catch (e: ArithmeticException) {
                        lastOp = null
                        waitingNextOperand = true
                        Toast.makeText(
                            context,
                            "Invalid operation!",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }

                    if (nextOp != null) lastResult = newValue
                    if (lastOp != null) text = newValue.toPlainString()

                    lastOp = nextOp
                    waitingNextOperand = nextOp != null
                }

                calcButton("+") { calc(OpKind.ADD) }
                calcButton("-") { calc(OpKind.SUBTRACT) }
                calcButton("*") { calc(OpKind.MULTIPLY) }
                calcButton("/") { calc(OpKind.DIVIDE) }
                calcButton("=") { calc(null) }
            }
        }
    }
}

// 버튼을 쉽게 만들기 위한 @Composable 함수
@Composable
fun calcButton(text: String, callback: () -> Unit) =
    Button(callback, Modifier.padding(10.dp)) { Text(text) }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCalculatorTheme {
        MainActivityLayout("987654321")
    }
}