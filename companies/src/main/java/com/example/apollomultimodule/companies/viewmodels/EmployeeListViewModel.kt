package com.example.apollomultimodule.companies.viewmodels

import androidx.lifecycle.*
import com.example.apollomultimodule.base.data.models.Employee
import com.example.apollomultimodule.companies.data.service.CompaniesService
import com.example.apollomultimodule.base.util.MutableSingleLiveEvent
import com.example.apollomultimodule.base.util.SingleLiveEvent
import com.example.apollomultimodule.base.viewmodels.epoxy.EmployeeListController
import com.example.apollomultimodule.base.viewmodels.epoxy.EmployeeSelectedHandler
import com.example.apollomultimodule.companies.data.models.fromService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeListViewModel(private val companiesService: CompaniesService) : ViewModel(),
    EmployeeSelectedHandler {

    private val _employeeListController: MutableLiveData<EmployeeListController> = MutableLiveData()
    val employeeListController: LiveData<EmployeeListController> = _employeeListController

    private val _actions: MutableSingleLiveEvent<Actions> = MutableSingleLiveEvent()
    val actions: SingleLiveEvent<Actions> = _actions

    fun fetchEmployees(companyId: String) {

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    companiesService.fetchCompanyEmployees(companyId)
                }

                _employeeListController.value =
                    EmployeeListController(
                        response.data?.company?.employees?.mapNotNull {
                            Employee.fromService(it)
                        } ?: listOf(), this@EmployeeListViewModel)
            } catch (e: Exception) {
                _actions.value = Actions.ShowError(e)
            }
        }
    }

    override fun employeeSelected(employee: Employee) {
        _actions.value = Actions.ShowEmployee(employee)
    }

    sealed class Actions {
        data class ShowEmployee(val employee: Employee) : Actions()
        data class ShowError(val exception: Exception) : Actions()
    }
}