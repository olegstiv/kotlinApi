package com.technoles.api.repository

import com.technoles.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByFirstName(firstName: String): Iterable<User>
}