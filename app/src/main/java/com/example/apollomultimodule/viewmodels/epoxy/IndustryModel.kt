package com.example.apollomultimodule.viewmodels.epoxy

import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.apollomultimodule.R

@EpoxyModelClass(layout = R.layout.item_industry)
abstract class IndustryModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    lateinit var industry: String
}