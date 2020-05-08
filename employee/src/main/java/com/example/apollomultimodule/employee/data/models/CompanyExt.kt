package com.example.apollomultimodule.employee.data.models

import com.example.apollomultimodule.apollo.EmployeeQuery
import com.example.apollomultimodule.base.data.models.Company

fun Company.Companion.fromService(empCompany: EmployeeQuery.Company?): Company? = empCompany?.let {
    if (empCompany.fragments.subCompany.id != null && empCompany.fragments.subCompany.name != null && empCompany.fragments.subCompany.industry != null)
        Company(
            empCompany.fragments.subCompany.id,
            empCompany.fragments.subCompany.name,
            empCompany.fragments.subCompany.industry
        )
    else null
}