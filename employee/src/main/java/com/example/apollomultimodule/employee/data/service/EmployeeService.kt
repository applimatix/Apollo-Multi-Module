package com.example.apollomultimodule.employee.data.service

import com.apollographql.apollo.coroutines.toDeferred
import com.example.apollomultimodule.apollo.EmployeeQuery
import com.example.apollomultimodule.base.data.service.Service

class EmployeeService : Service() {

    suspend fun fetchEmployee(employeeId: String) = apolloClient.query(EmployeeQuery(employeeId)).toDeferred().await()
}