package com.example.apollomultimodule

import android.app.Application
import com.example.apollomultimodule.base.di.BaseModule
import com.example.apollomultimodule.companies.di.CompaniesModule
import com.example.apollomultimodule.employee.di.EmployeeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ApolloApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@ApolloApplication)
        }
        BaseModule.init()
        CompaniesModule.init()
        EmployeeModule.init()
    }
}