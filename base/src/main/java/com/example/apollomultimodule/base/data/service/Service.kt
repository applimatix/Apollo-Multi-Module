package com.example.apollomultimodule.base.data.service

import com.apollographql.apollo.ApolloClient
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class Service : KoinComponent {
    val apolloClient: ApolloClient by inject()
}