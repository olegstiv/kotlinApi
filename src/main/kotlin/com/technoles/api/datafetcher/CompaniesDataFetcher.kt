package com.technoles.api.datafetcher

import com.netflix.graphql.dgs.*
import com.technoles.api.model.Company
import com.technoles.api.model.CompanyInput
import com.technoles.api.model.User
import com.technoles.api.service.CompanyService
import com.technoles.api.service.UserService
import graphql.schema.DataFetchingEnvironment
import java.util.*

@DgsComponent
class CompaniesDataFetcher(
    private val service: CompanyService,
) {
    @DgsQuery()
    fun company(id: Long): Optional<Company> = service.findById(id)

    @DgsMutation(field = "createCompany")
    fun create(name: String): Company = service.create(name = name)

    @DgsMutation(field = "updateCompany")
    fun update(id: Long, @InputArgument company: CompanyInput): Company = service.update(id, company)

    @DgsMutation(field = "deleteCompany")
    fun delete(id: Long): Boolean = service.delete(id)
}