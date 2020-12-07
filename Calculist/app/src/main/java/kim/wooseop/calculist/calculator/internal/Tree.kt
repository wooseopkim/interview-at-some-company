package kim.wooseop.calculist.calculator.internal

class Tree(
    priorities: (Operation) -> Pair<Operation, Int>,
    private val eagerPruning: Boolean = false
) {

    companion object {

        const val DEFAULT_PRIORITY = 0

    }

    private var root: Node? = null

    private val priorities =
        Operation.values()
            .map(priorities)
            .map { it.first to it.second + DEFAULT_PRIORITY }
            .toMap()

    fun add(c: Char) {
        val operations = Operation.symbolMap
        if (c in operations) {
            takeOperator(operations[c]!!)
        } else if (c.isDigit()) {
            takeInteger(Character.getNumericValue(c))
        } else {
            throw UnsupportedOperationException()
        }
    }

    fun evaluate(): Int? {
        pruneIfEager()
        return root?.evaluate()
    }

    fun prune() {
        root?.run { root = Node.Integer(evaluate()) }
    }

    fun clear() {
        root = null
    }

    fun size(): Int {
        val root = root ?: return 0
        return count(root.left, root.right)
    }

    fun makeString(): String? {
        if (root == null) {
            return null
        }

        pruneIfEager()

        val buf = StringBuffer()
        traverseInOrder(root) { buf.append(it.makeString()) }
        return buf.toString()
    }

    private fun takeInteger(n: Int) {
        val node = Node.Integer(n)
        val rightmost = findRightmost(root)
        when (rightmost) {
            is Node.Integer -> rightmost.value = rightmost.value * 10 + n
            is Node.Operator -> rightmost.right = node
            null -> root = node
        }
    }


    private fun takeOperator(op: Operation) {
        val rightmost = findRightmost(root)
        when (rightmost) {
            is Node.Integer -> locateOperator(root!!, op)
            is Node.Operator -> rightmost.operation = op
            null -> throw IllegalStateException()
        }
    }

    private fun locateOperator(root: Node, op: Operation) {
        val node = Node.Operator(op)
        when (root) {
            is Node.Integer -> {
                node.left = root
                if (this.root == root) {
                    this.root = node
                }
            }
            is Node.Operator -> {
                val self = priorities.getOrElse(op) { DEFAULT_PRIORITY }
                val other = priorities.getOrElse(root.operation) { DEFAULT_PRIORITY }

                if (self >= other) {
                    node.left = root.right
                    root.right = node
                } else {
                    node.left = root
                    if (this.root == root) {
                        this.root = node
                    }
                }
            }
        }
    }

    private fun findRightmost(root: Node?): Node? {
        if (root?.right == null) {
            return root
        }

        return findRightmost(root.right)
    }

    private fun findRightmostOperator(root: Node?): Node.Operator? {
        if (root is Node.Operator) {
            return root
        }

        val either = root?.right ?: root?.left ?: return null

        return findRightmostOperator(either)
    }

    private fun traverseInOrder(root: Node?, action: (Node) -> Unit) {
        if (root == null) {
            return
        }

        traverseInOrder(root.left, action)
        action(root)
        traverseInOrder(root.right, action)
    }

    private fun pruneIfEager() {
        if (eagerPruning) {
            try {
                prune()
            } catch (e: IllegalStateException) {}
        }
    }

    private fun count(left: Node?, right: Node?): Int {
        var count = 1
        if (left != null) {
            count += count(left.left, left.right)
        }
        if (right != null) {
            count += count(right.left, right.right)
        }
        return count
    }

}
