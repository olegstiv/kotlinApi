package com.technoles.api.service

import com.technoles.api.datafetcher.exception.NotFoundRoleException
import com.technoles.api.datafetcher.exception.NotFoundUserException
import com.technoles.api.model.Role
import com.technoles.api.model.RoleInput
import com.technoles.api.repository.RoleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoleService(
        val roleRepository: RoleRepository
) {
    fun findAll(): Iterable<Role> = roleRepository.findAll();
    fun findById(id: Long): Optional<Role> = roleRepository.findById(id);
    fun create(name: String): Role =  roleRepository.save(Role(name = name))

    fun update(id: Long, role: RoleInput): Role {
        if (!roleRepository.existsById(id))
            throw NotFoundRoleException("");

        val roleUpdated = roleRepository.findById(id).get();
        roleUpdated.name = role.name;
        return roleRepository.save(roleUpdated);
    }

    fun delete(id: Long): Boolean {
        if (!roleRepository.existsById(id))
            throw NotFoundRoleException("");

        roleRepository.deleteById(id);
        return true;
    }
}