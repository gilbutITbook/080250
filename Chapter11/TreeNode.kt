class TreeNode<T>(val data: T) {
    private val _children = arrayListOf<TreeNode<T>>()
  
    var parent: TreeNode<T>? = null
        private set
  
    // 복합 대입 연산자 함수들
    operator fun plusAssign(data: T) {
        val node = TreeNode(data)
        _children += node
        node.parent = this
    }
    operator fun minusAssign(data: T) {
        val index = _children.indexOfFirst { it.data == data }
        if (index < 0) return
        val node = _children.removeAt(index)
        node.parent = null
    }
	
	// -=의 동작을 확인하기 위해 단순 이항 연산자 함수를 정의
	operator fun minus(data: T):TreeNode<T> = TODO("Comming soon")
  
    override fun toString() =
        _children.joinToString(prefix = "$data {", postfix = "}")
}

fun main() {
    val tree = TreeNode("root")
    tree += "child 1"
    tree += "child 2"
    println(tree) // root {child 1 {}, child 2 {}}

    tree -= "child 2"
    println(tree) // root {child 1 {}}
}