package pt.baptista.kotlin.training.billboard.storage

import com.mongodb.MongoException
import pt.baptista.kotlin.training.billboard.domain.Author
import pt.baptista.kotlin.training.billboard.domain.Message

/**
 *  Encapsulates MongoDb Exceptions in Billboard Exceptions
 */
fun <T> tryBillboardAccess(block: () -> T) =
    try {
        block()
    } catch (ex: MongoException) {
        throw BillboardAccessException("", ex)
    }

/**
 * Implements the billboard operations using a MongoDB instance.
 * @property driver to access MongoDb
 */
class BillboardMongo(val driver: MongoDriver) : Billboard {
    override fun postMessage(msg: Message) =
        tryBillboardAccess { driver.getCollection<Message>(msg.author.id).insertDocument(msg) }

    override fun getAllMessages() =
        tryBillboardAccess { driver.getAllCollections<Message>().flatMap { it.getAllDocuments() } }

    override fun getMessagesByAuthor(author: Author) =
        tryBillboardAccess { driver.getCollection<Message>(author.id).getAllDocuments() }
}