package pt.baptista.kotlin.training.billboard.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

const val BLANK_MESSAGE = " \t\n  \t "
class MessageTests {

    @Test
    fun `message contains some characters beyond of whitespace characters works`() {
        Message(Author("owner"), "a\tb\nc d")
    }

    @Test
    fun `instantiation of message with blank spaces throws`() {
        assertFailsWith<IllegalArgumentException> {
            Message(Author("owner"), BLANK_MESSAGE)
        }
    }
}