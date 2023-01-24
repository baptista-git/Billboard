package pt.baptista.kotlin.training.billboard.domain

/**
 * Represents billboard messages
 * @property author   message author
 * @property content  message content
 */
data class Message(val author: Author, val content: String) {
    init {
        require(content.isNotBlank())
    }

    override fun toString() = "${author.id}: $content"
}

/**
 * Represents the information of billboard message authors.
 * @param  id  the author identifier.
 * Blank characters are not allowed.
 */
data class Author(val id: String) {
    init {
        require(isValidAuthorId(id))
    }
}

/**
 * Converts this string to an [Author] instance.
 * @return  the [Author] instance
 * @throws  IllegalArgumentException if this string is not a valid author identifier.
 */
fun String.toAuthor() = Author(this)

/**
 * Converts this string to an [Author] instance.
 * @return  the [Author] instance or null if this string is not a valid author identifier.
 */
fun String.toAuthorOrNull() = if (isValidAuthorId(this)) Author(this) else null

/**
 * Checks whether the given string is a valid author identifier.
 * @param   id  the string to be checked
 * @return  true if [id] can be used as an author identifier, false otherwise
 */
fun isValidAuthorId(id: String) = id.isNotEmpty() && id.all { letter -> !letter.isWhitespace() }

