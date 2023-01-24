package pt.baptista.kotlin.training.billboard.domain

/**
 * Sum type used to represent the execution result of the existing commands
 */
sealed class CommandResult

/**
 * Result produced when the command execution determines that the application should terminate.
 * See https://kotlinlang.org/docs/object-declarations.html#object-declarations-overview
 */
object ExitResult : CommandResult()

/**
 * Result produced when the command execution yields a value
 */
class ValueResult<T>(val data: T) : CommandResult()


/**
 * Exception to be produced when the command could not be executed
 */
class CommandException(message: String) : Exception(message)


