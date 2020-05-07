package com.example.apollomultimodule.base.data.service

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.example.apollomultimodule.apollo.AllCompaniesQuery
import com.example.apollomultimodule.apollo.CompanyEmployeesQuery
import com.example.apollomultimodule.apollo.EmployeeQuery

class CompaniesService(private val apolloClient: ApolloClient) {

    suspend fun fetchCompanies() = apolloClient.query(AllCompaniesQuery()).toDeferred().await()

    suspend fun fetchCompanyEmployees(companyId: String) = apolloClient.query(CompanyEmployeesQuery(companyId)).toDeferred().await()

    suspend fun fetchEmployee(employeeId: String) = apolloClient.query(EmployeeQuery(employeeId)).toDeferred().await()
}