package com.technoles.api.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
data class User(

    @Column(nullable = false)
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable =  false)
    var id: Long = 0,
)
