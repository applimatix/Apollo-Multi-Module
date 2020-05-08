package com.example.apollomultimodule.companies.data.service

import com.apollographql.apollo.coroutines.toDeferred
import com.example.apollomultimodule.apollo.AllCompaniesQuery
import com.example.apollomultimodule.apollo.CompanyEmployeesQuery
import com.example.apollomultimodule.base.data.service.Service

class CompaniesService : Service() {

    suspend fun fetchCompanies() = apolloClient.query(AllCompaniesQuery()).toDeferred().await()

    suspend fun fetchCompanyEmployees(companyId: String) = apolloClient.query(CompanyEmployeesQuery(companyId)).toDeferred().await()
}