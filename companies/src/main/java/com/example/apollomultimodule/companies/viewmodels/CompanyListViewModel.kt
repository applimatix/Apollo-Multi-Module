package com.example.apollomultimodule.companies.viewmodels

import androidx.lifecycle.*
import com.example.apollomultimodule.base.data.models.Company
import com.example.apollomultimodule.companies.data.service.CompaniesService
import com.example.apollomultimodule.base.util.MutableSingleLiveEvent
import com.example.apollomultimodule.base.util.SingleLiveEvent
import com.example.apollomultimodule.companies.data.models.fromService
import com.example.apollomultimodule.companies.viewmodels.epoxy.CompanyListController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompanyListViewModel(private val companiesService: CompaniesService) : ViewModel(),
    CompanySelectedHandler {

    private val _companyListController: MutableLiveData<CompanyListController> = MutableLiveData()
    val companyListController: LiveData<CompanyListController> = _companyListController

    private val _actions: MutableSingleLiveEvent<Actions> = MutableSingleLiveEvent()
    val actions: SingleLiveEvent<Actions> = _actions

    fun fetchCompanies() {

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    companiesService.fetchCompanies()
                }

                _companyListController.value =
                    CompanyListController(
                        response.data?.allCompanies?.mapNotNull {
                            Company.fromService(it)
                        } ?: listOf(), this@CompanyListViewModel)
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