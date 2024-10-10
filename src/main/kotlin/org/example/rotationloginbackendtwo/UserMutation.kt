package org.example.rotationloginbackendtwo

import com.expediagroup.graphql.server.operations.Mutation

class UserMutation(private val resolver: UserResolver) : Mutation {
    fun register(username: String, password: String): Boolean {
        return resolver.register(username, password)
    }
}
