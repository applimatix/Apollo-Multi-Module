package com.example.apollomultimodule.employee.data.models

import com.example.apollomultimodule.apollo.EmployeeQuery
import com.example.apollomultimodule.base.data.models.Company

fun Company.Companion.fromService(empCompany: EmployeeQuery.Company?): Company? = empCompany?.let {
    if (it.fragments.subCompany.id != null && it.fragments.subCompany.name != null && it.fragments.subCompany.industry != null)
        Company(
            it.fragments.subCompany.id!!,
            it.fragments.subCompany.name!!,
            it.fragments.subCompany.industry!!
        )
    else null
}