package com.example.apollomultimodule.companies.di

import com.example.apollomultimodule.companies.data.service.CompaniesService
import com.example.apollomultimodule.companies.viewmodels.CompanyListViewModel
import com.example.apollomultimodule.companies.viewmodels.EmployeeListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object CompaniesModule {

    private val viewModels = module {

        viewModel{ CompanyListViewModel(get()) }
        viewModel{ EmployeeListViewModel(get()) }
    }

    private val serviceModule = module {
        single { CompaniesService() }
    }

    fun init() = loadKoinModules(listOf(viewModels, serviceModule))
}