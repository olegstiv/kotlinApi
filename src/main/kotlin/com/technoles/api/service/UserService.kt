package com.technoles.api.service

import com.technoles.api.model.User
import com.technoles.api.repository.UserRepository

class UserService(
    private val repository: UserRepository
) {
    fun create(name: String!) : User {
        return User(name).also { 
            repository.save(it)
         }
    }

    fun findByNamy(name: String) : Iterable<User> = repository.findByName(name)
}