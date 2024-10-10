package org.example.rotationloginbackendtwo

import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.mindrot.jbcrypt.BCrypt

class UserResolver {

    // Method to handle user login
    fun login(username: String, password: String): Boolean {
        val user = Database.userCollection.findOne(User::username eq username)
        return user != null && BCrypt.checkpw(password, user.password)
    }

    // Method to handle user registration
    fun register(username: String, password: String): Boolean {
        val existingUser = Database.userCollection.findOne(User::username eq username)
        if (existingUser != null) {
            // Username already exists, registration fails
            return false
        }

        val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
        val newUser = User(username, hashedPassword)
        Database.userCollection.insertOne(newUser)
        return true
    }
}