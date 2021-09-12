package com.technoles.api.model

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "companies")
class Company(

        @Column(nullable = false, unique = true)
        var name: String,

        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        var users: MutableList<User> = mutableListOf()

) : BaseEntity() {
}
