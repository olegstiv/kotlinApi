package com.technoles.api.model

import javax.persistence.*

@Entity
@Table(name="users")
data class User(

    @Column(nullable = false)
    var name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable =  false)
    var id: Long = 0,
)
