package com.technoles.api.datafetcher.exception

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler
import com.netflix.graphql.types.errors.TypedGraphQLError
import graphql.GraphQLError
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import org.springframework.stereotype.Component

@Component
class CustomFetchingExceptionHandler : DataFetcherExceptionHandler {
    private val defaultHandler = DefaultDataFetcherExceptionHandler()
    override fun onException(handlerParameters: DataFetcherExceptionHandlerParameters): DataFetcherExceptionHandlerResult {
        return if (
            handlerParameters.exception is NotFoundRoleException ||
            handlerParameters.exception is NotFoundUserException ||
            handlerParameters.exception is NotFoundCompanyException
        ) {
            val debugInfo: MutableMap<String, Any> = HashMap()
            val graphqlError: GraphQLError = TypedGraphQLError.INTERNAL.message("Not found value for id")
                .debugInfo(debugInfo)
                .path(handlerParameters.path).build()
            DataFetcherExceptionHandlerResult.newResult()
                .error(graphqlError)
                .build()
        } else {
            defaultHandler.onException(handlerParameters)
        }
    }
}
