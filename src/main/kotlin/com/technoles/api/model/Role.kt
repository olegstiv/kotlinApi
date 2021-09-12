package com.technoles.api.model

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(
        @Column(nullable = false, unique = true)
        var name: String,

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = [CascadeType.ALL])
        var users: MutableList<User> = mutableListOf()
) : BaseEntity() {}








