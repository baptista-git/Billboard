package pt.baptista.kotlin.training.billboard.ui

import pt.baptista.kotlin.training.billboard.domain.Author
import pt.baptista.kotlin.training.billboard.domain.toAuthorOrNull

/**
 * Command line after is parsed.
 * first: name of command in uppercase.
 * second: optional parameter (one or more words)
 */
typealias LineCommand = Pair<String, String?>

/**
 * Reads and parses a command line after write the prompt.
 * @return command parsed
 */
fun readCommand(): LineCommand {
    print("> ")
    return readln().parseCommand()
}

/**
 * Parses a string to extract a command.
 * @return command parsed.
 */
fun String.parseCommand(): LineCommand {
    val line = this.trim()
    val cmd = line.substringBefore(' ').uppercase()
    val param = line.substringAfter(' ', "").ifBlank { null }
    return cmd to param
}

/**
 * Requests the author info to be used when posting billboard messages.
 * @return  the author information
 */
fun readAuthorId(): Author {
    while (true) {
        print("Please provide your user id:")
        readln().toAuthorOrNull()?.apply { return this }
        println("Author cannot contain whitespace characters.")
    }
}

