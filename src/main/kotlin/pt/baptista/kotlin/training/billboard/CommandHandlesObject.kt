package pt.baptista.kotlin.training.billboard

import pt.baptista.kotlin.training.billboard.domain.*
import pt.baptista.kotlin.training.billboard.storage.Billboard

/**
 * Type of each command handler.
 * Explicitly separates the action of the command and the presentation of the result.
 * It is possible to do tests on the action, without having tests on the presentation.
 * @property action The action function.
 * @property show The show function.
 */
data class CommandHandlerObject(
    /**
     * Performs the action of the command returning the information to present or null if it is to terminate.
     * Receive the parameter given on command line.
     * May throws exception in case of failure with appropriate error message.
     */
    val action: CommandObject,

    /**
     * Displays the result returned by the action.
     * Receive the result information to show.
     */
    val show: (Any?) -> Unit = { },
)

/**
 * Build the associative map of command handlers.
 * @param billboard for each command access the billboard operations.
 * @param author The author of the messages to be posted.
 * @return The handlers map of all commands.
 */

fun buildHandlersObject(billboard: Billboard, author: Author) = mapOf(
    "EXIT" to CommandHandlerObject(action = ExitCommand(), show = {}),
    "POST" to CommandHandlerObject(
        action = PostCommand(billboard, author),
        show = { println("Message \"$it\" posted by ${author.id}") }
    ),
    "GET" to CommandHandlerObject(
        action = GetCommand(billboard),
        show = { (it as Iterable<*>).forEach(::println) }
    ),
    "USER" to CommandHandlerObject(
        action = UserCommand(author),
        show = { println("Your user id is ${author.id}") }
    )
)