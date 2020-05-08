package com.example.apollomultimodule.base.navigation

import android.app.Application
import android.content.Intent
import org.koin.core.KoinComponent
import org.koin.core.inject

class Navigation : KoinComponent {

    private val application: Application by inject()

    fun intentTo(destination: Destination): Intent {
        return Intent(Intent.ACTION_VIEW)
            .setClassName(
                application.packageName,
                destination.className
            )
    }

    interface Destination {
        val className: String

        object EmployeeList : Destination {
            override val className =
                "com.example.apollomultimodule.companies.views.EmployeeListActivity"

            const val EXTRA_COMPANY_ID = "extra_company_id"
        }

        object Employee : Destination {
            override val className = "com.example.apollomultimodule.employee.views.EmployeeActivity"

            const val EXTRA_EMPLOYEE_ID = "extra_employee_id"
        }
    }
}