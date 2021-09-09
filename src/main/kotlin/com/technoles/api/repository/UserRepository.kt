package com.technoles.api.repository

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

import com.technoles.api.model.User

@Repository
interface UserRepository : CrudRepository<User, Long>{
    
    fun findByName(name: String): Iterable<User>

}