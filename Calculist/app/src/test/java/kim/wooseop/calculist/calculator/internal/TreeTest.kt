package kim.wooseop.calculist.calculator.internal

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class TreeTest {

    companion object {

        @Suppress("UNUSED")
        @JvmStatic
        @Parameterized.Parameters(name = "{5}")
        fun data() = arrayOf(
            testData(
                value = null,
                expression = null,
                size = 0,
                name = "shouldHaveNullStateAsDefault"
            ),
            testData(
                '0',
                value = 0,
                expression = "0",
                size = 1,
                name = "shouldInsertDigit"
            ),
            testData(
                '1', '2',
                expression = "12",
                value = 12,
                size = 1,
                name = "shouldConcatDigit"
            ),
            testData(
                '1', '+', '1',
                expression = "1+1",
                value = 2,
                size = 3,
                name = "shouldPerformOperation"
            ),
            testData(
                '1', '2', '+', '-', '1',
                expression = "12-1",
                value = 11,
                size = 3,
                name = "shouldChooseLastOneOnMultipleOperatorInputs"
            ),
            testData(
                '1', '+', '2', '×', '2',
                expression = "1+2×2",
                value = 5,
                size = 5,
                priorities = { it to if (it == Operation.MULTIPLY) 1 else 0 },
                name = "shouldPerformEvaluationByOperationPriorities"
            )
        )


        private fun testData(
            vararg input: Char,
            expression: String?,
            value: Int?,
            size: Int?,
            priorities: (Operation) -> Pair<Operation, Int> = { it to Tree.DEFAULT_PRIORITY },
            name: String
        ) =
            listOf(input)
                .plus(expression)
                .plus(value)
                .plus(size)
                .plus(priorities)
                .plus(name)
                .toTypedArray()

    }

    @Parameterized.Parameter(0)
    lateinit var input: CharArray

    @Parameterized.Parameter(1)
    lateinit var expression: String

    @JvmField
    @Parameterized.Parameter(2)
    var value: Int? = null

    @JvmField
    @Parameterized.Parameter(3)
    var size: Int? = null

    @Parameterized.Parameter(4)
    lateinit var priorities: (Operation) -> Pair<Operation, Int>

    @Suppress("UNUSED")
    @Parameterized.Parameter(5)
    lateinit var name: String

    @Test
    fun testExpression() {
        val tree = Tree(priorities)

        input.forEach { tree.add(it) }

        if (::expression.isInitialized) {
            assertEquals(expression, tree.makeString())
        } else {
            assertNull(tree.makeString())
        }
    }

    @Test
    fun testValue() {
        val tree = Tree(priorities)

        input.forEach { tree.add(it) }

        assertEquals(value, tree.evaluate())
    }

    @Test
    fun testSize() {
        val tree = Tree(priorities)

        input.forEach { tree.add(it) }

        assertEquals(size, tree.size())
    }

    @Test
    fun testPrune() {
        val tree = Tree(priorities)

        input.forEach { tree.add(it) }
        tree.prune()

        assertEquals(value?.run { toString() }, tree.makeString())
        assertEquals(size?.and(1), tree.size())
    }

    @Test
    fun testClear() {
        val tree = Tree(priorities)

        input.forEach { tree.add(it) }
        tree.clear()

        assertNull(tree.evaluate())
        assertNull(tree.makeString())
    }

}
