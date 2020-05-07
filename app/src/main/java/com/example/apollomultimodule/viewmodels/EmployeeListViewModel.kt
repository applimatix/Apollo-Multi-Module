package com.example.apollomultimodule.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apollomultimodule.base.ApolloApplication
import com.example.apollomultimodule.base.data.models.Employee
import com.example.apollomultimodule.base.util.MutableSingleLiveEvent
import com.example.apollomultimodule.base.util.SingleLiveEvent
import com.example.apollomultimodule.base.viewmodels.epoxy.EmployeeListController
import com.example.apollomultimodule.base.viewmodels.epoxy.EmployeeSelectedHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeListViewModel(application: Application) : AndroidViewModel(application),
    EmployeeSelectedHandler {

    private val _employeeListController: MutableLiveData<EmployeeListController> = MutableLiveData()
    val employeeListController: LiveData<EmployeeListController> = _employeeListController

    private val _actions: MutableSingleLiveEvent<Actions> = MutableSingleLiveEvent()
    val actions: SingleLiveEvent<Actions> = _actions

    fun fetchEmployees(companyId: String) {

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    getApplication<ApolloApplication>().companiesService.fetchCompanyEmployees(companyId)
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