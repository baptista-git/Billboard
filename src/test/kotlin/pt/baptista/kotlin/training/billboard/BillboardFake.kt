package pt.baptista.kotlin.training.billboard

import pt.baptista.kotlin.training.billboard.domain.Author
import pt.baptista.kotlin.training.billboard.domain.Message
import pt.baptista.kotlin.training.billboard.storage.Billboard

/**
 * Implements the billboard operations using a MutableMap in memory.
 * This implementation must be used in unit tests of commands.
 */
class BillboardFake : Billboard {
    // Internal mutable map using author id as the key.
    val data = mutableMapOf<Author, Iterable<Message>>()

    // Control the success of postMessage.
    var fail = false

    override fun postMessage(msg: Message): Boolean {
        if (fail) return false
        val msgs = data[msg.author]
        data[msg.author] = if (msgs == null) mutableListOf(msg) else msgs + msg
        return true
    }

    override fun getAllMessages() = data.values.flatten()

    override fun getMessagesByAuthor(author: Author) = data[author] ?: emptyList()
}