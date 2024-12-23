package com.hossein.dev.behavioral

interface IObserver {
    fun updates(message: String)
}

class Classroom(
    private val observers: MutableList<IObserver> = mutableListOf()
) {

    fun addObserver(observer: IObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: IObserver): Boolean {
        return observers.remove(observer)
    }

    fun sendMessage(message: String) {
        observers.forEach {
            it.updates(message)
        }
    }
}

class Student(val username: String): IObserver {
    override fun updates(message: String) {
        println("$username received message: $message")
    }
}

fun main() {
    val classroom = Classroom()

    val student1 = Student("Hossein")
    val student2 = Student("Jack")
    val student3 = Student("John")
    val student4 = Student("Tom")

    classroom.addObserver(student1)
    classroom.addObserver(student2)
    classroom.addObserver(student4)

    classroom.sendMessage("Hi every one :)")
    classroom.addObserver(student3)
    classroom.sendMessage("Say hello to our new student. Welcome ${student3.username} :))")

    val result = classroom.removeObserver(student4)
    if (result) {
        classroom.sendMessage("${student4.username} left the classroom :(")
    }

}