package com.example.apollomultimodule.base.data.models

data class Employee(
    val id: String,
    val firstName: String,
    val lastName: String,
    val address: String = "",
    val subordinates: List<Employee> = listOf(),
    val company: Company? = null
) {
    companion object {

    }
}