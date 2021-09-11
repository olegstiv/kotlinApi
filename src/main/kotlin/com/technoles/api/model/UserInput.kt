package com.technoles.api.model

data class UserInput(
    var firstName: String,
    var lastName: String,
    var phone: String,
    var email: String,
    var companies: MutableList<Company>,
    var role: Role?
    ) {
}
