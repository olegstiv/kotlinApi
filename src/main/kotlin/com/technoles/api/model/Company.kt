package com.technoles.api.model

import org.hibernate.Hibernate
import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.*

@Entity
@Table(name = "companies")
class Company(
        @field:NotEmpty(message = "{validation.field.name.empty")
        @Column(nullable = false, unique = true)
        var name: String,

        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        var users: MutableList<User> = mutableListOf()
) : BaseEntity() {
}
