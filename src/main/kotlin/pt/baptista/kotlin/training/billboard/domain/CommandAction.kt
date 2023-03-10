package pt.baptista.kotlin.training.billboard.domain

import pt.baptista.kotlin.training.billboard.storage.Billboard

/**
 * Returns messages from billboard posted by the specified author or by all authors.
 * QUESTION: Can throw exceptions?
 * @param billboard to access Billboard operations
 * @param authorId the author of messages or null for all authors
 */
fun getMessageAction(billboard: Billboard, authorId: String?) =
    if (authorId != null) billboard.getMessagesByAuthor(Author(authorId))
    else billboard.getAllMessages()

/**
 * Post a message by one author to the billboard in MongoDb.
 * @param billboard to access Billboard operations
 * @param author the author of message
 * @param content the content of message, cannot be null.
 * @throws IllegalArgumentException if there is no content.
 * @throws IllegalStateException if post failed
 */
fun postMessageAction(billboard: Billboard, author: Author, content: String?): String {
    if (content == null) throw CommandException("Missing content")
    billboard.postMessage(Message(author, content))
    return content
}

/**
 * Used to add logging information to the commands execution
 * @param decorated the command whose execution is to be logged
 */
fun log(decorated: (String?) -> CommandResult): (String?) -> CommandResult = {
    val start = System.currentTimeMillis()
    val result = decorated(it)
    println("Executed in ${System.currentTimeMillis() - start} ms")
    result
}
