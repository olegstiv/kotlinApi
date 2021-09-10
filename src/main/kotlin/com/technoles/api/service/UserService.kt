package com.technoles.api.service

import com.technoles.api.model.User
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

    fun update(user: User): User {
        println(user);
        return repository.save(user.copy(name = user.name))
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