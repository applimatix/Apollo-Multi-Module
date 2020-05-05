package com.example.apollomultimodule.viewmodels.epoxy

import android.view.View
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.apollomultimodule.R
import com.example.apollomultimodule.data.models.Company

@EpoxyModelClass(layout = R.layout.item_company)
abstract class CompanyModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    lateinit var company: Company

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null
}