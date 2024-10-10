package org.example.rotationloginbackendtwo

data class GraphQLRequest(
    val query: String,
    val operationName: String? = null,
    val variables: Map<String, Any>? = null
)
