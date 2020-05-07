package com.example.apollomultimodule.viewmodels.epoxy

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.OnModelClickListener
import com.example.apollomultimodule.base.data.models.Company
import com.example.apollomultimodule.viewmodels.CompanySelectedHandler

class CompanyListController(
    private val companies: List<Company>,
    private val handler: CompanySelectedHandler
) : EpoxyController() {

    override fun buildModels() {
        var lastIndustry = ""

        companies.sortedBy { it.industry }
            .forEach { company ->

                if (lastIndustry != company.industry) {
                    IndustryModel_()
                        .id("industry_${company.industry}")
                        .industry(company.industry)
                        .addTo(this)
                    lastIndustry = company.industry
                }

                CompanyModel_()
                    .id(company.id)
                    .company(company)
                    .clickListener(OnModelClickListener { model, _, _, _ ->
                        handler.companySelected(
                            model.company
                        )
                    })
                    .addTo(this)
            }
    }

}