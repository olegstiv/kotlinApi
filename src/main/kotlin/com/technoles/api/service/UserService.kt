package com.technoles.api.service

import com.technoles.api.model.User
import com.technoles.api.model.UserInput
import com.technoles.api.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val repository: UserRepository
) {
    fun create(name: String) : User {
        return User(name = name).also { 
            repository.save(it)
         }
    }

    fun update(id: Long, user: UserInput): User {
        var updateUser = repository.findById(id).get();
        updateUser.name = user.name;
        return repository.save(updateUser);
    }

    fun delete(id: Long) : Boolean {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    fun findAll() : Iterable<User> = repository.findAll();
    fun findByName(name: String) : Iterable<User> = repository.findByName(name);
    fun findById(id: Long) : Optional<User> = repository.findById(id);
    
}