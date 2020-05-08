package com.example.apollomultimodule.employee.viewmodels

import androidx.lifecycle.*
import com.example.apollomultimodule.base.data.models.Employee
import com.example.apollomultimodule.base.util.MutableSingleLiveEvent
import com.example.apollomultimodule.base.util.SingleLiveEvent
import com.example.apollomultimodule.base.viewmodels.epoxy.EmployeeListController
import com.example.apollomultimodule.employee.data.models.fromService
import com.example.apollomultimodule.employee.data.service.EmployeeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeViewModel(private val employeeService: EmployeeService) : ViewModel() {



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
                    employeeService.fetchEmployee(employeeId)
                }

                response.data?.employee?.let {
                    _employee.value = Employee.fromService(it)
                    _employeeListController.value =
                        EmployeeListController(
                            it.subordinates?.mapNotNull { sub ->
                                Employee.fromService(sub)
                            } ?: listOf())
                }
            } catch (e: Exception) {
                _actions.value =
                    Actions.ShowError(e)
            }
        }
    }

    sealed class Actions {
        data class ShowError(val exception: Exception) : Actions()
    }
}