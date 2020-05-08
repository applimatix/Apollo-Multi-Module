package com.example.apollomultimodule.companies.data.models

import com.example.apollomultimodule.apollo.CompanyEmployeesQuery
import com.example.apollomultimodule.base.data.models.Employee


fun Employee.Companion.fromService(companyEmployees: CompanyEmployeesQuery.Employee): Employee? =
    if (companyEmployees.fragments.subEmployee.id != null && companyEmployees.fragments.subEmployee.firstName != null && companyEmployees.fragments.subEmployee.lastName != null)
        Employee(companyEmployees.fragments.subEmployee.id, companyEmployees.fragments.subEmployee.firstName, companyEmployees.fragments.subEmployee.lastName)
    else null