package pt.baptista.kotlin.training.billboard.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith


const val BLANK_AUTHOR = " \t\n  \t "

class AuthorTests {

    @Test
    fun `instantiation of Author with blank string throws`() {
        assertFailsWith<IllegalArgumentException> {
            Author(BLANK_AUTHOR)
        }
    }

    @Test
    fun `instantiation of Author with blank spaces throws`() {
        assertFailsWith<IllegalArgumentException> {
            Author(" invalid user id")
        }
    }

    @Test
    fun `instantiation of Author with tabs and newlines throws`() {
        assertFailsWith<IllegalArgumentException> {
            Author("invalid\nuser\tid")
        }
    }

    @Test
    fun `instantiation of Author without blank characters works`() {
        Author("the_user_id")
    }

    @Test
    fun `toAuthor on blank string throws`() {
        assertFailsWith<IllegalArgumentException> {
            BLANK_AUTHOR.toAuthor()
        }
    }
}