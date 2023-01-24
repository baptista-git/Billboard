package pt.baptista.kotlin.training.billboard

import pt.baptista.kotlin.training.billboard.domain.CommandException
import pt.baptista.kotlin.training.billboard.domain.ExitResult
import pt.baptista.kotlin.training.billboard.domain.ValueResult
import pt.baptista.kotlin.training.billboard.storage.BillboardAccessException
import pt.baptista.kotlin.training.billboard.storage.BillboardMongo
import pt.baptista.kotlin.training.billboard.storage.MongoDriver
import pt.baptista.kotlin.training.billboard.ui.readAuthorId
import pt.baptista.kotlin.training.billboard.ui.readCommand
import pt.baptista.kotlin.training.billboard.ui.showOptions

/**
 * The application entry point.
 *
 * The application supports the following commands:
 * GET - Gets all billboard messages
 * GET <author> - Gets all messages from <author>
 * POST <message_content> - Post the given message to the billboard
 * EXIT - Ends the application
 *
 * Execution is parameterized through the following environment variables:
 * - MONGO_DB_NAME, bearing the name of the database to be used
 * - MONGO_DB_CONNECTION, bearing the connection string to the database server. If absent, the application
 * uses a local server instance (it must be already running)
 */

fun main() {
    MongoDriver().use { driver ->
        //val handlers = buildHandlers(BillboardMongo(driver), readAuthorId())
        val handlers = buildHandlersObject(BillboardMongo(driver), readAuthorId())
        while (true) {
            val (name, parameter) = readCommand()
            val cmd = handlers[name]
            if (cmd == null) {
                println("Invalid command")
                showOptions()
            } else try {
                when (val result = cmd.action(parameter)) {
                    is ExitResult -> break
                    is ValueResult<*> -> cmd.show(result.data)
                }
            } catch (cmdEx: CommandException) {
                println("An error occurred while trying to execute the command: ${cmdEx.message}")
            } catch (boardEx: BillboardAccessException) {
                println("An error occurred while trying to reach the database: ${boardEx.message}.")
            }
        }
    }
}

