package pt.baptista.kotlin.training.billboard.storage

import org.junit.jupiter.api.Test
import pt.baptista.kotlin.training.billboard.BillboardFake
import pt.baptista.kotlin.training.billboard.domain.Author
import pt.baptista.kotlin.training.billboard.domain.Message
import kotlin.test.assertEquals

class InterfaceBillboardTests {

    @Test
    fun `getMessagesByAuthor from an author returns his messages`() {
        val billboardStub = BillboardFake()
        val author = Author("testAuthor")
        billboardStub.postMessage(Message(author, "Test Message"))
        billboardStub.postMessage(Message(Author("other"), "Other Test Message"))

        val messages = billboardStub.getMessagesByAuthor(author)
        assertEquals(1, messages.toList().size)
    }

    @Test
    fun `getMessagesByAuthor from an unknown author returns no messages`() {
        val billboardStub = BillboardFake()
        billboardStub.postMessage(Message(Author("testAuthor"), "Test Message"))
        billboardStub.postMessage(Message(Author("other"), "Other Test Message"))

        val messages = billboardStub.getMessagesByAuthor(Author("unknown"))
        assertEquals(0, messages.toList().size)
    }

    @Test
    fun `getAllMessages returns all messages in database`() {
        val billboardStub = BillboardFake()
        billboardStub.postMessage(Message(Author("testAuthor"), "Test Message"))
        billboardStub.postMessage(Message(Author("other"), "Other Test Message"))

        val messages = billboardStub.getAllMessages()
        assertEquals(2, messages.toList().size)
    }
}