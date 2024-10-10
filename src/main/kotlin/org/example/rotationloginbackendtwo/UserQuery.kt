package org.example.rotationloginbackendtwo

import com.expediagroup.graphql.server.operations.Query

class UserQuery(private val resolver: UserResolver) : Query {
    fun login(username: String, password: String): Boolean {
        return resolver.login(username, password)
    }
}

