package com.technoles.api.datafetcher

import com.netflix.graphql.dgs.*
import com.technoles.api.model.Company
import com.technoles.api.service.UserService
import com.technoles.api.model.User
import com.technoles.api.model.UserInput
import java.util.*

import com.technoles.api.service.CompanyService
import graphql.schema.DataFetchingEnvironment

@DgsComponent
class UsersDataFetcher(
    private val service: UserService,
) {
    @DgsQuery(field = "users")
    fun users(): Iterable<User> = service.findAll()

    @DgsQuery(field = "user")
    fun user(id: Long): Optional<User> = service.findById(id)

    @DgsMutation(field = "createUser")
    fun create(
        firstName: String,
        lastName: String,
        phone: String,
        email: String
    ): User = service.create(
        firstName = firstName,
        lastName = lastName,
        phone = phone,
        email = email,
    )

    @DgsMutation
    fun addCompany(idUser: Long, idCompany: Long): User = service.addCompany(idUser, idCompany)

    @DgsMutation
    fun removeCompany(idUser: Long, idCompany: Long): User = service.removeCompany(idUser, idCompany)

    @DgsMutation
    fun setRole(idUser: Long, idRole: Long): User = service.setRole(idUser, idRole)

    @DgsMutation
    fun removeRole(idUser: Long): User = service.removeRole(idUser)

    @DgsMutation(field = "updateUser")
    fun update(id: Long, @InputArgument user: UserInput): User = service.update(id, user)

    @DgsMutation(field = "deleteUser")
    fun delete(id: Long): Boolean = service.delete(id)
}

