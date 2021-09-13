package com.technoles.api.model

import org.hibernate.Hibernate
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank
import javax.persistence.*
import javax.validation.constraints.Pattern

const val VALID_PHONE_PHONE_NUMBER_REGEX: String = "^\\+?79\\d{9}\$"
const val VALID_EMAIL_ADDRESS_REGEX: String =
        "(^\\s+$)|([a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)"


@Entity
@Table(name = "users")
class User(
        @Column(nullable = false)
        @field:NotBlank(message = "{validation.field.firstName.blank}")
        var firstName: String,

        @Column(nullable = false)
        @field:NotBlank(message = "{validation.field.lastName.blank}")
        var lastName: String,

        @field:Pattern(
                message = "{validation.field.phone.invalidFormat}",
                regexp = VALID_PHONE_PHONE_NUMBER_REGEX
        )
        @field:NotBlank(message = "{validation.field.phone.blank}")
        @Column(nullable = false, unique = true)
        var phone: String,

        @field:Email(
                message = "{validation.field.email.invalidFormat}",
                regexp = VALID_EMAIL_ADDRESS_REGEX
        )
        @field:NotBlank(message = "{validation.field.email.blank}")
        @Column(nullable = false, unique = true)
        var email: String,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "role_id", referencedColumnName = "id")
        var role: Role? = null,

        @ManyToMany(mappedBy = "users", cascade = [CascadeType.ALL])
        var companies: MutableList<Company> = mutableListOf()
) : BaseEntity() {
}
