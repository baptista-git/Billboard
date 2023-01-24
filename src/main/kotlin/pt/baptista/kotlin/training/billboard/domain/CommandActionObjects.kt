package pt.baptista.kotlin.training.billboard.domain

import pt.baptista.kotlin.training.billboard.storage.Billboard

/**
 * Contract to be supported by all commands (an Object-Oriented style)
 */
fun interface CommandObject {
    /**
     * Executes this command passing it the given parameter
     * @param parameter the command parameter, or null, if no parameter has been passed
     */
    fun execute(parameter: String?): CommandResult

    /**
     * Overload of invoke operator, for convenience.
     */
    operator fun invoke(parameter: String? = null) = execute(parameter)
}

/**
 * Implementation of the EXIT command
 */
class ExitCommand : CommandObject {
    override fun execute(parameter: String?) = ExitResult
}

/**
 * Implementation of the POST command
 * @param billboard the [Billboard] instance to be used
 * @param author    the post author
 */
class PostCommand(
    private val billboard: Billboard,
    private val author: Author,
) : CommandObject {

    override fun execute(parameter: String?): ValueResult<Boolean> {
        if (parameter == null) throw CommandException("Missing content")
        return ValueResult(billboard.postMessage(Message(author, parameter)))
    }
}

/**
 * Implementation of the GET command
 * @param billboard the [Billboard] instance to be used
 */
class GetCommand(private val billboard: Billboard) : CommandObject {
    override fun execute(parameter: String?): ValueResult<Iterable<Message>> = ValueResult(
        if (parameter != null) billboard.getMessagesByAuthor(Author(parameter))
        else billboard.getAllMessages()
    )
}

//*********************************************************************************************************************

/**
 * Implementation of the USER command
 * @param user the logged-in user
 */
class UserCommand(private val user: Author) : CommandObject {
    override fun execute(parameter: String?): ValueResult<String> = ValueResult(user.id)
}

/**
 * Used to add logging information to the commands execution
 * @param decorated the command whose execution is to be logged
 */
class LogExecutionTime(private val decorated: CommandObject) : CommandObject {
    override fun execute(parameter: String?): CommandResult {
        val start = System.currentTimeMillis()
        val result = decorated(parameter)
        println("Executed in ${System.currentTimeMillis() - start} ms")
        return result
    }
}