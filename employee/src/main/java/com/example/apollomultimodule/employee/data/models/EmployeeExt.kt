package com.example.apollomultimodule.employee.data.models

import com.example.apollomultimodule.apollo.EmployeeQuery
import com.example.apollomultimodule.base.data.models.Company
import com.example.apollomultimodule.base.data.models.Employee


fun Employee.Companion.fromService(employee: EmployeeQuery.Employee): Employee? =
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

fun Employee.Companion.fromService(subordinate: EmployeeQuery.Subordinate): Employee? =
    if (subordinate.fragments.subEmployee.id != null && subordinate.fragments.subEmployee.firstName != null && subordinate.fragments.subEmployee.lastName != null)
        Employee(subordinate.fragments.subEmployee.id, subordinate.fragments.subEmployee.firstName, subordinate.fragments.subEmployee.lastName)
    else null