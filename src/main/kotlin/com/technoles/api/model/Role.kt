package com.technoles.api.model

import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(
        @field:NotEmpty(message = "{validation.field.name.empty")
        @Column(nullable = false, unique = true)
        var name: String,

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = [CascadeType.ALL])
        var users: MutableList<User> = mutableListOf()
) : BaseEntity() {}








