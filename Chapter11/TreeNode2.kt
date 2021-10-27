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

    operator fun get(index: Int) = _children[index]
   
    operator fun set(index: Int, node: TreeNode<T>) {
      node.parent?._children?.remove(node)
      node.parent = this
      _children[index].parent = null
      _children[index] = node
    }
}

fun main() {
  val root = TreeNode("Root")
  
  root += "Child 1"
  root += "Child 2"
  println(root[1].data) // Child 2
  
  root[0] = TreeNode("Child 3")
  println(root[0].data) // Child 3

}