open class GrandParent
open class Parent :GrandParent()
interface IParent
class Child : Parent(), IParent

fun main() {
  println(Child::class.supertypes) // [Parent, IParent]
}
