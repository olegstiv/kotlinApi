package com.technoles.api.fetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import com.technoles.api.service.UserService
import com.technoles.api.model.User
import java.util.*

@DgsComponent
class UsersDataFetcher(
    private val service: UserService
) {
    @DgsData(parentType = "Query", field = "users")
    fun users() : Iterable<User>{
        val users = service.findAll();
        return users;
    }
    @DgsData(parentType = "Query", field = "users")
    fun user(id: Long) : Optional<User> {
        val user = service.findById(id);
        return user;
    }


    @DgsData(parentType = "Mutation", field = "createUser")
    fun create(name: String) : User{
        val user = service.create(name);
        return user;
    }

    @DgsData(parentType = "Mutation", field = "updateUser")
    fun update(user: User) : User{
        val updateUser = service.update(user);
        return updateUser;
    }
    @DgsData(parentType = "Mutation", field = "deleteUser")
    fun delete(id: Long) : Boolean {
        return service.delete(id);
    }
}