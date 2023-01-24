package pt.baptista.kotlin.training.billboard.storage

import pt.baptista.kotlin.training.billboard.domain.Author
import pt.baptista.kotlin.training.billboard.domain.Message

/**
 * Exception to be produced when the db access fails
 */
class BillboardAccessException(message: String, cause: Throwable) : Exception(message, cause)

/**
 * The billboard basic operations.
 * Contract to be implemented by any concrete database.
 */
interface Billboard {
    /**
     * Posts the [message] to the billboard
     * @param message   the message to be posted
     * @return successful (true) or unsuccessful (false)
     */
    fun postMessage(msg: Message): Boolean

    /**
     * Gets all messages posted on the billboard, regardless of their author
     * @return  the messages on the billboard
     */
    fun getAllMessages(): Iterable<Message>

    /**
     * Gets all messages posted on the billboard by [author]
     * @param [author] the author
     * @return  the messages from the given author
     */
    fun getMessagesByAuthor(author: Author): Iterable<Message>
}


