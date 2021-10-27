sealed class Expr

data class Const(val num: Int): Expr()
data class Neg(val operand: Expr): Expr()
data class Plus(val op1: Expr, val op2: Expr): Expr()
data class Mul(val op1: Expr, val op2: Expr): Expr()

fun Expr.eval(): Int = when (this) {
  is Const -> num
  is Neg -> -operand.eval()
  is Plus -> op1.eval() + op2.eval()
  is Mul -> op1.eval() * op2.eval()
}

fun main() {
  // (1 + 2) * 3
  val expr = Mul(Plus(Const(1), Const(2)), Const(3))
  
  // Mul(op1=Plus(op1=Const(num=1), op2=Const(num=2)), op2=Const(num=3))
  println(expr)
  println(expr.eval()) // 9
  
  // 2 * 3
  val expr2 = expr.copy(op1 = Const(2))
  
  // Mul(op1=Const(num=2), op2=Const(num=3))
  println(expr2)
  println(expr2.eval()) // 6
}
