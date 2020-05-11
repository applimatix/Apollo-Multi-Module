package com.example.apollomultimodule.companies.data.models

import com.example.apollomultimodule.apollo.AllCompaniesQuery
import com.example.apollomultimodule.base.data.models.Company

fun Company.Companion.fromService(allCompany: AllCompaniesQuery.AllCompany): Company? =
    if (allCompany.fragments.subCompany.id != null && allCompany.fragments.subCompany.name != null && allCompany.fragments.subCompany.industry != null)
        Company(allCompany.fragments.subCompany.id!!, allCompany.fragments.subCompany.name!!, allCompany.fragments.subCompany.industry!!)
    else null