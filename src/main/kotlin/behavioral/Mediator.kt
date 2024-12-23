package com.hossein.dev.behavioral

interface ChatMediator {
    fun sendMessage(message: String, user: User)
    fun addUser(user: User)
}

abstract class User(protected val chatMediator: ChatMediator, val name: String) {
    abstract fun send(message: String)
    abstract fun receive(message: String)
}

class ChatRoom : ChatMediator {
    private val users = mutableListOf<User>()

    override fun sendMessage(message: String, user: User) {
        users.filter { it != user }.forEach {
            it.receive(message)
        }
    }

    override fun addUser(user: User) {
        users.add(user)
    }
}

class ChatUser(
    mediator: ChatMediator,
    name: String,
) : User(mediator, name) {

    override fun send(message: String) {
        println("$name sent: $message")
        chatMediator.sendMessage(message, this)
    }

    override fun receive(message: String) {
        println("$name received: $message")
    }

}

fun main() {
    val chatRoom = ChatRoom()

    val user1 = ChatUser(chatRoom, "Hossein")
    val user2 = ChatUser(chatRoom, "Lionel")
    val user3 = ChatUser(chatRoom, "Andres")

    chatRoom.addUser(user1)
    chatRoom.addUser(user2)

    user1.send("Hi Every one!!")
    user2.send("Hi. How are you?")

    chatRoom.addUser(user3)
    user3.send("Hi every body. I'm new")
}