package com.technoles.api.service

import com.technoles.api.datafetcher.exception.NotFoundCompanyException
import com.technoles.api.datafetcher.exception.NotFoundRoleException
import com.technoles.api.datafetcher.exception.NotFoundUserException
import com.technoles.api.model.Company
import com.technoles.api.model.User
import com.technoles.api.model.UserInput
import com.technoles.api.repository.CompanyRepository
import com.technoles.api.repository.RoleRepository
import com.technoles.api.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val companyRepository: CompanyRepository,
    private val roleRepository: RoleRepository,
) {

    fun findAll(): Iterable<User> = userRepository.findAll();
    fun findByFirstName(name: String): Iterable<User> = userRepository.findByFirstName(name);
    fun findById(id: Long): Optional<User> = userRepository.findById(id);

    fun getCompaniesForUser(id: Long): List<Company> {
        return companyRepository.findByUsers_IdIs(id)
    }

    fun create(
        firstName: String,
        lastName: String,
        email: String,
        phone: String,
    ): User = userRepository.save(
        User(
            firstName = firstName,
            lastName = lastName,
            email = email,
            phone = phone
        )
    )

    fun update(id: Long, user: UserInput): User {
        if (!userRepository.existsById(id))
            throw NotFoundUserException("");

        var updateUser = userRepository.findById(id).get();
        updateUser.firstName = user.firstName;
        updateUser.lastName = user.lastName;
        updateUser.email = user.email;
        updateUser.phone = user.phone;
        return userRepository.save(updateUser);
    }

    fun delete(id: Long): Boolean {
        if (!userRepository.existsById(id))
            throw NotFoundUserException("");

        userRepository.deleteById(id);
        return true;
    }

    fun addCompany(idUser: Long, idCompany: Long): User {
        if (!userRepository.existsById(idUser))
            throw NotFoundUserException("");

        if (!companyRepository.existsById(idCompany))
            throw NotFoundCompanyException("");

        val user = findById(idUser).get();
        val company = companyRepository.findById(idCompany).get();
        user.companies.add(company);
        company.users.add(user);
        companyRepository.save(company);
        return userRepository.save(user);
    }

    fun removeCompany(idUser: Long, idCompany: Long): User {
        if (!userRepository.existsById(idUser))
            throw NotFoundUserException("");

        if (!companyRepository.existsById(idCompany))
            throw NotFoundCompanyException("");

        val user = findById(idUser).get();
        val company = companyRepository.findById(idCompany).get();
        user.companies.remove(company);
        return userRepository.save(user);
    }

    fun setRole(idUser: Long, idRole: Long): User {
        if (!userRepository.existsById(idUser))
            throw NotFoundUserException("");

        val user = findById(idUser).get();
        if (!roleRepository.existsById(idRole))
            throw NotFoundRoleException("")

        user.role = roleRepository.findById(idRole).get();
        return userRepository.save(user);
    }

    fun removeRole(idUser: Long): User {
        if (!userRepository.existsById(idUser))
            throw NotFoundUserException("");

        val user = findById(idUser).get();

        user.role = null;
        return userRepository.save(user);
    }
}