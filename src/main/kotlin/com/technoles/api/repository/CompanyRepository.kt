package com.technoles.api.repository;

import com.technoles.api.model.Company
import com.technoles.api.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface CompanyRepository : CrudRepository<Company, Long> {

    fun findByUsers_IdIs(id: Long): List<Company>

}