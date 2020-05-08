package com.example.apollomultimodule.companies.viewmodels.epoxy

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.example.apollomultimodule.base.data.models.Company
import com.example.apollomultimodule.companies.BR
import com.example.apollomultimodule.companies.R

@EpoxyModelClass
abstract class CompanyModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    lateinit var company: Company

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    // Module workarounds
    override fun getDefaultLayout(): Int {
        return R.layout.item_company
    }

    override fun setDataBindingVariables(binding: ViewDataBinding) {
        check(
            binding.setVariable(
                BR.company,
                company
            )
        ) { "The attribute company was defined in your data binding model (com.example.apollomultimodule.companies.viewmodels.epoxy.CompanyModel) but a data variable of that name was not found in the layout." }
        check(
            binding.setVariable(
                BR.clickListener,
                clickListener
            )
        ) { "The attribute clickListener was defined in your data binding model (com.example.apollomultimodule.companies.viewmodels.epoxy.CompanyModel) but a data variable of that name was not found in the layout." }
    }

    override fun setDataBindingVariables(
        binding: ViewDataBinding,
        previousModel: EpoxyModel<*>?
    ) {
        if (previousModel !is CompanyModel_) {
            setDataBindingVariables(binding)
            return
        }
        val that = previousModel
        if (company != that.company) {
            binding.setVariable(BR.company, company)
        }
        if (clickListener == null != (that.clickListener == null)) {
            binding.setVariable(BR.clickListener, clickListener)
        }
    }
}