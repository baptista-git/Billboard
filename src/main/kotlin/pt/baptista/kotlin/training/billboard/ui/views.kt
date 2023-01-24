package pt.baptista.kotlin.training.billboard.ui

/**
 * Show the implemented options
 */
fun showOptions() {
    println("The application supports the following commands:")
    println("USER - Current user logged")
    println("GET - Gets all billboard messages")
    println("GET <author> - Gets all messages from <author>")
    println("POST <message_content> - Post the given message to the billboard")
    println("EXIT - Ends the application")
}