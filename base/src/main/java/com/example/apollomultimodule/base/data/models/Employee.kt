package com.example.apollomultimodule.base.data.models

import com.example.apollomultimodule.apollo.CompanyEmployeesQuery
import com.example.apollomultimodule.apollo.EmployeeQuery


data class Employee(
    val id: String,
    val firstName: String,
    val lastName: String,
    val address: String = "",
    val subordinates: List<Employee> = listOf(),
    val company: Company? = null
) {
    companion object {
        fun fromService(companyEmployees: CompanyEmployeesQuery.Employee): Employee? =
            if (companyEmployees.fragments.subEmployee.id != null && companyEmployees.fragments.subEmployee.firstName != null && companyEmployees.fragments.subEmployee.lastName != null)
                Employee(companyEmployees.fragments.subEmployee.id, companyEmployees.fragments.subEmployee.firstName, companyEmployees.fragments.subEmployee.lastName)
            else null

        fun fromService(employee: EmployeeQuery.Employee): Employee? =
            if (employee.fragments.subEmployee.id != null && employee.fragments.subEmployee.firstName != null && employee.fragments.subEmployee.lastName != null)
                Employee(
                    employee.fragments.subEmployee.id,
                    employee.fragments.subEmployee.firstName,
                    employee.fragments.subEmployee.lastName,
                    employee.address ?: "",
                    employee.subordinates?.mapNotNull { subordinate -> fromService(subordinate) }
                        ?: listOf(),
                    Company.fromService(employee.company))
            else null

        fun fromService(subordinate: EmployeeQuery.Subordinate): Employee? =
            if (subordinate.fragments.subEmployee.id != null && subordinate.fragments.subEmployee.firstName != null && subordinate.fragments.subEmployee.lastName != null)
                Employee(subordinate.fragments.subEmployee.id, subordinate.fragments.subEmployee.firstName, subordinate.fragments.subEmployee.lastName)
            else null
    }
}