package com.technoles.api.model

import javax.persistence.*

@Entity
@Table(name = "companies")
data class Company(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false, unique = true)
        var name: String,

        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        var users: MutableList<User> = mutableListOf()

)
