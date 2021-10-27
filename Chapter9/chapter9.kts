val map = HashMap<Int, String>()
val list = arrayListOf<String>()

// map의 타입을 명시했기 때문에, HashMap 클래스의 타입 인자를 추론할 수 있음
val map2: Map<Int, String> = HashMap()

// arrayListOf()에 전달된 인자의 타입(모두 String)으로부터 타입 인자를 추론할 수 있음
val list2 = arrayListOf("abc", "def")

class TreeNode<T>(val data: T) {
  private val _children = arrayListOf<TreeNode<T>>()
  var parent: TreeNode<T>? = null
  
  private set
  
  val children: List<TreeNode<T>>get() = _children
  
  fun addChild(data: T) = TreeNode(data).also {
    _children += it
    it.parent = this
  }
  
  override fun toString() =
    _children.joinToString(prefix = "$data {", postfix = "}")
}

fun main1() {
  val root = TreeNode("Hello").apply {
    addChild("World")
    addChild("!!!")
  }
  
  println(root) // Hello {World {}, !!! {}}
}

main1()

open class DataHolder<T>(val data: T)

// 실제 타입을 상위 타입의 타입 인자로 넘김
class StringDataHolder(data: String) : DataHolder<String>(data)

// 타입 인자를 상위 타입의 타입 인자로 넘김
class TreeNode2<T>(data: T) : DataHolder<T>(data) { }

//error: one type argument expected for class DataHolder<T>
//class StringDataHolder2(data: String) : DataHolder(data)

// Ok: DataHolder<String>를 컴파일러가 추론함
fun stringDataHolder(data: String) = DataHolder(data)

fun <T> TreeNode<T>.addChildren(vararg data: T) {
  data.forEach { addChild(it) }
}

fun <T> TreeNode<T>.walkDepthFirst(action: (T) -> Unit) {
  children.forEach { it.walkDepthFirst(action) }
  action(data)
}

val <T> TreeNode<T>.depth: Int
  get() = (children.asSequence().map { it.depth }.maxOrNull() ?: 0) + 1

fun main2() {
  val root = TreeNode("Hello").apply {
    addChildren("World", "!!!")
  }
  
  println(root.depth) // 2
}

main2()

// error: type parameter of a property must be used in its receiver type
//var <T> root: TreeNode<T>? = null

// error: type parameters are not allowed for objects
//object EmptyTree<T> 

// error: expression 'depth' of type 'Int' cannot be invoked as a function.
//val minDepth = TreeNode("").depth<String>

// error: type parameter of a property must be used in its receiver type
// val <T> TreeNode<String>.upperCaseDataget() = data.toUpperCase()

fun <T : Number>TreeNode<T>.average(): Double {
  var count = 0
  var sum = 0.0
  walkDepthFirst {  // 깊이 우선으로 노드를 방문하면서 함수 수행
    count++
    sum += it.toDouble()
  }
  return sum/count
}

val intTree = TreeNode(1).apply {
  addChild(2).addChild(3)
  addChild(4).addChild(5)
}
println(intTree.average()) // 3.0

val doubleTree = TreeNode(1.0).apply {
  addChild(2.0)
  addChild(3.0)
}
println(doubleTree.average()) // 2.0

val stringTree = TreeNode("Hello").apply {
  addChildren("World", "!!!")
}
//error: unresolved reference. None of the following candidates is applicable because of receiver type mismatch
//println(stringTree.average()) 

// 제네릭이 아닌 함수로 대신할 수 있다.
// fun TreeNode<Int>.sum(): Int {...}
fun <T : Int> TreeNode<T>.sum(): Int { // Warning
  var sum = 0
  walkDepthFirst{ sum += it }
  return sum
}

fun <T : Comparable<T>> TreeNode<T>.maxNode(): TreeNode<T> {
  val maxChild = children.maxByOrNull { it.data } ?: return this
  
  return if (data >= maxChild.data) this else maxChild
}

fun main3() {
  // Double은 Comparable<Double>의 하위 타입임
  val doubleTree = TreeNode(1.0).apply {
    addChild(2.0)
    addChild(3.0)
  }
  println(doubleTree.maxNode().data) // 3.0
  
  // String은 Comparable<String>의 하위 타입임
  val stringTree = TreeNode("abc").apply {
    addChildren("xyz", "def")
  }
  println(stringTree.maxNode().data) // xyz
}

main3()


fun <T, U : T> TreeNode<U>.toList(list: MutableList<T>) {
  walkDepthFirst{ list += it }
}

fun main4() {
  val list = ArrayList<Number>()
  
  TreeNode(1).apply {
    addChild(2)
    addChild(3)
  }.toList(list)
  
  TreeNode(1.0).apply {
    addChild(2.0)
    addChild(3.0)
  }.toList(list)
}

main4()

fun <T: Any> notNullTreeOf(data: T) = TreeNode(data)

interface Named {
  val name: String
}

interface Identified {
  val id: Int
}

class Registry<T> where T : Named, T : Identified {
  val items = ArrayList<T>()
}

/*
fun <T>TreeNode<Any>.isInstanceOf(): Boolean =
  // error: cannot check for instance of erased type: T
  data is T && children.all{ it.isInstanceOf<T>() } 
*/

val list3 = listOf(1, 2, 3) // List<Int>
list3 is List<Number> // OK
//list3 is List<String> // error: cannot check for instance of erased type: List<String>

val collection: Collection<Int> = setOf(1, 2, 3)

if (collection is List<Int>) {
  println("list")
}

val n = (listOf(1, 2, 3) as List<Number>)[0] // OK
//val s = (listOf(1, 2, 3) as List<String>)[0] // java.lang.ClassCastException

fun <T>TreeNode<T>.cancellableWalkDepthFirst(
  onEach: (T) -> Boolean
): Boolean {
  // 자바 LinkedList는 Deque를 구현하고 있어서 스택으로도 사용 가능함
  val nodes = java.util.LinkedList<TreeNode<T>>() 
  
  nodes.push(this)
  
  while (nodes.isNotEmpty()) {
    val node = nodes.pop()
    if (!onEach(node.data)) return false
      node.children.forEach { nodes.push(it) }
  }
  
  return true
}

inline fun <reified T> TreeNode<*>.isInstanceOf() =
  cancellableWalkDepthFirst{ it is T }


fun main5() {
  val tree = TreeNode<Any>("abc").addChild("def").addChild(123)
  println(tree.isInstanceOf<String>())
}

main5()

//inline fun <reified T> factory() = T() // error: type parameter T cannot be called as function

// error: cannot use 'T' as reified type parameter. Use a class instead
//fun <T, U> TreeNode<*>.isInstanceOfBoth() =
//  isInstanceOf<T>() && sInstanceOf<U>()
val objects: List<Any> = listOf("a", "b", "c") // 맞음

//val node: TreeNode<Any> = TreeNode<String>("Hello") // error: type mismatch

// 아래 코드는 공변성을 설명하는 예제이지만, 코틀린에서는 anyNode에 stringNode를 대입할 수 없다.
/*
val stringNode = TreeNode<String>("Hello")
val anyNode: TreeNode<Any> = stringNode
anyNode.addChild(123)
val s = stringNode.children.first() // ???
*/

val stringProducer: () -> String = { "Hello" }
val anyProducer: () -> Any = stringProducer
println(anyProducer()) // Hello

interface NonGrowingList<T> {
  val size: Int
  fun get(index: Int): Int
  fun remove(index: Int)
}

interface Set<T> {
  fun contains(element: T): Boolean
}

val anyConsumer: (Any) -> Unit = { println(it) }
val stringConsumer: (String) -> Unit = anyConsumer
stringConsumer("Hello") // Hello

//$ kotlinc ListByArray1.kt -include-runtime -d ListByArray1.jar

//$ kotlinc ListByArray2.kt -include-runtime -d ListByArray2.jar

//$ kotlinc MutableList.kt -include-runtime -d MutableList.jar

//$ kotlinc MutableList2.kt -include-runtime -d MutableList2.jar

//$ kotlinc LazyList.kt -include-runtime -d LazyList.jar

//$ kotlinc LazyList2.kt -include-runtime -d LazyList2.jar

//$ kotlinc Writer.kt -include-runtime -d Writer.jar

fun <T> TreeNode<T>.addSubtree(node: TreeNode<T>): TreeNode<T> {
  val newNode = addChild(node.data)
  node.children.forEach { newNode.addSubtree (it) }
  return newNode
}

fun main6() {
  val root = TreeNode("abc")
  val subRoot = TreeNode("def")
  
  root.addSubtree(subRoot)
  println(root) // abc {def {}}
}

main6()

val root = TreeNode<Number>(123)
val subRoot = TreeNode(456.7)
//root.addSubtree(subRoot) // error: type mismatch

fun <T> TreeNode<T>.addSubtree2(node: TreeNode<out T>): TreeNode<T> {
  val newNode = addChild(node.data)
  
  node.children.forEach { newNode.addSubtree2(it) }
  return newNode
}

fun main7() {
  val root = TreeNode<Number>(123)
  val subRoot = TreeNode(456.7)
  
  root.addSubtree2(subRoot)
  
  println(root) // 123 {456.7 {}}
}

main7()

fun <T, U : T> TreeNode<T>.addSubtree3(node: TreeNode<U>): TreeNode<T> {
  val newNode = addChild(node.data)
  
  node.children.forEach { newNode.addSubtree3(it) }
  return newNode
}


fun main8() {
  val root = TreeNode<Number>(123)
  val subRoot = TreeNode(456.7)
  
  root.addSubtree3(subRoot)
  
  println(root) // 123 {456.7 {}}
}

main8()

/*
fun processOut(node: TreeNode<out Any>) {
  node.addChild("xyz") // error: type mismatch
}
*/

fun <T> TreeNode<T>.addTo(parent: TreeNode<in T>) {
  val newNode = parent.addChild(data)
  
  children.forEach { it.addTo(newNode) }
}

fun main9() {
  val root = TreeNode<Number>(123)
  val subRoot = TreeNode(456.7)

  subRoot.addTo(root)
  
  println(root) // 123 {456.7 {}}
}

main9()

interface Producer<out T>{
  fun produce(): T
}

interface Consumer<in T> {
  fun consume(value: T)
}

fun main10() {
  // error: projection is conflicting with variance of the corresponding type parameter of Producer
  //val inProducer: Producer<in String>   
  val outProducer: Producer<out String> // out이 불필요함
  val inConsumer: Consumer<in String>   // in이 불필요함
  // error: projection is conflicting with variance of the corresponding type parameter of Consumer
  //val outConsumer: Consumer<out String>
}

// List의 원소 타입은 `Any?`에 의해 제한되므로 아무 리스트나 가능함
val anyList: List<*> = listOf(1, 2, 3)

// 자기 자신과 비교가능한 아무 객체나 가능(T : Comparable<T> 바운드에 의해)
val anyComparable: Comparable<*> = "abcde"

val any2: Any = ""
any2 is TreeNode<*>
any2 is TreeNode<out Any?> // Ok
//any2 is TreeNode<out Number> // error: cannot check for instance of erased type

interface Named2 {
  val name: String
}

interface Identified2 {
  val id: Int
}

class Registry2<T> where T : Named2, T : Identified2

// Registry의 타입 파라미터의 바운드는 Named와 Identified의 교집합이다
var registry: Registry2<*>? = null

//println(registry?.id ?: "")    // error: unresolved reference: id 
//println(registry?.name ?: "")  // error: unresolved reference: name

typealias IntPredicate = (Int) -> Boolean
typealias IntMap = HashMap<Int, Int>

fun readFirst(filter: IntPredicate) =
  generateSequence{ readLine()?.toIntOrNull() }.firstOrNull(filter)

fun main11() {
  val map = IntMap().also {
    it[1] = 2
    it[2] = 3
  }
}

main11()

sealed class Status {
  object Success : Status()
  class Error(val message: String) : Status()
}

typealias StSuccess = Status.Success
typealias StError = Status.Error

typealias ThisPredicate<T> = T.() -> Boolean
typealias MultiMap<K, V> = Map<K, Collection<V>>

private typealias MyMap = Map<String, String> // 현재 파일 내부에서만 볼 수 있음

fun main12() {
  // error: nested and local type aliases are not supported
  //typealias A = Int 
}

main12()

// error: bounds are not allowed on type alias parameters
//typealias ComparableMap<K : Comparable<K>, V> = Map<K, V> 

typealias A = Int

fun main13() {
  val n = 1
  val a: A = n
  val b: Int = a
}

main13()

class MyMap<T> : HashMap<T, T>()

fun main14() {
  val map: Map<String, String> = MyMap() // Ok, MyMap은 Map의 하위타입
  val myMap: MyMap<String> = map // error: type mismatch
}

main14()




