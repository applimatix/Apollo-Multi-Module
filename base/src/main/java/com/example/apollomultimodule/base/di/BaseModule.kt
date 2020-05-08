package com.example.apollomultimodule.base.di

import com.apollographql.apollo.ApolloClient
import com.example.apollomultimodule.base.navigation.Navigation
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object BaseModule {

    private val baseModule = module {

        single {
            ApolloClient.builder()
                .serverUrl("http://localhost:9002/graphql")
                .build()
        }

        single { Navigation() }
    }

    fun init() = loadKoinModules(baseModule)
}