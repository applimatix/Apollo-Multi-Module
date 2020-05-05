package com.example.apollomultimodule.data.models

import com.example.CompanyEmployeesQuery
import com.example.EmployeeQuery

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
            if (companyEmployees.id != null && companyEmployees.firstName != null && companyEmployees.lastName != null)
                Employee(companyEmployees.id, companyEmployees.firstName, companyEmployees.lastName)
            else null

        fun fromService(employee: EmployeeQuery.Employee): Employee? =
            if (employee.id != null && employee.firstName != null && employee.lastName != null)
                Employee(
                    employee.id,
                    employee.firstName,
                    employee.lastName,
                    employee.address ?: "",
                    employee.subordinates?.mapNotNull { subordinate -> fromService(subordinate) }
                        ?: listOf(),
                    Company.fromService(employee.company))
            else null

        fun fromService(subordinate: EmployeeQuery.Subordinate): Employee? =
            if (subordinate.id != null && subordinate.firstName != null && subordinate.lastName != null)
                Employee(subordinate.id, subordinate.firstName, subordinate.lastName)
            else null
    }
}