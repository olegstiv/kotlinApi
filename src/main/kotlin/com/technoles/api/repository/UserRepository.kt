package com.technoles.api.repository

import com.technoles.api.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByFirstName(firstName: String): Iterable<User>
}