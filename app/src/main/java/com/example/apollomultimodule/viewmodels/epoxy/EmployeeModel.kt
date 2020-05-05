package com.example.apollomultimodule.viewmodels.epoxy

import android.view.View
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.apollomultimodule.R
import com.example.apollomultimodule.data.models.Employee

@EpoxyModelClass(layout = R.layout.item_employee)
abstract class EmployeeModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    lateinit var employee: Employee

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null
}