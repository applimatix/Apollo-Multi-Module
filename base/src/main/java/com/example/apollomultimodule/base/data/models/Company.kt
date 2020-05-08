package com.example.apollomultimodule.base.data.models

data class Company(
    val id: String,
    val name: String,
    val industry: String,
    val employees: List<Employee> = listOf()
) {
    companion object {

    }
}