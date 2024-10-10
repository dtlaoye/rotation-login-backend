package org.example.rotationloginbackendtwo

import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

object Database {
    private val client = KMongo.createClient()
    private val database = client.getDatabase("userDatabase")
    val userCollection = database.getCollection<User>()
}

data class User(val username: String, val password: String)