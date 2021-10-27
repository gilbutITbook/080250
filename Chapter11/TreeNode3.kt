class TreeNode<T>(val data: T) {
    private val _children = arrayListOf<TreeNode<T>>()
  
    var parent: TreeNode<T>? = null
        private set
  
    val children: List<TreeNode<T>>get() = _children
	
    fun addChild(data: T) = TreeNode(data).also {
        _children += it
        it.parent = this
    }
	
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

operator fun <T>TreeNode<T>.iterator() = children.iterator()

fun main() {
    val content = TreeNode("Title").apply {
        addChild("Topic 1").apply {
            addChild("Topic 1.1")
            addChild("Topic 1.2")
        }
        addChild("Topic 2")
        addChild("Topic 3")
    }
    for (item in content) {
        println(item.data)
    }
}
