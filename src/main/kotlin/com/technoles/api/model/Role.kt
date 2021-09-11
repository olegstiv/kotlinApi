package com.technoles.api.model

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false, unique = true)
        var name: String,

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = [CascadeType.ALL])
        var users: MutableList<User> = mutableListOf()
)
