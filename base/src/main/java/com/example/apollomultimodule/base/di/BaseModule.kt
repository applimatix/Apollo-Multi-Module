package com.example.apollomultimodule.base.di

import com.apollographql.apollo.ApolloClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object BaseModule {

    private val serviceModule = module {

        single {
            ApolloClient.builder()
                .serverUrl("http://localhost:9002/graphql")
                .build()
        }
    }

    fun init() = loadKoinModules(serviceModule)
}