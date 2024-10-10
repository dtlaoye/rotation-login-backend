package org.example.rotationloginbackendtwo

import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.toSchema
import graphql.schema.GraphQLSchema

object UserSchema {
    private val config = SchemaGeneratorConfig(
        supportedPackages = listOf("org.example.rotationloginbackendtwo")
    )
    val schema: GraphQLSchema = toSchema(
        config = config,
        queries = listOf(TopLevelObject(UserQuery(UserResolver()))),
        mutations = listOf(TopLevelObject(UserMutation(UserResolver())))
    )
}