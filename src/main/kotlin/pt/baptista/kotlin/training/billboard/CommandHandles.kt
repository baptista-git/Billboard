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
data class Command(
    /**
     * Performs the action of the command returning the information to present or null if it is to terminate.
     * Receive the parameter given on command line.
     * May throws exception in case of failure with appropriate error message.
     */
    val action: (String?) -> CommandResult,

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
fun buildHandlers(billboard: Billboard, author: Author) = mapOf(
    "EXIT" to Command(action = { ExitResult }),
    "POST" to Command(
        action = { ValueResult(postMessageAction(billboard, author, it)) },
        show = { println("Message \"$it\" posted by ${author.id}") }
    ),
    "GET" to Command(
        action = { ValueResult(getMessageAction(billboard, it)) },
        show = { (it as Iterable<*>).forEach(::println) }
    ),
    "USER" to Command(
        action = { ValueResult(null) },
        show = { println("Your user id is ${author.id}") }
    )
)

