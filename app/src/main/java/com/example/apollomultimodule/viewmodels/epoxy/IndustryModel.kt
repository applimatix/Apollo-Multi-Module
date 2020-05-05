package com.example.apollomultimodule.viewmodels.epoxy

import android.view.View
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.apollomultimodule.R
import com.example.apollomultimodule.data.models.Company

@EpoxyModelClass(layout = R.layout.item_industry)
abstract class IndustryModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    lateinit var industry: String
}