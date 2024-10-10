package org.example.rotationloginbackendtwo

import com.expediagroup.graphql.generator.toSchema
import graphql.ExecutionInput
import graphql.GraphQL
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.defaultheaders.DefaultHeaders
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.serialization.kotlinx.json.json
import io.ktor.http.HttpStatusCode

fun main() {
    // Create the GraphQL schema using the UserSchema object
    val graphQL = GraphQL.newGraphQL(UserSchema.schema).build()

    embeddedServer(Netty, port = 8080) {
        install(DefaultHeaders)
        install(ContentNegotiation) {
            json()
        }
        install(StatusPages) {
            exception<Throwable> { call, cause ->
                call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)
            }
        }

        routing {
            post("/graphql") {
                // Parse the incoming GraphQL request using the data class
                val request = call.receive<GraphQLRequest>()

                // Create an ExecutionInput for GraphQL processing
                val executionInput = ExecutionInput.newExecutionInput()
                    .query(request.query)
                    .variables(request.variables ?: emptyMap())
                    .operationName(request.operationName)
                    .build()

                // Execute the GraphQL query
                val result = graphQL.execute(executionInput)
                call.respond(result.toSpecification())
            }
        }
    }.start(wait = true)
    println("Server is running...")
}