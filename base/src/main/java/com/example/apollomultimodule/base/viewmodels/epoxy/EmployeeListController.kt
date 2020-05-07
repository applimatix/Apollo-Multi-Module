package com.example.apollomultimodule.base.viewmodels.epoxy

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.OnModelClickListener
import com.example.apollomultimodule.base.data.models.Employee

class EmployeeListController(
    private val employees: List<Employee>,
    private val handler: EmployeeSelectedHandler? = null
) : EpoxyController() {

    override fun buildModels() {
        employees.forEach { employee ->
            val employeeModel = EmployeeModel_()
                .id(employee.id)
                .employee(employee)

            handler?.let {
                employeeModel.clickListener(OnModelClickListener { model, _, _, _ ->
                    handler.employeeSelected(
                        model.employee
                    )
                })
            }

            employeeModel.addTo(this)
        }
    }

}

interface EmployeeSelectedHandler {
    fun employeeSelected(employee: Employee)
}