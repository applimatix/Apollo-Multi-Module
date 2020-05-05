package com.example.apollomultimodule.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apollomultimodule.ApolloApplication
import com.example.apollomultimodule.data.models.Company
import com.example.apollomultimodule.util.MutableSingleLiveEvent
import com.example.apollomultimodule.util.SingleLiveEvent
import com.example.apollomultimodule.viewmodels.epoxy.CompanyController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompanyViewModel(application: Application) : AndroidViewModel(application),
    CompanySelectedHandler {

    private val _companyController: MutableLiveData<CompanyController> = MutableLiveData()
    val companyController: LiveData<CompanyController> = _companyController

    private val _actions: MutableSingleLiveEvent<Actions> = MutableSingleLiveEvent()
    val actions: SingleLiveEvent<Actions> = _actions

    fun fetchCompanies() {

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    getApplication<ApolloApplication>().companiesService.fetchCompanies()
                }

                _companyController.value =
                    CompanyController(
                        response.data?.allCompanies?.mapNotNull {
                            Company.fromService(it)
                        } ?: listOf(), this@CompanyViewModel)
            } catch (e: Exception) {
                _actions.value = Actions.ShowError(e)
            }
        }
    }

    override fun companySelected(company: Company) {
        _actions.value = Actions.ShowCompanyEmployees(company)
    }

    sealed class Actions {
        data class ShowCompanyEmployees(val company: Company) : Actions()
        data class ShowError(val exception: Exception) : Actions()
    }
}


interface CompanySelectedHandler {
    fun companySelected(company: Company)
}