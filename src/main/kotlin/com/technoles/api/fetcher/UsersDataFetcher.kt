package com.technoles.api.fetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
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
    fun update(id: Long, user: User) : User{
        val user = service.update(id, user);
        return user;
    }
    @DgsData(parentType = "Mutation", field = "deleteUser")
    fun delete(id: Long) : Boolean {
        service.delete(id);
        return true;
    }
}