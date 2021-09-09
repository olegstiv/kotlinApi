package com.technoles.api.resolver

import org.springframework.stereotype.Component

import com.technoles.api.model.User
import com.technoles.api.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserQueryResolver(private val repository: UserRepository) {

    fun addWidget(description: String): Widget {
        val newWidget: User = User(description)
        repository.save(newWidget)
        return User.fromDAO(newUser)
    }

    fun getUserById(id: Long): User? {
        val widget: Optional<User> = repository.findById(id)
        return if (widget.isPresent) User.fromDAO(widget.get()) else null
    }
}