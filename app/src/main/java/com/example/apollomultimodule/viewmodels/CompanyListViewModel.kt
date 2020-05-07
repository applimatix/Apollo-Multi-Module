package com.example.apollomultimodule.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apollomultimodule.base.ApolloApplication
import com.example.apollomultimodule.base.data.models.Company
import com.example.apollomultimodule.base.util.MutableSingleLiveEvent
import com.example.apollomultimodule.base.util.SingleLiveEvent
import com.example.apollomultimodule.viewmodels.epoxy.CompanyListController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompanyViewModel(application: Application) : AndroidViewModel(application),
    CompanySelectedHandler {

    private val _companyListController: MutableLiveData<CompanyListController> = MutableLiveData()
    val companyListController: LiveData<CompanyListController> = _companyListController

    private val _actions: MutableSingleLiveEvent<Actions> = MutableSingleLiveEvent()
    val actions: SingleLiveEvent<Actions> = _actions

    fun fetchCompanies() {

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    getApplication<ApolloApplication>().companiesService.fetchCompanies()
                }

                _companyListController.value =
                    CompanyListController(
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