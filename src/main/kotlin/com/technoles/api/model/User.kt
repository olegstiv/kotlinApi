package com.technoles.api.model

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
        @Column(nullable = false)
        var firstName: String,

        @Column(nullable = false)
        var lastName: String,

        @Column(nullable = false, unique = true)
        var phone: String,

        @Column(nullable = false, unique = true)
        var email: String,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "role_id", referencedColumnName = "id")
        var role: Role? = null,

        @ManyToMany(mappedBy = "users", cascade = [CascadeType.ALL])
        var companies: MutableList<Company> = mutableListOf()
) : BaseEntity() {
}
