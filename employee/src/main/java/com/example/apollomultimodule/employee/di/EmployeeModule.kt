package com.example.apollomultimodule.employee.di

import com.example.apollomultimodule.employee.data.service.EmployeeService
import com.example.apollomultimodule.employee.viewmodels.EmployeeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object EmployeeModule {

    private val viewModels = module {

        viewModel{ EmployeeViewModel(get()) }
    }

    private val serviceModule = module {
        single { EmployeeService() }
    }

    fun init() = loadKoinModules(listOf(viewModels, serviceModule))
}