package com.technoles.api.datafetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.technoles.api.model.Company
import com.technoles.api.model.Role
import com.technoles.api.model.RoleInput
import com.technoles.api.service.RoleService
import java.util.*

@DgsComponent
class RolesDataFetcher(
        private val service: RoleService
) {
    @DgsQuery(field = "roles")
    fun roles(): Iterable<Role> = service.findAll()

    @DgsQuery(field = "role")
    fun role(id: Long): Optional<Role> = service.findById(id)

    @DgsMutation(field = "createRole")
    fun create(name: String): Role = service.create(name = name);

    @DgsMutation(field = "updateRole")
    fun update(id: Long, @InputArgument role: RoleInput): Role = service.update(id, role);

    @DgsMutation(field = "deleteRole")
    fun delete(id: Long): Boolean = service.delete(id);
}