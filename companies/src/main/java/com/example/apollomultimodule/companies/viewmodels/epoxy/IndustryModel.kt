package com.example.apollomultimodule.companies.viewmodels.epoxy

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.example.apollomultimodule.companies.BR
import com.example.apollomultimodule.companies.R

@EpoxyModelClass
abstract class IndustryModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    lateinit var industry: String

    // Module workarounds
    override fun getDefaultLayout(): Int {
        return R.layout.item_industry
    }

    override fun setDataBindingVariables(binding: ViewDataBinding) {
        check(
            binding.setVariable(
                BR.industry,
                industry
            )
        ) { "The attribute industry was defined in your data binding model (com.example.apollomultimodule.companies.viewmodels.epoxy.IndustryModel) but a data variable of that name was not found in the layout." }
    }

    override fun setDataBindingVariables(
        binding: ViewDataBinding,
        previousModel: EpoxyModel<*>?
    ) {
        if (previousModel !is IndustryModel_) {
            setDataBindingVariables(binding)
            return
        }
        val that = previousModel
        if (industry != that.industry) {
            binding.setVariable(BR.industry, industry)
        }
    }
}