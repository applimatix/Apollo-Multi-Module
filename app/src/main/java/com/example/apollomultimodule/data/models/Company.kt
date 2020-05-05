package com.example.apollomultimodule.data.models

import com.example.AllCompaniesQuery
import com.example.EmployeeQuery

data class Company(val id: String, val name: String, val industry: String, val employees: List<Employee> = listOf()) {

    companion object {
        fun fromService(allCompany: AllCompaniesQuery.AllCompany): Company? =
            if (allCompany.id != null && allCompany.name != null && allCompany.industry != null)
                Company(allCompany.id, allCompany.name, allCompany.industry)
            else null

        fun fromService(empCompany: EmployeeQuery.Company?): Company? = empCompany?.let {
            if (empCompany.id != null && empCompany.name != null && empCompany.industry != null)
                Company(empCompany.id, empCompany.name, empCompany.industry)
            else null
        }
    }
}