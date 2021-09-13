package com.technoles.api.datafetcher.exception

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler
import com.netflix.graphql.types.errors.TypedGraphQLError
import graphql.GraphQLError
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import org.springframework.stereotype.Component
import javax.validation.ConstraintViolationException

@Component
class CustomFetchingExceptionHandler : DataFetcherExceptionHandler {
    private val defaultHandler = DefaultDataFetcherExceptionHandler()
    override fun onException(handlerParameters: DataFetcherExceptionHandlerParameters): DataFetcherExceptionHandlerResult {
        val debugInfo: MutableMap<String, Any> = HashMap()
        val message = when (handlerParameters.exception) {
            is ConstraintViolationException -> {
                var msg: String = "";
                (handlerParameters.exception as ConstraintViolationException).constraintViolations.forEach() {
                    debugInfo[it.propertyPath.toString()] = it.messageTemplate
                    msg += (it.messageTemplate + "\n")
                    println(it)
                }
                msg
            }
            else -> {
                handlerParameters.exception.message
            }
        }
        return when (handlerParameters.exception) {
            is NotFoundRoleException, is NotFoundUserException, is NotFoundCompanyException, is ConstraintViolationException -> {
                val graphqlError: GraphQLError = TypedGraphQLError.newInternalErrorBuilder()
                        .message(message)
                        .debugInfo(debugInfo)
                        .path(handlerParameters.path).build()

                DataFetcherExceptionHandlerResult.newResult()
                        .error(graphqlError)
                        .build()
            }
            else -> {
                defaultHandler.onException(handlerParameters)
            }
        }
    }
}
