package com.example.apollomultimodule.base.data.models

import com.example.apollomultimodule.apollo.AllCompaniesQuery
import com.example.apollomultimodule.apollo.EmployeeQuery


data class Company(val id: String, val name: String, val industry: String, val employees: List<Employee> = listOf()) {

    companion object {
        fun fromService(allCompany: AllCompaniesQuery.AllCompany): Company? =
            if (allCompany.fragments.subCompany.id != null && allCompany.fragments.subCompany.name != null && allCompany.fragments.subCompany.industry != null)
                Company(allCompany.fragments.subCompany.id, allCompany.fragments.subCompany.name, allCompany.fragments.subCompany.industry)
            else null

        fun fromService(empCompany: EmployeeQuery.Company?): Company? = empCompany?.let {
            if (empCompany.fragments.subCompany.id != null && empCompany.fragments.subCompany.name != null && empCompany.fragments.subCompany.industry != null)
                Company(empCompany.fragments.subCompany.id, empCompany.fragments.subCompany.name, empCompany.fragments.subCompany.industry)
            else null
        }
    }
}