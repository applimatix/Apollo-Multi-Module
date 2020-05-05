package com.example.apollomultimodule

import android.app.Application
import com.apollographql.apollo.ApolloClient
import com.example.apollomultimodule.data.service.CompaniesService

class ApolloApplication : Application() {

    private val apolloClient = ApolloClient.builder()
        .serverUrl("http://localhost:9002/graphql")
        .build()

    val companiesService : CompaniesService = CompaniesService(apolloClient)

}