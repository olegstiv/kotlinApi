package com.technoles.api.service

import com.technoles.api.datafetcher.exception.NotFoundCompanyException
import com.technoles.api.datafetcher.exception.NotFoundUserException
import com.technoles.api.model.Company
import com.technoles.api.model.CompanyInput
import com.technoles.api.model.User
import com.technoles.api.repository.CompanyRepository
import com.technoles.api.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompanyService(
        val companyRepository: CompanyRepository,
        val userRepository: UserRepository
) {
    fun findAll(): Iterable<Company> = companyRepository.findAll();
    fun findById(id: Long): Optional<Company> = companyRepository.findById(id);
    fun create(name: String): Company = companyRepository.save(Company(name = name))

    fun update(id: Long, company: CompanyInput): Company {
        if (!companyRepository.existsById(id))
            throw NotFoundCompanyException("");

        val companyUpdated = companyRepository.findById(id).get();
        companyUpdated.name = company.name;
        return companyRepository.save(companyUpdated);
    }

    fun delete(id: Long): Boolean {
        if (!companyRepository.existsById(id))
            throw NotFoundCompanyException("");

        companyRepository.deleteById(id);
        return true;
    }

    fun findByUserId(userId: Long): MutableList<Company> {
        if(!userRepository.existsById(userId))
            throw NotFoundUserException("");

        return companyRepository.findByUsers_IdIs(userId) as MutableList<Company>;
    }

}
