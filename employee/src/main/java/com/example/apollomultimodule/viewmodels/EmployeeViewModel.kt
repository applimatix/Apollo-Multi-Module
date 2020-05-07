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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    private val _employee: MutableLiveData<Employee> = MutableLiveData()
    val employee: LiveData<Employee> = _employee

    private val _employeeListController: MutableLiveData<EmployeeListController> = MutableLiveData()
    val employeeListController: LiveData<EmployeeListController> = _employeeListController

    private val _actions: MutableSingleLiveEvent<Actions> = MutableSingleLiveEvent()
    val actions: SingleLiveEvent<Actions> = _actions

    fun fetchEmployee(employeeId: String) {

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    getApplication<ApolloApplication>().companiesService.fetchEmployee(employeeId)
                }

                response.data?.employee?.let {
                    _employee.value = Employee.fromService(it)
                    _employeeListController.value =
                        EmployeeListController(
                            it.subordinates?.mapNotNull {
                                Employee.fromService(it)
                            } ?: listOf())
                }
            } catch (e: Exception) {
                _actions.value =
                    Actions.ShowError(
                        e
                    )
            }
        }
    }

    sealed class Actions {
        data class ShowError(val exception: Exception) : Actions()
    }
}