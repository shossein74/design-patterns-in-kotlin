package com.hossein.dev.creational

data class Connection(
    val databaseName: String,
    val domain: String,
    val port: String,
) {
    fun connect() {
        println("Connecting to database")
    }

    override fun toString(): String {
        return "$domain:$port,db_name:$databaseName"
    }
}

class DatabaseConnection private constructor() {

    companion object {
        private var INSTANCE: Connection? = null

        fun build(databaseName: String, domain: String, port: String): Connection {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Connection(databaseName, domain, port).also { INSTANCE = it }
            }
        }
    }
}

fun main() {

    val database1 = DatabaseConnection.build("example_db1", "localhost", "8080")
    database1.connect()

    val database2 = DatabaseConnection.build("example_db2", "192.168.56.133", "3301")
    database2.connect()

    println(database1)
    println(database2)

}