import java.lang.StringBuilder
import java.io.FileInputStream
import java.io.FileReader 
import java.io.File

val list = listOf("red", "green", "blue") // Create new list

for (item in list) {
  print(item + " ")
} // red green blue 출력함
println("")

val list2 = ArrayList<String>()
list2.add("abc")            // Ok: changing collection data
//list2 = ArrayList<String>() // Error: can’t reassign immutable variable

fun processCollection(c: Iterable<Any>) { /*아무것도 하지 않음*/ }

fun main1() {
  val list = listOf("a", "b", "c") // List<String>
  processCollection(list)          // Ok: List<String>을 List<Any>로 전달
}

main1()

fun processCollection2(c: MutableCollection<Any>) { c.add(123) }

fun main2() {
  val list = arrayListOf("a", "b", "c")  // ArrayList<String>
  //processCollection2(list)               // error: type mismatch
}

main2()

class Person(
  val firstName: String,
  val familyName: String,
  val age: Int
) : Comparable<Person> {
  val fullName get() = "$firstName $familyName"
  override fun compareTo(other: Person) = fullName.compareTo(other.fullName)
}

val AGE_COMPARATOR = Comparator<Person>{ p1, p2 ->
  p1.age.compareTo(p2.age)
}

val AGE_COMPARATOR2 = compareBy<Person>{ it.age }
val REVERSE_AGE_COMPARATOR = compareByDescending<Person>{ it.age }

val list3 = ArrayList<String>()
list3.add("red")
list3.add("green")
println(list3) // [red, green]

// 코틀린 스크립트 모드에서는 set을 게터/세터의 set으로 인식해서 문제가 생길 수 있다. 변수 이름을 바꿔서 문제를 해결한다.
val set1 = HashSet<Int>()
set1.add(12)
set1.add(21)
set1.add(12)
println(set1) // [12, 21]

val map = java.util.TreeMap<Int, String>()
map[20] = "Twenty"
map[10] = "Ten"
println(map) // {10=Ten, 20=Twenty}

val emptyList = emptyList<String>()
println(emptyList)   // []
//emptyList.add("abc") // error: unresolved reference: add

val singletonSet = setOf("abc")
println(singletonSet)      // [abc]
//singletonSet.remove("abc") // error: unresolved reference

val mutableList = mutableListOf("abc")
println(mutableList) // [abc]
mutableList.add("def")
mutableList[0] = "xyz"
println(mutableList) // [xyz, def]

val sortedSet = sortedSetOf(8, 5, 7, 1, 4)
println(sortedSet) // [1, 4, 5, 7, 8]
sortedSet.add(2)
println(sortedSet) // [1, 2, 4, 5, 7, 8]

println(List(5) { it*it }) // [0, 1, 4, 9, 16]

val numbers = MutableList(5) { it*2 }
println(numbers) // [0, 2, 4, 6, 8]
numbers.add(100)
println(numbers) // [0, 2, 4, 6, 8, 100]

println(sequenceOf(1, 2, 3).iterator().next())             // 1
println(listOf(10, 20, 30).asSequence().iterator().next()) // 10
println(
  mapOf(1 to "One", 2 to "Two").asSequence().iterator().next()
)                                                          // 1=One

val numbers2 = generateSequence{ println("Input a number:");readLine()?.toIntOrNull() }

println(numbers2.first()) // 실제 원소를 소비해야 readLine이 호출됨

// 무한 시퀀스(단, 값 오버플로가 발생해서 음수와 양수를 왔다갔다 함): 1, 2, 4, 8,...
val powers = generateSequence(1) { it*2 }

// 유한 시퀀스: 10, 8, 6, 4, 2, 0
val evens = generateSequence(10) { if (it >= 2) it - 2 else null }

val numbers3 = sequence {
  yield(0)
  yieldAll(listOf(1, 2, 3))
  yieldAll(intArrayOf(4, 5, 6).iterator())
  yieldAll(generateSequence(10) { if (it < 50) it*3 else null })
}

println(numbers3.toList()) // [0, 1, 2, 3, 4, 5, 6, 10, 30, 90]

println(
  listOf(1, 2, 3, 2, 3).toSet()
) // [1, 2, 3]

println(
  arrayOf("red", "green", "blue").toSortedSet()
) // [blue, green, red]

println(
  mapOf(1 to "one", 2 to "two", 3 to "threen").toList()
) // [(1, one), (2, two), (3, threen)]

println(
  sequenceOf(1 to "one", 2 to "two", 3 to "threen").toMap()
) // {1=one, 2=two, 3=threen}

val map2 = mapOf(1 to "one", 2 to "two", 3 to "three")

for ((key, value) in map2) {
  println("$key -> $value")
}

intArrayOf(1, 2, 3).forEach { println(it*it) }

listOf("a", "b", "c").forEach { println("'$it'") }

sequenceOf("a", "b", "c").forEach { println("'$it'") }

mapOf(1 to "one", 2 to "two", 3 to "three").forEach { (key, value) ->
  println("$key -> $value")
}

listOf(10, 20, 30).forEachIndexed { i, n ->println("$i: ${n*n}") }

val list4 = listOf(1, 2, 3)

println(list4.isEmpty())                 // false
println(list4.size)                      // 3
println(list4.contains(4))               // false
println(2 in list4)                      // true
println(list4.containsAll(listOf(1, 2))) // true

val list5 = arrayListOf(1, 2, 3)
list5.add(4)                     // 원소 하나 추가: [1, 2, 3, 4]
list5.remove(3)                  // 원소 하나 제거: [1, 2, 4]
list5.addAll(setOf(5, 6))        // 합집합: [1, 2, 4, 5, 6]
list5.removeAll(listOf(1, 2))    // 차집합: [4, 5, 6]
list5.retainAll(listOf(5, 6, 7)) // 교집합: [5, 6]
list5.clear()                    // 모든 원소 제거: []

list5 += 4
list5 -= 3
list5 += setOf(5, 6)
list5 -= listOf(1, 2)

println(listOf(1, 2, 3) + 4) // [1, 2, 3, 4]
println(listOf(1, 2, 3) - setOf(2, 5)) // [1, 3]

val readOnly = listOf(1, 2, 3)
//readOnly += 4 // error: can't assign to val
var mutable = listOf(1, 2, 3)
mutable += 4  // Correct

val list6 = listOf(1, 4, 6, 2, 4, 1, 7)

println(list6.get(3))         // 2
println(list6[2])             // 6
//println(list6[10])            // java.lang.ArrayIndexOutOfBoundsException
println(list6.indexOf(4))     // 1
println(list6.lastIndexOf(4)) // 4
println(list6.indexOf(8))     // -1

val list7 = arrayListOf(1, 4, 6, 2, 4, 1, 7)

list7.set(3, 0)   // [1, 4, 6, 0, 4, 1, 7]
list7[2] = 1      // [1, 4, 1, 0, 4, 1, 7]
list7.removeAt(5) // [1, 4, 1, 0, 4, 7]
list7.add(3, 8)   // [1, 4, 1, 8, 0, 4, 7]

val list8 = arrayListOf(1, 4, 6, 2, 4, 1, 7)
val segment = list8.subList(2, 5) // [6, 2, 4, 1]

list8[3] = 0
println(segment[1])  // 0
segment[1] = 8
println(list8[3])    // 8

val map3 = mapOf(1 to "I", 5 to "V", 10 to "X", 50 to "L")

println(map3.isEmpty())              // false
println(map3.size)                   // 4
println(map3.get(5))                 // V
println(map3[10])                    // X
println(map3[100])                   // null
println(map3.getOrDefault(100, "?")) // ?
println(map3.getOrElse(100) { "?" }) // ?
println(map3.containsKey(10))        // true
println(map3.containsValue("C"))     // false
println(map3.keys)                   // [1, 5, 10, 50]
println(map3.values)                 // [I, V, X, L]
println(map3.entries)                // [1=I, 5=V, 10=X, 50=L]

val map4 = sortedMapOf(1 to "I", 5 to "V")

map4.put(100, "C")            // {1=I, 5=V, 100=C}
map4[500] = "D"               // {1=I, 5=V, 100=C, 500=D}
map4.remove(1)                // {5=V, 100=C, 500=D}
map4.putAll(mapOf(10 to "X")) // {5=V, 10=X, 100=C, 500=D}
map4 += 50 to "L"             // {5=V, 10=X, 50=L, 100=C, 500=D}
map4 += mapOf(2 to "II",
3 to "III")                  // {2=II, 3=III, 5=V, 10=X, 50=L, 100=C, 500=D}
map4 -= 100                   // {2=II, 3=III, 5=V, 10=X, 50=L, 500=D}
map4 -= listOf(2, 3)          // {5=V, 10=X, 50=L, 500=D}

println(listOf(1, 2, 3).first())            // 1
println(listOf(1, 2, 3).last())             // 3
//println(emptyArray<String>().first())       // java.util.NoSuchElementException
println(emptyArray<String>().firstOrNull()) // null

val seq = generateSequence(1) { if (it > 50) null else it * 3 }

println(seq.first()) // 1
println(seq.last())  // 81

println(listOf(1, 2, 3).first { it > 2 })      // 31
println(listOf(1, 2, 3).lastOrNull { it < 0 }) // null
//println(intArrayOf(1, 2, 3).first { it > 3 })  // java.util.NoSuchElementException

println(listOf(1).single())                  // 1
println(emptyArray<String>().singleOrNull()) // null
println(setOf(1, 2, 3).singleOrNull())       // null
// java.lang.IllegalArgumentException: Sequence has more than one element.
//println(sequenceOf(1, 2, 3).single())

println(listOf(1, 2, 3).elementAt(2))                        // 3
println(sortedSetOf(1, 2, 3).elementAtOrNull(-1))            // null
println(arrayOf("a", "b", "c").elementAtOrElse(1) { "???" }) // b

val seq2 = generateSequence(1) { if (it > 50) null else it * 3 }

println(seq2.elementAtOrNull(2))                              // 9
println(seq2.elementAtOrElse(100) { Int.MAX_VALUE })          // 2147483647
//println(seq2.elementAt(10))                                   // java.lang.IndexOutOfBoundsException

val list9 = listOf(1, 2, 3)

val (x, y) = list9        // 1, 2
//val (a, b, c, d) = list9  // java.lang.ArrayIndexOutOfBoundsException


println(listOf(1, 2, 3, 4).all { it < 10 })     // true
println(listOf(1, 2, 3, 4).all { it % 2 == 0 }) // false
println(
  mapOf(1 to "I", 5 to "V", 10 to "X")
    .all { it.key == 1 || it.key % 5 == 0 }
)                                               // true
// 1, 3, 9, 27, 81

val seq3 = generateSequence(1) { if (it < 50) it*3 else null }

println(seq3.all { it % 3 == 0 }) // false
println(seq3.all { it == 1 || it % 3 == 0 }) // true


println(listOf(1, 2, 3, 4).none { it > 5 }) // true
println(
  mapOf(1 to "I", 5 to "V", 10 to "X").none { it.key % 2 == 0 }
)                                           // false

// 1, 3, 9, 27, 81로 이뤄진 시퀀스 만들기
val seq4 = generateSequence(1) { if (it < 50) it*3 else null }
println(seq4.none { it >= 100 })             // true

// 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0,...로 이뤄진 시퀀스
val seq5 = generateSequence(0) { (it + 1) % 5 }
//println(seq5.all { it < 5 }) // 끝나지 않음

println(emptyList<String>().any())  // false
println(emptyList<String>().none()) // true
println(listOf(1, 2, 3).any())      // true
println(listOf(1, 2, 3).none())     // false

println(listOf(1, 2, 3, 4).count())                   // 4
println(mapOf(1 to "I", 5 to "V", 10 to "X").count()) // 3

// 1, 3, 9, 27, 81로 이뤄진 시퀀스
val seq6 = generateSequence(1) { if (it < 50) it*3 else null }
println(seq6.count())                                  // 5

// 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0,... 로 이뤄진 시퀀스
val seq7 = generateSequence(0) { (it + 1) % 5 }

// Int.MAX_VALUE보다 많은 원소를 방문하자마자 예외를 던짐
// java.lang.ArithmeticException: Count overflow has happened
//println(seq7.count())

println(listOf(1, 2, 3, 4).count { it < 0 })      // 0
println(listOf(1, 2, 3, 4).count { it % 2 == 0 }) // 2
println(
  mapOf(1 to "I", 5 to "V", 10 to "X").count { it.key == 1 }
)                                                 // 1

// 1, 3, 9, 27, 81로 이뤄진 시퀀스
val seq8 = generateSequence(1) { if (it < 50) it*3 else null }

println(seq8.count { it % 3 == 0 }) // 4
println(seq8.count { it > 100 })    // 0

println(listOf(1, 2, 3, 4).sum())           // 10
println(doubleArrayOf(1.2, 2.3, 3.4).sum()) // 6.9

// 1, 3, 9, 27, 81의 합계 구하기
val seq9 = generateSequence(1) { if (it < 50) it*3 else null }

println(seq9.sum()) // 121

println(listOf(1, 2, 3, 4).sumOf { it/4.0 })   // 2.5
println(arrayOf("1", "2", "3").sumOf { it.toInt() }) // 6

// X, XX, XXX, XXXX, XXXXX
val seq10 = generateSequence("X") {
  if (it.length>= 5) null else it + "X"
}

println(seq10.sumOf { it.length })                     // 15

println(listOf(1, 2, 3, 4).average())           // 2.5
println(doubleArrayOf(1.2, 2.3, 3.4).average()) // 2.3000000000000003

// 1, 3, 9, 27, 81의 평균 구하기
val seq11 = generateSequence(1) { if (it < 50) it*3 else null }

println(seq11.average())                             // 24.2

println(intArrayOf(5, 8, 1, 4, 2).minOrNull())               // 1
println(intArrayOf(5, 8, 1, 4, 2).maxOrNull())               // 8
println(listOf("abc", "w", "xyz", "def", "hij").minOrNull()) // abc
println(listOf("abc", "w", "xyz", "def", "hij").maxOrNull()) // xyz

// 1, -3, 9, -27, 81
val seq12 = generateSequence(1) { if (it < 50) -it * 3 else null }

println(seq12.minOrNull()) // -27
println(seq12.maxOrNull()) // 81

class Person2(val firstName: String,
             val familyName: String,
             val age: Int) {
  override fun toString() = "$firstName $familyName: $age"
}

fun main3() {
  val persons = sequenceOf(
    Person2("Brook", "Watts", 25),
    Person2("Silver", "Hudson", 30),
    Person2("Dane", "Ortiz", 19),
    Person2("Val", "Hall", 28)
  )
  
  println(persons.minByOrNull { it.firstName })  // Brook Watts: 25
  println(persons.maxByOrNull { it.firstName })  // Val Hall: 28
  println(persons.minByOrNull { it.familyName }) // Val Hall: 25
  println(persons.maxByOrNull { it.familyName }) // Brook Watts: 28
  println(persons.minByOrNull { it.age })        // Dane Ortiz: 19
  println(persons.maxByOrNull { it.age})         // Silver Hudson: 30
}

main3()

//$ kotlinc PersonMaxMin.kt -include-runtime -d PersonMaxMin.jar
//$ java -jar PersonMaxMin.jar


println(listOf(1, 2, 3).joinToString()) // 1, 2, 3

println(listOf(1, 2, 3).joinToString { it.toString(2) }) // 1, 10, 11

val list10 = listOf(1, 2, 3)
println(list10.joinToString(prefix = "[", postfix = "]")) // [1, 2, 3]
println(list10.joinToString(separator = "|"))             // 1|2|3
println(list10.joinToString(limit = 2))                   // 1, 2, ...
println(list10.joinToString(
  limit = 1,
  separator = " ",
  truncated = "???"
))                                                      // 1 ???

//import java.lang.StringBuilder  // 파일 맨 앞에서 함

fun main4() {
  val builder = StringBuilder("joinTo: ")
  val list = listOf(1, 2, 3)
  
  println(list.joinTo(builder, separator = "|")) // joinTo: 1|2|3
}

main4()

println(intArrayOf(1, 2, 3, 4, 5).reduce { acc, n -> acc * n })  // 120
println(listOf("a", "b", "c", "d").reduce { acc, s -> acc + s }) // abcd

// 8
println(intArrayOf(1, 2, 3, 4, 5)
  .reduceIndexed { i, acc, n -> if (i % 2 == 1) acc * n else acc })

// abd
println(listOf("a", "b", "c", "d")
  .reduceIndexed { i, acc, s -> if (i % 2 == 1) acc + s else acc })

println(
  intArrayOf(1, 2, 3, 4).fold("") { acc, n -> acc + ('a' + n - 1) }
) // abcd

println(
  listOf(1, 2, 3, 4).foldIndexed("") { i, acc, n ->
    if (i % 2 == 1) acc + ('a' + n - 1) else acc
  }
) // bd

println(
  arrayOf("a", "b", "c", "d").reduceRight { s, acc -> acc + s }
) // dcba

println(
  listOf("a", "b", "c", "d").reduceRightIndexed { i, s, acc ->
    if (i % 2 == 0) acc + s else acc
  }	
) // dca

println(
  intArrayOf(1, 2, 3, 4).foldRight("") { n, acc -> acc + ('a' + n - 1) }
) // dcba

println(
  listOf(1, 2, 3, 4).foldRightIndexed("") { i, n, acc ->
    if (i % 2 == 0) acc + ('a' + n - 1) else acc
  }
) // ca

// List: [green, blue, green]
println(
  listOf("red", "green", "blue", "green").filter { it.length> 3 }
)

// List: [green, blue]
println(setOf("red", "green", "blue", "green").filter { it.length> 3 })

// List: [green, blue, green]
println(
  arrayOf("red", "green", "blue", "green").filter { it.length> 3 }
)

// List: [2, 4]
println(byteArrayOf(1, 2, 3, 4, 5).filter { it % 2 == 0 })

// Map: {X=10, L=50}
println(
  mapOf("I" to 1, "V" to 5, "X" to 10, "L" to 50)
    .filter { it.value> 5 }
)

// Sequence
val seq13 = generateSequence(100) {
  if (it != 0) it/3 else null
}.filter { it > 10 }

// 리스트로 변환: [100, 33, 11]
println(seq13.toList())

val map5 = mapOf("I" to 1, "V" to 5, "X" to 10, "L" to 50)

println(map5.filterKeys { it != "L" })  // {X=1, V=5, X=10}
println(map5.filterValues { it >= 10 }) // {X=10, L=50}

// [red]
println(listOf("red", "green", "blue").filterNot { it.length> 3 })

// {I=1, V=5}
println(
  mapOf("I" to 1, "V" to 5, "X" to 10, "L" to 50)
    .filterNot { it.value> 5 }
)

val list11 = listOf("red", "green", "blue", "orange")

// [green, blue]
println(
  list11.filterIndexed { i, v ->v.length> 3 && i<list11.lastIndex }
)

val seq14 = generateSequence(100) { if (it != 0) it/3 else null }

// [33, 11, 3, 1]
println(seq14.filterIndexed { i, v -> v > 0 && i> 0 }.toList())

val list12 = listOf("red", null, "green", null, "blue")

// 널이 될 수 있기 때문에 안전한 호출이나 널 아님 단언이 필요함
// error: only safe (?.) or non-null asserted (!!.) calls are allowed
//list12.forEach { println(it.length) }

// Ok: it이 널이 될 수 없음
list12.filterNotNull().forEach { println(it.length) }

val hotchpotch = listOf(1, "two", 3, "four", 5, "six")
val numbers4 = hotchpotch.filterIsInstance<Int>()
val strings = hotchpotch.filterIsInstance<String>()

println(numbers4.filter { it > 2 })      // [3, 5]
println(strings.filter { it != "two" }) // [four, six]


val allStrings = ArrayList<String>()

// green, blue 추가됨
listOf("red", "green", "blue").filterTo(allStrings) { it.length> 3 }

// one, two, three 추가됨
arrayOf("one", null, "two", null, "three").filterNotNullTo(allStrings)

// abcde, bcde, cde, de, e,
val seq15 = generateSequence("abcde") {
  if (it.isNotEmpty()) it.substring(1) else null
}

// abcde, bcde, cde 추가됨
seq15.filterNotTo(allStrings) { it.length< 3 }

// [green, blue, one, two, three, abcde, bcde, cde]
println(allStrings)

val list13 = arrayListOf("red", "green", "blue")
//list13.filterTo(list13) { it.length> 3 } // java.util.ConcurrentModificationException

val (evens2, odds2) = listOf(1, 2, 3, 4, 5).partition { it % 2 == 0 }
println(evens2) // [2, 4]
println(odds2) // [1, 3, 5]

val seq16 = generateSequence(100) { if (it == 0) null else it/3 }

val (evens3, odds3) = seq16.partition { it % 2 == 0 }

println(evens3) // [100, 0]
println(odds3) // [33, 11, 3, 1]

println(setOf("red", "green", "blue").map { it.length }) // [3, 5, 4]
println(listOf(1, 2, 3, 4).map { it*it })                // [1, 4, 9, 16]
println(byteArrayOf(10, 20, 30).map { it.toString(16) }) // [a, 14, 1e]

// 50, 16, 5, 1, 0
val seq17 = generateSequence(50) { if (it == 0) null else it / 3 }

println(seq17.map { it*3 }.toList())                        // [150, 48, 15, 3, 0]

// [(0, 0), (1, 1), (2, 4), (3, 9), (4, 16), (5, 25)]
println(List(6) { it*it }.mapIndexed { i, n ->i to n })

println(
  arrayOf("1", "red", "2", "green", "3").mapNotNull { it.toIntOrNull() }
) // [1, 2, 3]

println(
  listOf("1", "red", "2", "green", "3").mapIndexedNotNull { i, s ->
    s.toIntOrNull()?.let { i to it }
  }
) // [(0, 1), (2, 2), (4, 3)]

val map6 = mapOf("I" to 1, "V" to 5, "X" to 10, "L" to 50)

// [I 1, V 5, X 10, L 50]
println(map6.map { "${it.key} ${it.value}" })

// {i=1, v=5, x=10, l=50}
println(map6.mapKeys { it.key.lowercase() })

// {I=1, V=5, X=a, L=32}
println(map6.mapValues { it.value.toString(16) })

val result = ArrayList<String>()

listOf(1, 2, 3).mapTo(result) { it.toString() }

arrayOf("one", "two", "three").mapIndexedTo(result) { i, s ->
  "${i + 1}: s"
}

sequenceOf("100", "?", "101", "?", "110").mapNotNullTo(result) {
  it.toIntOrNull(2)?.toString()
}

println(result) // [1, 2, 3, 1: s, 2: s, 3: s, 4, 5, 6]

// [a, b, c, d, e, f, g, h, i]
println(setOf("abc", "def", "ghi").flatMap { it.asIterable() })

// [1, 2, 3, 4]
println(listOf(1, 2, 3, 4).flatMap { listOf(it) })

// [1, 1, 2, 1, 2, 3]
Array(3) { it + 1 }.flatMap { 1..it }

println(
  listOf(listOf(1, 2), setOf(3, 4), listOf(5)).flatten()
) // [1, 2, 3, 4, 5]

println(Array(3) { arrayOf("a", "b") }.flatten()) // [a, b, a, b, a, b]

println(
  sequence {
    yield(sequenceOf(1, 2))
    yield(sequenceOf(3, 4))
  }.flatten().toList()
) // [1, 2, 3, 4]

val result2 = ArrayList<String>()

listOf(listOf("abc", "def"), setOf("ghi"))
  .flatMapTo(result2) { it }
  
sequenceOf(sequenceOf(1, 2), sequenceOf(3, 4))
  .flatMapTo(result2) { it.map { "$it" } }
  
println(result2) // [abc, def, ghi, 1, 2, 3, 4]

val result3 = ArrayList<String>()

listOf(listOf("abc", "def"), setOf("ghi"))
  .flatMapTo(result3) { it }
  
sequenceOf(sequenceOf(1, 2), sequenceOf(3, 4))
  .flatMapTo(result3) { it.map { "$it" } }
  
println(result3) // [abc, def, ghi, 1, 2, 3, 4]

println(
  listOf("red", "green", "blue").associateWith { it.length }
) // {red=3, green=5, blue=4}

println(
  generateSequence(1) { if (it > 50) null else it*3 }
    .associateWith { it.toString(3) }
) // {1=1, 3=10, 9=100, 27=1000, 81=10000}

// {3=red, 5=green, 4=blue}
println(listOf("red", "green", "blue").associateBy { it.length })

// {1=15, 2=25, 3=35}
println(intArrayOf(10, 15, 20, 25, 30, 35).associateBy { it/10 })

// {1=1, 10=3, 100=9, 1000=27, 10000=81}
println(
  generateSequence(1) { if (it > 50) null else it*3 }
    .associateBy { it.toString(3) }
)

println(
  listOf("red", "green", "blue")
    .associate { it.uppercase() to it.length }
) // {RED=3, GREEN=5, BLUE=4}

println(
  intArrayOf(10, 15, 20, 25, 30, 35).associate { it to it/10 }
) // {10=1, 15=1, 20=2, 25=2, 30=3, 35=3}

println(
  generateSequence(1) { if (it > 50) null else it*3 }
    .associate {
      val s = it.toString(3)
      "3^${s.length - 1}" to s
    }
) // {3^0=1, 3^1=10, 3^2=100, 3^3=1000, 3^4=10000}

println(
  listOf("red", "green", "blue").associateBy(
    keySelector = { it.uppercase() },
    valueTransform = { it.length }
  )
) // {RED=3, GREEN=5, BLUE=4}

// 0, 1, 4, 9, 16, 25
println(List(6) { it*it }.slice(2..4)) // [4, 9, 16]

// 0, 1, 8, 27, 64, 125
println(Array(6) { it*it*it }.slice(2..4)) // [8, 27, 64]

val slice = Array(6) { it*it*it }.sliceArray(2..4).contentToString()

println(List(6) { it*it }.slice(listOf(1, 2, 3)))    // [1, 4, 9]
println(Array(6) { it*it*it }.slice(setOf(1, 2, 3))) // [1, 8, 27]
println(
  Array(6) { it*it*it }.sliceArray(listOf(1, 2, 3)).contentToString()
) // [1, 8, 27]

println(List(6) { it*it }.take(2))         // [0, 1]
println(List(6) { it*it }.takeLast(2))     // [16, 25]
println(Array(6) { it*it*it }.take(3))     // [0, 1, 8]
println(Array(6) { it*it*it }.takeLast(3)) // [27, 64, 125]

val seq18 = generateSequence(1) { if (it > 100) null else it*3 }
println(seq18.take(3).toList()) // [1, 3, 9]

println(List(6) { it*it }.drop(2))         // [4, 9, 16, 25]
println(List(6) { it*it }.dropLast(2))     // [0, 1, 4, 9]

println(Array(6) { it*it*it }.drop(3))     // [27, 64, 125]
println(Array(6) { it*it*it }.dropLast(3)) // [0, 1, 8]

val seq19 = generateSequence(1) { if (it > 100) null else it*3 }
println(seq19.drop(3).toList())              // [27, 81, 243]

val list14 = List(6) { it * it }

println(list14.takeWhile { it < 10 })         // [0, 1, 4, 9]
println(list14.takeLastWhile { it > 10 })     // [16, 25]
println(list14.dropWhile { it < 10 })         // [16, 25]
println(list14.dropLastWhile { it > 10 })     // [0, 1, 4, 9]

val seq20 = generateSequence(1) { if (it > 100) null else it*3 }

println(seq20.takeWhile { it < 10 }.toList()) // [1, 3, 9]
println(seq20.dropWhile { it < 10 }.toList()) // [27, 81, 243]

// 0, 1, 4, 9, 16, 25, 36, 49, 64, 81
val list15 = List(10) { it*it }

println(list15.chunked(3)) // [[0, 1, 4], [9, 16, 25], [36, 49, 64], [81]]

// 1, 3, 9, 27, 81, 243, 729
val seq21 = generateSequence(1) { if (it > 300) null else it*3 }

println(seq21.chunked(3).toList()) // [[1, 3, 9], [27, 81, 243], [729]]

// 0, 1, 4, 9, 16, 25, 36, 49, 64, 81
val list16 = List(10) { it*it }

println(list16.chunked(3) { it.sum() }) // [5, 50, 149, 81]

// 1, 3, 9, 27, 81, 243, 729
val seq22 = generateSequence(1) { if (it > 300) null else it*3 }

println(seq22.chunked(3) { it.sum() }.toList()) // [13, 351, 729]

// 0, 1, 4, 9, 16, 25
val list17 = List(6) { it*it }

// [[0, 1, 4], [1, 4, 9], [4, 9, 16], [9, 16, 25]]
println(list17.windowed(3))

// 1, 3, 9, 27, 81, 243
val seq23 = generateSequence(1) { if (it > 100) null else it*3 }

// [[1, 3, 9], [3, 9, 27], [9, 27, 81], [27, 81, 243]]
println(seq23.windowed(3).toList())

// 0, 1, 4, 9, 16, 25
val list18 = List(6) { it*it }

println(list18.windowed(3) { it.sum() }) // [5, 14, 29, 50]

// 1, 3, 9, 27, 81, 243
val seq24 = generateSequence(1) { if (it > 100) null else it*3 }

println(seq24.windowed(3) { it.sum() }.toList()) // [13, 39, 117, 351]

// 0, 1, 4, 9, 16, 25
val list19 = List(6) { it*it }

// 짝수 인덱스(0, 2) 원소만 윈도우 시작 원소가 된다
// [[0, 1, 4], [4, 9, 16]]
println(list19.windowed(3, step = 2))

// 맨 뒤에 두 가지 부분 윈도우(크기가 3보다 작음)를 포함시킨다
// [[0, 1, 4], [1, 4, 9], [4, 9, 16], [9, 16, 25], [16, 25], [25]]
println(list19.windowed(3, partialWindows = true))

// 0, 1, 4, 9, 16, 25
val list20 = List(6) { it*it }

// [(0, 1), (1, 4), (4, 9), (9, 16), (16, 25)]
println(list20.zipWithNext())

// 1, 3, 9, 27, 81, 243
val seq25 = generateSequence(1) { if (it > 100) null else it*3 }

// [(1, 3), (3, 9), (9, 27), (27, 81), (81, 243)]
println(seq25.zipWithNext().toList())

// [0, 4, 36, 144, 400]
println(List(6) { it*it }.zipWithNext { a, b -> a * b })

println(intArrayOf(5, 8, 1, 4, 2).sorted()) // [1, 2, 4, 5, 8]

println(
  intArrayOf(5, 8, 1, 4, 2).sortedDescending()
)                                           // [8, 5, 4, 2, 1]

println(
  listOf("abc", "w", "xyz", "def", "hij").sorted()
)                                           // [abc, def, hij, w, xyz]

println(
  listOf("abc", "w", "xyz", "def", "hij").sortedDescending()
)                                           // [xyz, w, hij, def, abc]

// 1, -3, 9, -27, 81
val seq26 = generateSequence(1) { if (it < 50) -it * 3 else null }

println(seq26.sorted().toList())              // [-27, -3, 1, 9, 81]
println(seq26.sortedDescending().toList())    // [81, 9, 1, -3, -27]

//$ kotlinc PersonSortedWith.kt -include-runtime -d PersonSortedWith.jar
//$ java -jar PersonSortedWith.jar

val array = intArrayOf(4, 0, 8, 9, 2).apply { sort() }
println(array.contentToString()) // [0, 2, 4, 8, 9]

val list21 = arrayListOf("red", "blue", "green").apply { sort() }
println(list21)                    // [blue, green, red]

println(intArrayOf(1, 2, 3, 4, 5).reversed())      // [5, 4, 3, 2, 1]
println(listOf("red", "green", "blue").reversed()) // [blue, green, red]

val array2 = intArrayOf(1, 2, 3, 4, 5).apply { reverse() }.contentToString()
println(array2) // [5, 4, 3, 2, 1]

val list22 = arrayListOf("red", "green", "blue").apply { reverse() }
println(list22)  // [blue, green, red]

val list23 = arrayListOf("red", "green", "blue")
val reversedCopy = list23.reversed()
val reversedMirror = list23.asReversed()

list23[0] = "violet"

println(list23)         // [violet, green, blue]
println(reversedCopy)   // [blue, green, red]
println(reversedMirror) // [blue, green, violet]

println(listOf(1, 2, 3, 4, 5).shuffled())

arrayListOf(1, 2, 3, 4, 5).shuffle()


//$ kotlinc FileRead.kt -include-runtime -d FileRead.jar
//$ java -jar FileRead.jar

// import java.io.FileInputStream // 맨 앞에 함
FileInputStream("data.bin").buffered().use {
  var sum = 0
  for (byte in it) sum += byte
}

// import java.io.FileReader // 맨 앞에 함
FileReader("data.bin").buffered().use {
  for (line in it.lineSequence()) println(line)
}

//$ kotlinc FileRead2.kt -include-runtime -d FileRead2.jar
//$ java -jar FileRead2.jar

//$ kotlinc FileWrite.kt -include-runtime -d FileWrite.jar
//$ java -jar FileWrite.jar

val lines = FileReader("data.bin").use { it.readLines() }

//$ kotlinc Buffer.kt -include-runtime -d Buffer.jar
//$ java -jar Buffer.jar

//$ kotlinc BinStream.kt -include-runtime -d BinStream.jar
//$ java -jar BinStream.jar

println("Hello".byteInputStream().read().toChar())                  // H
println("Hello".byteInputStream(Charsets.US_ASCII).read().toChar()) // H

println("One\nTwo".reader().readLines()) // [One, Two]

println(byteArrayOf(10, 20, 30).inputStream().read())

val bytes = byteArrayOf(10, 20, 30, 40, 50)

println(
  bytes.inputStream(2, 2).readBytes().contentToString()
) // [30, 40]

//$ kotlinc Text.kt -include-runtime -d Text.jar
//$ java -jar Text.jar

//$ kotlinc Bytes.kt -include-runtime -d Bytes.jar
//$ java -jar Bytes.jar

//$ kotlinc ForEachLine.kt -include-runtime -d ForEachLine.jar
//$ java -jar ForEachLine.jar

//$ kotlinc UseLines.kt -include-runtime -d UseLines.jar
//$ java -jar UseLines.jar

//$ kotlinc ForEachBlock.kt -include-runtime -d ForEachBlock.jar
//$ java -jar ForEachBlock.jar

//$ kotlinc FileSystem.kt -include-runtime -d FileSystem.jar
//$ java -jar FileSystem.jar

//$ kotlinc CopyTo.kt -include-runtime -d CopyTo.jar
//$ java -jar CopyTo.jar

//$ kotlinc CopyToOverwrite.kt -include-runtime -d CopyToOverwrite.jar
//$ java -jar CopyToOverwrite.jar

//$ kotlinc CopyToRecursive.kt -include-runtime -d CopyToRecursive.jar
//$ java -jar CopyToRecursive.jar

//$ kotlinc Walk.kt -include-runtime -d Walk.jar
//$ java -jar Walk.jar

println(
  File("my").walk().maxDepth(1).map { it.name }.toList()
) // [my, dir]

println(
  File("my")
    .walk()
    .onEnter { it.name != "dir" }
    .onLeave { println("Processed: ${it.name}") }
    .map { it.name }
    .toList())

// 1.5 버전에서 정상 작동해야 하나 안되는 경우도 있음
//$ kotlinc TempPath.kt -include-runtime -d TempPath.jar
//$ java -jar TempPath.jar

val tmpDir = createTempDir(prefix = "data")
val tmpFile = createTempFile(directory = tmpDir)
